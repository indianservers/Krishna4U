package com.indianservers.krishna4u

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.indianservers.krishna4u.ui.theme.Krishna4UTheme
import androidx.compose.runtime.mutableStateOf

class MainActivity : ComponentActivity() {
    companion object { const val DESTINATION_EXTRA = "krishna_destination" }

    private val pendingDestination = mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pendingDestination.value = intent?.getStringExtra(DESTINATION_EXTRA)
        enableEdgeToEdge()
        setContent {
            Krishna4UTheme {
                KrishnaApp(pendingDestination.value) { pendingDestination.value = null }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        pendingDestination.value = intent.getStringExtra(DESTINATION_EXTRA)
    }
}
