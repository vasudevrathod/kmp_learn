package com.vaasudev.kmptest.domain.model

import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.DrawableResource

data class DashboardBottomNavigation(
    val id: Int,
    val title: String,
    val iconSelected: DrawableResource,
    val iconUnselected: DrawableResource,
    val isCenterIcon: Boolean = false,
    val isSelected: Boolean,
)

val bottomNavigationItemList = listOf(
    DashboardBottomNavigation(
        id = 1,
        title = "Home",
        iconSelected = Res.drawable.ic_bottom_home_selected,
        iconUnselected = Res.drawable.ic_bottom_home,
        isSelected = true,
    ),
    DashboardBottomNavigation(
        id = 2,
        title = "Services",
        iconSelected = Res.drawable.ic_bottom_menu_selected,
        iconUnselected = Res.drawable.ic_bottom_menu,
        isSelected = false,
    ),
    DashboardBottomNavigation(
        id = 3,
        title = "Account",
        iconSelected = Res.drawable.ic_bottom_profile_selected,
        iconUnselected = Res.drawable.ic_bottom_profile,
        isSelected = false,
    ),
)
