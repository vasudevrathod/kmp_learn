package com.vaasudev.kmptest._global.controller

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun BlurBackground(
    modifier: Modifier = Modifier,
    // You can add more common parameters here like blur radius
)