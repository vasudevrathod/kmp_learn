package com.vaasudev.kmptest._global.controller

import platform.Foundation.NSDecimalNumber
import platform.Foundation.NSDecimalNumberHandler
import platform.Foundation.NSRoundingMode

actual fun roundToTwoDecimalPlaces(amount: Double): Float {
    val handler = NSDecimalNumberHandler(
        roundingMode = NSRoundingMode.NSRoundPlain, // Equivalent to HALF_UP
        scale = 2,
        raiseOnExactness = false,
        raiseOnOverflow = false,
        raiseOnUnderflow = false,
        raiseOnDivideByZero = false
    )
    val decimalNumber = NSDecimalNumber(double = amount)
    return decimalNumber.decimalNumberByRoundingAccordingToBehavior(handler).floatValue()
}