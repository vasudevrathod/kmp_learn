package com.vaasudev.kmptest._global.controller

import platform.Foundation.NSString
import platform.Foundation.stringWithFormat

actual fun formatStringEnglish(value: Float): String {
    return NSString.stringWithFormat(format = "%.2f", value)
}