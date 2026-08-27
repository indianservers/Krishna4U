# Initial visual comparison

Device pass: Android API 35 emulator, 1080 × 2400 (393 dp-class portrait). Screenshots are in `build/verification`.

| Screen | Result | Comparison notes |
|---|---|---|
| Splash | Pass after iteration | Layered cosmic background, halo, Krishna, native title/footer and loading art reproduce the reference hierarchy. Splash hold increased to 3 seconds so loading state is legible. |
| Divine Onboarding | Pass with asset caveat | Hero scale, lower gradient, heading, actions and pager closely follow reference. The supplied chariot PNG contains a small opaque checker pattern in its central opening; source was not altered. |
| Choose Language | Pass | Feather-led hierarchy, four native selectable glass rows, selected check and gold CTA align closely. Responsive version is slightly less ornament-heavy than the raster reference. |
| Personalise Journey | Pass | Lotus emphasis, two-column chips, selected/non-selected states and CTA reproduce the reference. Native layouts retain 48 dp targets. |
| Home | Pass | Greeting, portrait teaching hero, journey progress, feature grid and fixed custom bottom navigation match the structure. The portrait is aspect-fit rather than cropped, preserving dignity at the cost of a smaller face than the reference. |

No application crash or missing-resource error appeared in Logcat during the complete entry flow. The emulator itself briefly produced Pixel Launcher/System UI ANR dialogs unrelated to the app; these were dismissed before the final captures.
