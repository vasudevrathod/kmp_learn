package com.vaasudev.kmptest.domain.model

import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.DrawableResource

data class AccountSettings(
    val id: Int,
    val title: String,
    val icon: DrawableResource
)

val myAccountSettings = listOf(
    AccountSettings(1, "Settings", Res.drawable.ic_settings),
    AccountSettings(2, "Simple mode", Res.drawable.ic_phone),
    AccountSettings(3, "Rider Insurance", Res.drawable.ic_umbrella),
    AccountSettings(4, "Messages", Res.drawable.ic_email),
    AccountSettings(5, "Send a gift", Res.drawable.ic_gift),
    AccountSettings(6, "Earn by driving or delivering", Res.drawable.ic_settings),
    AccountSettings(7, "Saved groups", Res.drawable.ic_people),
    AccountSettings(8, "Set up your business profile", Res.drawable.ic_business),
    AccountSettings(9, "Manage SafarCab account", Res.drawable.ic_person),
    AccountSettings(10, "Legal", Res.drawable.ic_info),
)
