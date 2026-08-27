# Verified Bhagavad Gita import schema

The app intentionally does not invent or ship a synthetic 700-verse corpus. A future verified dataset can be imported as UTF-8 JSON using this structure:

```json
{
  "source": { "title": "", "publisher": "", "edition": "", "license": "", "verifiedAt": "YYYY-MM-DD" },
  "chapters": [{
    "number": 1,
    "sanskritTitle": "",
    "transliteratedTitle": "",
    "englishTitle": "",
    "summary": { "language": "en", "text": "", "sourceCitation": "" },
    "verses": [{
      "number": 1,
      "sanskrit": "",
      "transliteration": "",
      "translations": [{ "language": "en", "text": "", "translator": "", "sourceCitation": "" }],
      "wordMeanings": [{ "word": "", "transliteration": "", "meaning": "" }],
      "commentary": [{ "language": "en", "text": "", "author": "", "sourceCitation": "" }],
      "audioAsset": null
    }]
  }]
}
```

Validation must enforce chapters 1–18, unique verse numbers within a chapter, non-empty Sanskrit, explicit translator/source/license metadata, and local-only audio asset references.
