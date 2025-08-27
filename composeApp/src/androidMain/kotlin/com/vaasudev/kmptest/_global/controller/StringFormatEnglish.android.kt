package com.vaasudev.kmptest._global.controller

import java.util.Locale

actual fun formatStringEnglish(value: Float): String {
    return String.format(Locale.ENGLISH, "%.2f", value)
}