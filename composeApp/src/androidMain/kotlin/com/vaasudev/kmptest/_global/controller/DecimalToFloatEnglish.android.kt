package com.vaasudev.kmptest._global.controller

import java.math.BigDecimal
import java.math.RoundingMode

actual fun roundToTwoDecimalPlaces(amount: Double): Float {
    val roundedFloat = BigDecimal(amount)
        .setScale(2, RoundingMode.HALF_UP)
        .toFloat()
    return roundedFloat
}