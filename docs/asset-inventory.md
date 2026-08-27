# Asset inventory

Inventory confirmed: **156 files** — 92 PNG and 64 SVG. All PNG alpha information was inspected. SVGs are text/vector sources and inherently support transparent backgrounds. Original SVGs are preserved under `app/src/main/assets/vector_source`; Android uses the supplied raster equivalents where gradient-heavy SVG conversion would lose fidelity.

## Asset groups and Android treatment

| Folder/files | Count | Dimensions | Type/transparency | Intended purpose | Android resource |
|---|---:|---|---|---|---|
| `app_icon/app_icon_{48,72,96,144,192,512,1024}.png` | 7 | filename size, square | PNG/RGBA | Legacy/store icon sizes | launcher/store source |
| `app_icon/app_icon_background.{png,svg}` | 2 | PNG 1024² | RGBA/vector | Adaptive icon background | `drawable-nodpi` + source asset |
| `app_icon/app_icon_foreground.{png,svg}` | 2 | PNG 1024² | RGBA/vector | Adaptive icon foreground/monochrome | `drawable-nodpi` + source asset |
| `backgrounds/*.png` | 9 | 941 × 1672 | PNG/RGB, opaque | Full-screen scene plates | `drawable-nodpi` |
| `effects/*.png` | 3 | 1254²; halo 1247 × 1261 | PNG/RGBA | Breathing orb, rotating halo, stardust | `drawable-nodpi` |
| `icons/png/*.png` | 48 | 512² | PNG/RGBA | Gradient custom functional/category icons | `drawable-nodpi` |
| `icons/svg/*.svg` | 48 | scalable | SVG/transparent | Editable vector originals | `assets/vector_source` |
| `illustrations/*.png` | 9 | 1024 × 1536 except chariot 1122 × 1402, Gita 1233 × 1275, tree 1224 × 1285 | PNG/RGBA | Krishna, Gita and seeker heroes | `drawable-nodpi` |
| `ui_elements/png/*.png` | 14 | 1024² or 2048-wide | PNG/RGBA | Frames, ornaments, rings, waveform | `drawable-nodpi` / Compose reference |
| `ui_elements/svg/*.svg` | 14 | scalable | SVG/transparent | Editable UI sources | `assets/vector_source` |

## Every supplied filename

Backgrounds (all opaque 941 × 1672 PNG): `01_cosmic_mandala`, `02_vrindavan_dawn`, `03_kurukshetra_cosmos`, `04_sacred_cosmic_temple`, `05_moonlit_sacred_river`, `06_dharma_crossroads`, `07_gita_wisdom`, `08_minimal_starfield`, `09_lotus_reflection`.

Effects (transparent PNG): `01_breathing_lotus_orb` (1254²), `02_rotating_mandala_halo` (1247 × 1261), `03_stardust_particles` (1254²).

Illustrations (transparent PNG): `01_krishna_full_body` (1024 × 1536), `02_krishna_portrait` (1024 × 1536), `03_krishna_arjuna_chariot` (1122 × 1402), `04_govardhan` (1024 × 1536), `05_vishvarupa` (1024 × 1536), `06_meditating_seeker` (1024 × 1536), `07_open_gita` (1233 × 1275), `08_wisdom_tree` (1224 × 1285), `09_peacock_feather` (1024 × 1536).

Icons — each name exists once as transparent 512 × 512 PNG and once as transparent scalable SVG, and is suitable as `drawable-nodpi` plus preserved vector source: `ask_krishna`, `audio`, `bookmark`, `calendar`, `chakra`, `check`, `compassion`, `courage`, `dharma`, `download`, `explore`, `flute`, `font_size`, `friendship`, `gita`, `home`, `info`, `inner_peace`, `journal`, `karma`, `language`, `leadership`, `life_journey`, `lock`, `lotus`, `love`, `meditation`, `mind`, `next`, `notification`, `om`, `pause`, `peacock_feather`, `play`, `playfulness`, `previous`, `privacy`, `profile`, `purpose`, `relationships`, `search`, `settings`, `share`, `strategy`, `teachings`, `theme`, `timer`, `wifi`.

UI elements — each name exists once as transparent PNG and once as transparent scalable SVG: `audio_waveform` (2048 × 560), `bottom_nav_frame` (2048 × 520), `chip_frame` (1024 × 360), `constellation_lines` (2048 × 1280), `glass_card_frame` (2048 × 1120), `glow_orb` (1024²), `gold_divider` (2048 × 256), `header_ornament` (2048 × 360), `loading_ring` (1024²), `lotus_pedestal` (2048 × 840), `mandala_ring` (1024²), `primary_button_frame` (2048 × 480), `progress_ring` (1024²), `secondary_button_frame` (2048 × 480). Their intended purposes correspond to their names; responsive cards/buttons/chips/navigation are recreated in Compose while ornament/ring/waveform art can be layered without stretching.

## Import status

85 runtime PNG resources were imported: adaptive icon layers, all backgrounds/effects/icons/illustrations/UI elements. The seven redundant fixed-size complete icon PNGs were retained only in the untouched source pack. All 64 SVG originals were imported as source assets. No remote or generated artwork is used.
