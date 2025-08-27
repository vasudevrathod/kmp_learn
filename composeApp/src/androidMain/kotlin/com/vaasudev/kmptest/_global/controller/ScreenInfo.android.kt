package com.vaasudev.kmptest._global.controller

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// Actual implementation for Option 1: Expect function
@Composable
actual fun getScreenHeightDp(): Dp {
    return getWindowHeightInDpCorrectly()
}

@Composable
fun getWindowWidthInDpCorrectly(): Dp {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val widthInPx = windowInfo.containerSize.width
    val widthInDp = with(density) { widthInPx.toDp() }

    return widthInDp
}

@Composable
fun getWindowHeightInDpCorrectly(): Dp {
    val windowInfo = LocalWindowInfo.current
    val density = LocalDensity.current

    val heightInPx = windowInfo.containerSize.height
    val heighInDp = with(density) { heightInPx.toDp() }

    return heighInDp
}

class AndroidScreenInfo(
    override val screenHeightDp: Dp,
    override val screenWidthDp: Dp
) : PlatformScreenInfo

@Composable
actual fun rememberPlatformScreenInfo(): PlatformScreenInfo {
    return AndroidScreenInfo(
        screenHeightDp = getWindowHeightInDpCorrectly(),
        screenWidthDp = getWindowWidthInDpCorrectly()
    )
}