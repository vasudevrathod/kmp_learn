package com.vaasudev.kmptest._global.controller

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import platform.UIKit.UIBlurEffect
import platform.UIKit.UIBlurEffectStyle
import platform.UIKit.UIVisualEffectView

@Composable
actual fun BlurBackground(modifier: Modifier) {
    UIKitView(
        factory = {
            val blurEffect = UIBlurEffect.effectWithStyle(UIBlurEffectStyle.UIBlurEffectStyleSystemMaterial)
            UIVisualEffectView(effect = blurEffect)
        },
        modifier = modifier
    )
}