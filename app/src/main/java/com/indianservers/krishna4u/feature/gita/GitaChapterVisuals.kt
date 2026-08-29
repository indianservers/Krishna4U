package com.indianservers.krishna4u.feature.gita

import androidx.annotation.DrawableRes
import com.indianservers.krishna4u.R

@DrawableRes
internal fun chapterIcon(chapter: Int): Int = when (chapter) {
    1 -> R.drawable.icon_courage
    2 -> R.drawable.icon_meditation
    3 -> R.drawable.icon_karma
    4 -> R.drawable.icon_teachings
    5 -> R.drawable.icon_lotus
    6 -> R.drawable.icon_mind
    7 -> R.drawable.icon_gita
    8 -> R.drawable.icon_purpose
    9 -> R.drawable.icon_om
    10 -> R.drawable.icon_life_journey
    11 -> R.drawable.icon_chakra
    12 -> R.drawable.icon_compassion
    13 -> R.drawable.icon_inner_peace
    14 -> R.drawable.icon_strategy
    15 -> R.drawable.icon_peacock_feather
    16 -> R.drawable.icon_dharma
    17 -> R.drawable.icon_love
    18 -> R.drawable.icon_conch
    else -> R.drawable.icon_gita
}

@DrawableRes
internal fun chapterIllustration(chapter: Int): Int = when (chapter) {
    5, 6 -> R.drawable.illustration_06_meditating_seeker
    7, 8, 9, 10, 12 -> R.drawable.illustration_02_krishna_portrait
    11 -> R.drawable.illustration_05_vishvarupa
    13, 14 -> R.drawable.illustration_08_wisdom_tree
    15 -> R.drawable.illustration_01_krishna_full_body
    17 -> R.drawable.illustration_07_open_gita
    else -> R.drawable.illustration_03_krishna_arjuna_chariot
}

@DrawableRes
internal fun chapterBackground(chapter: Int): Int = when (chapter) {
    5, 6 -> R.drawable.bg_05_moonlit_sacred_river
    7, 8, 9, 10 -> R.drawable.bg_07_gita_wisdom
    11 -> R.drawable.bg_04_sacred_cosmic_temple
    12 -> R.drawable.bg_09_lotus_reflection
    13, 14, 15 -> R.drawable.bg_06_dharma_crossroads
    17 -> R.drawable.bg_07_gita_wisdom
    else -> R.drawable.bg_03_kurukshetra_cosmos
}
