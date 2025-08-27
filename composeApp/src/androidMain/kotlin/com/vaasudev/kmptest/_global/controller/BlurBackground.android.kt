package com.vaasudev.kmptest._global.controller

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
actual fun BlurBackground(modifier: Modifier) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        // Use Modifier.blur on Android 12+
        Box(
            modifier = modifier.blur(radius = 16.dp)
        )
    } else {
        // Fallback for older Android versions
        Box(
            modifier = modifier.background(Color.Black.copy(alpha = 0.5f))
        )
    }
}