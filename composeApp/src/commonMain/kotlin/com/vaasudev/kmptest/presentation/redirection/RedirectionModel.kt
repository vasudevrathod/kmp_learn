package com.vaasudev.kmptest.presentation.redirection

import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.DrawableResource

data class RedirectionModel(
    val id: Int = 0,
    val title: String = "",
    val icon: DrawableResource = Res.drawable.ic_currency_rupee,
)
