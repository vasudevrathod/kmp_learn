package com.vaasudev.kmptest.presentation.wallet

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.PaymentMethods
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.ic_arrow_forward
import org.jetbrains.compose.resources.painterResource

@Composable
fun PaymentMethodsItem(data: PaymentMethods, onClick: (data: PaymentMethods) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 15.dp,
            )
            .clickable(
                onClick = {
                    onClick.invoke(data)
                },
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(vertical = 15.dp, horizontal = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(data.icon),
            contentDescription = null
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .padding(horizontal = 15.dp),
            text = buildAnnotatedString {
                append(data.name)
                if (data.isDefault) {
                    append(" - ")
                    withStyle(
                        style = SpanStyle(
                            fontFamily = fontFamily(font = Fonts.BOLD),
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("Default")
                    }
                }
            },
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = fontFamily(font = Fonts.REGULAR),
                color = MaterialTheme.colorScheme.onSurface,
            )
        )

        Icon(
            modifier = Modifier.size(15.dp),
            painter = painterResource(Res.drawable.ic_arrow_forward),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F)
        )
    }
}