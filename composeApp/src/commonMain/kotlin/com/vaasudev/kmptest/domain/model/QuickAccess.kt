package com.vaasudev.kmptest.domain.model


import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.DrawableResource

data class QuickAccess(
    val id: Int,
    val title: String,
    val icon: DrawableResource
)

val myQuickAccess = listOf(
    QuickAccess(
        id = 1,
        title = "Help",
        icon = Res.drawable.ic_help
    ),
    QuickAccess(
        id = 2,
        title = "Wallet",
        icon = Res.drawable.ic_wallet
    ),
    QuickAccess(
        id = 3,
        title = "Activity",
        icon = Res.drawable.ic_local_activity
    ),
)
