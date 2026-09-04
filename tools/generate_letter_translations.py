"""Generate local Telugu/Hindi Krishna Letter content used at build/runtime.

The generated Kotlin file is committed with the app; this script is a development-only
content tool and is never called by the Android application.
"""
from __future__ import annotations

import html
import json
import re
import time
import urllib.parse
import urllib.request
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SOURCE = ROOT / "app/src/main/java/com/indianservers/krishna4u/feature/letters/KrishnaLettersContent.kt"
OUTPUT = ROOT / "app/src/main/java/com/indianservers/krishna4u/feature/letters/KrishnaLettersTranslationsGenerated.kt"
CACHE = ROOT / "tools/letter_translation_cache.json"
LANGUAGES = {"te": "teluguKrishnaLetterTranslations", "hi": "hindiKrishnaLetterTranslations"}

SIMPLE_REPLACEMENTS = [
    ("qualified medical care", "care from a trained doctor"), ("qualified support", "trained help"),
    ("qualified help", "trained help"), ("professional support", "trained help"),
    ("nonessential", "not needed"), ("interrogation", "too many questions"),
    ("self-betrayal", "betraying yourself"), ("consistent behaviour", "steady actions"),
    ("independently", "on your own"), ("circumstances", "situations"),
    ("circumstance", "situation"), ("unmanageable", "too heavy to carry"),
    ("depleted", "very tired"), ("manufacture", "force"), ("recognition", "praise"),
    ("possessiveness", "the need to control"), ("possessive", "controlling"),
    ("exploits", "takes advantage of"), ("exploit", "take advantage of"),
]


def kotlin_strings(block: str) -> list[str]:
    values: list[str] = []
    i = 0
    while i < len(block):
        if block[i] != '"':
            i += 1
            continue
        i += 1
        chars: list[str] = []
        while i < len(block):
            char = block[i]
            if char == '"':
                i += 1
                break
            if char == "\\" and i + 1 < len(block):
                i += 1
                escaped = block[i]
                chars.append({"n": "\n", "r": "\r", "t": "\t", '"': '"', "\\": "\\", "$": "$"}.get(escaped, escaped))
            else:
                chars.append(char)
            i += 1
        values.append("".join(chars))
    return values


def call_block(text: str, open_paren: int) -> tuple[str, int]:
    depth = 0
    in_string = False
    escaped = False
    for i in range(open_paren, len(text)):
        char = text[i]
        if in_string:
            if escaped:
                escaped = False
            elif char == "\\":
                escaped = True
            elif char == '"':
                in_string = False
            continue
        if char == '"':
            in_string = True
        elif char == "(":
            depth += 1
        elif char == ")":
            depth -= 1
            if depth == 0:
                return text[open_paren : i + 1], i + 1
    raise ValueError("Unclosed letter call")


def simple_english(value: str) -> str:
    result = value
    for formal, simple in SIMPLE_REPLACEMENTS:
        result = re.sub(re.escape(formal), simple, result, flags=re.IGNORECASE)
    return result


def parse_letters() -> list[dict[str, object]]:
    whole_text = SOURCE.read_text(encoding="utf-8")
    touch_section = whole_text[
        whole_text.index("private fun krishnaPersonalTouch") : whole_text.index("private fun personalLetter")
    ]
    personal_touches = {
        match.group(1): kotlin_strings(match.group(2))[0]
        for match in re.finditer(r'^\s*"([^"]+)"\s*->\s*("(?:\\.|[^"\\])*")', touch_section, re.MULTILINE)
    }
    text = whole_text[whole_text.index("val krishnaLetters = listOf(") :]
    pattern = re.compile(r"(KrishnaLetter|personalLetter)\(")
    letters: list[dict[str, object]] = []
    position = 0
    while match := pattern.search(text, position):
        call_name = match.group(1)
        block, position = call_block(text, match.end() - 1)
        values = kotlin_strings(block)
        expected = 10 if call_name == "KrishnaLetter" else 9
        if len(values) < expected:
            raise ValueError(f"Expected {expected} strings, found {len(values)} near {values[:2]}")
        letter_id, situation, title, preview = values[:4]
        if call_name == "KrishnaLetter":
            paragraph_values = values[4:8]
            reflection, next_step = values[8:10]
        else:
            if letter_id not in personal_touches:
                raise ValueError(f"Missing personal touch for {letter_id}")
            paragraph_values = values[4:7] + [personal_touches[letter_id]]
            reflection, next_step = values[7:9]
        paragraphs = [simple_english(value) for value in paragraph_values]
        paragraphs[-1] = " ".join(re.split(r"(?<=[.!?])\s+", paragraphs[-1])[:2])
        paragraphs[0] = re.sub(r"^My dear one,\s*", "", paragraphs[0], flags=re.IGNORECASE)
        letters.append({
            "id": letter_id, "situation": situation, "title": title, "preview": preview,
            "paragraphs": paragraphs, "reflection": reflection, "nextStep": next_step,
        })
    if len(letters) != 89:
        raise ValueError(f"Expected 89 letters, parsed {len(letters)}")
    return letters


