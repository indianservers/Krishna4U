package com.indianservers.krishna4u.core.sharing

import android.content.Context
import android.content.Intent

fun shareSacredText(context: Context, chooserTitle: String, text: String) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, chooserTitle)
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(Intent.createChooser(sendIntent, chooserTitle))
}
