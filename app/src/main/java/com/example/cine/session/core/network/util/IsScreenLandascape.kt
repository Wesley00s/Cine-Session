package com.example.cine.session.core.network.util

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun isScreenLandscape(): Boolean {
    val configuration = LocalConfiguration.current

    return when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> true
        else -> false
    }
}