def translate_request(text: str, language: str) -> str:
    query = urllib.parse.urlencode({"sl": "en", "tl": language, "hl": "en-US", "q": text})
    request = urllib.request.Request(
        f"https://translate.google.com/m?{query}",
        headers={"User-Agent": "Mozilla/5.0 (Krishna-For-You content localization)"},
    )
    with urllib.request.urlopen(request, timeout=45) as response:
        page = response.read().decode("utf-8")
    match = re.search(r'<div class="result-container">(.*?)</div>', page, flags=re.DOTALL)
    if not match:
        raise RuntimeError("Translation response did not contain a result")
    return html.unescape(re.sub(r"<[^>]+>", "", match.group(1))).strip()


def translate_fields(fields: list[str], language: str) -> list[str]:
    def normalize_unicode_escapes(value: str) -> str:
        return re.sub(
            r"(?:\\)+u([0-9a-fA-F]{4})",
            lambda match: chr(int(match.group(1), 16)),
            value,
        )

    batches: list[list[tuple[int, str]]] = []
    current: list[tuple[int, str]] = []
    size = 0
    for index, field in enumerate(fields):
        addition = len(field) + 18
        if current and size + addition > 900:
            batches.append(current)
            current = []
            size = 0
        current.append((index, field))
        size += addition
    if current:
        batches.append(current)

    translated = [""] * len(fields)
    for batch in batches:
        source = " ".join(f"[KFY{index}] {value}" for index, value in batch)
        last_error: Exception | None = None
        for attempt in range(5):
            try:
                result = translate_request(source, language)
                result = normalize_unicode_escapes(result)
                pieces = re.split(r"\s*\[KFY(\d+)]\s*", result)
                found = {int(pieces[i]): pieces[i + 1].strip() for i in range(1, len(pieces), 2)}
                for index, original in batch:
                    if index in found and found[index]:
                        translated[index] = found[index]
                    else:
                        translated[index] = normalize_unicode_escapes(translate_request(original, language))
                time.sleep(0.18)
                break
            except Exception as error:  # network retries are only for this development generator
                last_error = error
                time.sleep(1.5 * (attempt + 1))
        else:
            raise RuntimeError(f"Could not translate batch to {language}") from last_error
    return translated


def kotlin(value: str) -> str:
    return '"' + value.replace("\\", "\\\\").replace('"', '\\"').replace("$", "\\$").replace("\n", "\\n") + '"'


def main() -> None:
    letters = parse_letters()
    cache = json.loads(CACHE.read_text(encoding="utf-8")) if CACHE.exists() else {}
    for language in LANGUAGES:
        language_cache = cache.setdefault(language, {})
        for number, letter in enumerate(letters, start=1):
            letter_id = str(letter["id"])
            fields = [
                str(letter["situation"]), str(letter["title"]), str(letter["preview"]),
                *[str(value) for value in letter["paragraphs"]],
                str(letter["reflection"]), str(letter["nextStep"]),
            ]
            fingerprint = "\n".join(fields)
            cached = language_cache.get(letter_id)
            if not cached or cached.get("source") != fingerprint:
                translated = translate_fields(fields, language)
                language_cache[letter_id] = {"source": fingerprint, "translated": translated}
                CACHE.write_text(json.dumps(cache, ensure_ascii=False, indent=2), encoding="utf-8")
            print(f"{language}: {number:02d}/{len(letters)} {letter_id}", flush=True)

    lines = [
        "// Generated by tools/generate_letter_translations.py. Do not edit individual entries by hand.",
        "package com.indianservers.krishna4u.feature.letters", "",
    ]
    for language, variable in LANGUAGES.items():
        lines.append(f"internal val {variable}: Map<String, KrishnaLetterTranslation> = mapOf(")
        for letter in letters:
            letter_id = str(letter["id"])
            values = cache[language][letter_id]["translated"]
            paragraphs = ", ".join(kotlin(value) for value in values[3:7])
            lines.extend([
                f"    {kotlin(letter_id)} to KrishnaLetterTranslation(",
                f"        situation = {kotlin(values[0])},",
                f"        title = {kotlin(values[1])},",
                f"        preview = {kotlin(values[2])},",
                f"        paragraphs = listOf({paragraphs}),",
                f"        reflection = {kotlin(values[7])},",
                f"        nextStep = {kotlin(values[8])}",
                "    ),",
            ])
        lines.extend([")", ""])
    OUTPUT.write_text("\n".join(lines), encoding="utf-8")
    print(f"Wrote {OUTPUT}")


if __name__ == "__main__":
    main()
