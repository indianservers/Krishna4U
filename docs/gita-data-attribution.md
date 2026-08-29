# Bhagavad Gita offline data attribution

The app’s 700-verse offline corpus is derived from the [`gita-quotes` dataset](https://github.com/ChiragMirani/gita-quotes), which republishes Sanskrit, IAST transliteration, and the 1935 English translation by Shri Purohit Swami. The repository identifies that translation as public domain and permits reuse of the full dataset.

Upstream data was collected from `vedicscriptures.github.io`. The imported copy is bundled locally; the Android app makes no network request.

## Verse-count normalization

The upstream file contains 701 numbered records because its recension counts Arjuna’s opening question in Chapter 13 as an additional verse. For the requested conventional 700-verse edition, that opening question is treated as unnumbered and the remaining Chapter 13 records are normalized to verses 1–34. Sanskrit verse-number suffixes are removed during import to prevent conflicting source numbering.

The `englishSummary` field contains the public-domain English rendering with the numeric prefix removed. It is presented as a concise English meaning of the verse, not as new theological commentary.
