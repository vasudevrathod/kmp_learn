package com.vaasudev.kmptest._global.controller

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

// Option 1: Expect function
@Composable
expect fun getScreenHeightDp(): Dp

// Option 2: Expect interface (more flexible for multiple properties)
interface PlatformScreenInfo {
    val screenHeightDp: Dp
    val screenWidthDp: Dp
    // Add other properties like density, status bar height if needed
}

@Composable
expect fun rememberPlatformScreenInfo(): PlatformScreenInfo