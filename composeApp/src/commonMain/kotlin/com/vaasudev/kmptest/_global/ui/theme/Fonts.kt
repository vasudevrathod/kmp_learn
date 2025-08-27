package com.vaasudev.kmptest._global.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.Font

// WEIGHT 100 - THIN
// WEIGHT 200 - EXTRA_LIGHT
// WEIGHT 300 - LIGHT
// WEIGHT 400 - REGULAR
// WEIGHT 500 - MEDIUM
// WEIGHT 600 - SEMI_BOLD
// WEIGHT 700 - BOLD
// WEIGHT 800 - EXTRA_BOLD
// WEIGHT 900 - BLACK

enum class Fonts {
    THIN,
    EXTRA_LIGHT,
    LIGHT,
    REGULAR,
    MEDIUM,
    SEMI_BOLD,
    BOLD,
    EXTRA_BOLD,
    BLACK,
    UBUNTU_LIGHT,
    UBUNTU_REGULAR,
    UBUNTU_MEDIUM,
    UBUNTU_BOLD,
}

@Composable
fun fontFamily(font: Fonts): FontFamily {
    return when (font) {
        Fonts.THIN -> FontFamily(Font(Res.font.font_thin, FontWeight.Thin))
        Fonts.EXTRA_LIGHT -> FontFamily(Font(Res.font.font_extra_light, FontWeight.ExtraLight))
        Fonts.LIGHT -> FontFamily(Font(Res.font.font_light, FontWeight.Light))
        Fonts.REGULAR -> FontFamily(Font(Res.font.font_regular, FontWeight.Normal))
        Fonts.MEDIUM -> FontFamily(Font(Res.font.font_medium, FontWeight.Medium))
        Fonts.SEMI_BOLD -> FontFamily(Font(Res.font.font_semi_bold, FontWeight.SemiBold))
        Fonts.BOLD -> FontFamily(Font(Res.font.font_bold, FontWeight.Bold))
        Fonts.EXTRA_BOLD -> FontFamily(Font(Res.font.font_extra_bold, FontWeight.ExtraBold))
        Fonts.BLACK -> FontFamily(Font(Res.font.font_black, FontWeight.Black))
        Fonts.UBUNTU_LIGHT -> FontFamily(Font(Res.font.ubuntu_light, FontWeight.Light))
        Fonts.UBUNTU_REGULAR -> FontFamily(Font(Res.font.ubuntu_regular, FontWeight.Normal))
        Fonts.UBUNTU_MEDIUM -> FontFamily(Font(Res.font.ubuntu_medium, FontWeight.Medium))
        Fonts.UBUNTU_BOLD -> FontFamily(Font(Res.font.ubuntu_bold, FontWeight.Bold))
    }
}

