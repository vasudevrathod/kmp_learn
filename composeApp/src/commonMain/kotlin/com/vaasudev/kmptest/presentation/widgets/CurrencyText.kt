package com.vaasudev.kmptest.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.controller.formatStringEnglish
import com.vaasudev.kmptest._global.controller.roundToTwoDecimalPlaces
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily

@Composable
fun CurrencyText(modifier: Modifier = Modifier, currency: String = "$", amount: Any) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            currency,
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD)),
            )
        Spacer(Modifier.width(10.dp))
        Text(
            convertAmount(amount).toString(),
            style = MaterialTheme.typography.headlineLarge.copy(fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD)),
        )
    }

}

fun convertAmount(amount: Any?): Float {
    amount?.let {
        return when (amount) {
            is String -> {
                if (amount.isNotEmpty()) {
                    val formated = formatStringEnglish(amount.toFloat())
                    formated.toFloat()
                } else {
                    0.0F
                }
            }

            is Float -> {
                val formated = formatStringEnglish(amount.toFloat())
                formated.toFloat()
            }

            is Int -> {
                val formatted = formatStringEnglish(amount.toFloat())
                formatted.toFloat()
            }

            is Double -> {
                val roundedFloat = roundToTwoDecimalPlaces(amount)
                roundedFloat
            }

            else ->  0.0F
        }
    }

    return 0.0F
}