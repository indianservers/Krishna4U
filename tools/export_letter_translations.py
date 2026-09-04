"""Export the complete local Letter translations as human-readable review documents."""
from __future__ import annotations

import json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
CACHE = ROOT / "tools/letter_translation_cache.json"
OUTPUT_DIR = ROOT / "docs/review"

CONFIG = {
    "te": {
        "file": "krishna_letters_telugu.md",
        "heading": "కృష్ణుని లేఖలు — తెలుగు",
        "greeting": "నా ప్రియమైన [పేరు],",
        "reflection": "ఆగి ఆలోచించండి",
        "step": "తదుపరి చిన్న అడుగు",
        "closing": "మీ ప్రతి నిజాయితీ అడుగులో మీతోనే,\n\n**కృష్ణుడు**",
    },
    "hi": {
        "file": "krishna_letters_hindi.md",
        "heading": "कृष्ण के पत्र — हिन्दी",
        "greeting": "मेरे प्रिय [नाम],",
        "reflection": "ठहरें और मनन करें",
        "step": "अगला सरल कदम",
        "closing": "आपके हर सच्चे कदम में आपके साथ,\n\n**कृष्ण**",
    },
}

TITLE_CORRECTIONS = {
    "te": {
        "failure": "మీరు ఒక ఫలితం కంటే ఎంతో ఎక్కువ", "loneliness": "మిమ్మల్ని మరచిపోలేదు",
        "tired-strong": "ఆ భారాన్ని దించవచ్చు", "self-forgiveness": "ఒక తప్పును జీవితాంత శిక్షగా మార్చుకోవద్దు",
        "stuck": "ముందడుగు మీరు అనుకున్నదానికంటే చిన్నగా మొదలవచ్చు", "change": "కొత్తదాన్ని ఒక్కో అడుగుగా ఎదుర్కోవచ్చు",
        "grateful": "కృతజ్ఞతను సేవగా మార్చండి", "hopeful": "ఆశను తదుపరి సరైన అడుగుగా మార్చండి",
        "confident": "ఆత్మవిశ్వాసం పనికి ఉపయోగపడనివ్వండి", "inspired": "స్ఫూర్తి తగ్గకముందే ప్రారంభించండి",
        "belonging": "మీ ఆప్యాయత వలయాన్ని విస్తరించండి", "homesick": "మీరు జీవించే విలువల్లో ఇంటిని వెంట తీసుకెళ్లండి",
        "identity-confusion": "మీరు ఇంకా ఎదుగుతున్నారనే విషయాన్ని అంగీకరించండి", "parent-failing": "పరిపూర్ణతకంటే మీ తదుపరి సరిదిద్దుకోవడమే ముఖ్యం",
        "rebuilding-trust": "నిజాన్ని మళ్లీ మళ్లీ జీవించినప్పుడు నమ్మకం తిరిగి వస్తుంది", "comfort-growth": "సౌకర్యం మిమ్మల్ని నిలబెట్టాలి, నియంత్రించకూడదు",
        "smart-work": "ప్రయత్నానికి వివేకాన్ని జోడించండి", "someone-only-takes": "ప్రేమలో ఇవ్వడమూ స్వీకరించడమూ ఉండాలి",
        "rise-again": "భారాన్ని కాదు, పాఠాన్ని తీసుకుని మళ్లీ లేవండి", "life-purpose": "మీ దగ్గర ఉన్న మంచితోనే ప్రయోజనం మొదలవుతుంది",
        "dream-discipline": "రోజువారీ సాధనే కలను ముందుకు నడిపిస్తుంది", "valuable-gift": "మీరు అందించగల మంచితనం ప్రపంచానికి అవసరం",
        "next-step-strength": "ఒక్క అడుగుకు సరిపడ వెలుగు చాలు",
    },
    "hi": {
        "failure": "आप एक परिणाम से कहीं अधिक हैं", "tired-strong": "आप यह बोझ नीचे रख सकते हैं",
        "self-forgiveness": "एक गलती को जीवन भर की सज़ा मत बनाइए", "stuck": "आगे बढ़ना एक छोटे कदम से शुरू हो सकता है",
        "unanswered-prayers": "मौन यह प्रमाण नहीं कि आपको भुला दिया गया है", "change": "नए बदलाव का सामना एक-एक कदम करके करें",
        "belonging": "अपने अपनत्व के दायरे को व्यापक बनाइए", "homesick": "अपने जीवन-मूल्यों में घर को साथ लेकर चलें",
        "identity-confusion": "आपका अभी अधूरा होना ठीक है", "parent-failing": "पूर्णता से अधिक अगला सुधार मायने रखता है",
        "smart-work": "प्रयास के साथ बुद्धि जोड़ें", "returning-to-hurt": "एक परिचित दरवाज़ा फिर भी दर्द तक ले जा सकता है",
        "memories-pull-back": "आज को छोड़े बिना अतीत को याद रखें", "closure-never-came": "आप अपनी ओर से उस दरवाज़े को बंद कर सकते हैं",
        "rise-again": "बोझ नहीं, सीख लेकर फिर उठें", "life-purpose": "उद्देश्य आपके पास मौजूद अच्छाई से शुरू होता है",
        "dream-discipline": "रोज़ का अभ्यास आपके सपने को आगे ले जाए", "future-still-open": "आपका भविष्य एक परिणाम से बड़ा है",
        "next-step-strength": "अगले एक कदम भर का प्रकाश पर्याप्त है",
    },
}


def main() -> None:
    cache = json.loads(CACHE.read_text(encoding="utf-8"))
    OUTPUT_DIR.mkdir(parents=True, exist_ok=True)
    for language, config in CONFIG.items():
        note = "యాప్‌లో [పేరు] స్థానంలో వినియోగదారి పేరు స్వయంచాలకంగా కనిపిస్తుంది." if language == "te" else "ऐप में [नाम] के स्थान पर उपयोगकर्ता का सहेजा हुआ नाम अपने आप दिखाई देगा।"
        lines = [f"# {config['heading']}", "", note, ""]
        for number, (letter_id, entry) in enumerate(cache[language].items(), start=1):
            values = entry["translated"]
            situation, title, preview = values[:3]
            title = TITLE_CORRECTIONS[language].get(letter_id, title)
            paragraphs = values[3:7]
            reflection, next_step = values[7:9]
            lines.extend([
                f"## {number}. {situation}", "", f"### {title}", "", f"*{preview}*", "",
                config["greeting"], "", paragraphs[0], "", paragraphs[1], "", paragraphs[2], "", paragraphs[3], "",
                f"**{config['reflection']}**", "", reflection, "",
                f"**{config['step']}**", "", next_step, "", config["closing"], "", "---", "",
            ])
        output = OUTPUT_DIR / config["file"]
        output.write_text("\n".join(lines), encoding="utf-8")
        print(f"Wrote {output} ({len(cache[language])} letters)")


if __name__ == "__main__":
    main()
