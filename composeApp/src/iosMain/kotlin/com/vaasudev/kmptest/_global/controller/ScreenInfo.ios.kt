package com.vaasudev.kmptest._global.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGFloat
import platform.CoreGraphics.CGSize
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun getScreenHeightDp(): Dp {
    val height = UIScreen.mainScreen.bounds.useContents{size}.height.toFloat()
    return height.dp
}

class IosScreenInfo(
    override val screenHeightDp: Dp,
    override val screenWidthDp: Dp
) : PlatformScreenInfo

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun rememberPlatformScreenInfo(): PlatformScreenInfo {
    return remember {
        val screen = UIScreen.mainScreen
        val bounds = screen.bounds
        val screenSize: CGSize = bounds.useContents { size }
        // val scale = screen.scale.toFloat() // Device pixel ratio (useful if converting from pixels)

        // Convert CGFloat to Float, then to Dp
        val screenHeight: CGFloat = screenSize.height
        val screenWidth: CGFloat = screenSize.width

        IosScreenInfo(
            screenHeightDp = screenHeight.toFloat().dp,
            screenWidthDp = screenWidth.toFloat().dp
        )
    }
}