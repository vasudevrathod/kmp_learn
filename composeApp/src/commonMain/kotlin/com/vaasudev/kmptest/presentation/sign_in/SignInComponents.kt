package com.vaasudev.kmptest.presentation.sign_in

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily

@Composable
fun ButtonCustom(
    title: String,
    onClick: () -> Unit,
    style: TextStyle = MaterialTheme.typography.titleMedium.copy(
        fontFamily = fontFamily(font = Fonts.REGULAR),
        color = MaterialTheme.colorScheme.onPrimary,
        fontSize = 14.sp
    ),
    enabled: Boolean = true,
    shape: Shape = RoundedCornerShape(10.dp),
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    horizontalPadding: Dp = 15.dp,
    verticalPadding: Dp = 15.dp,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = shape)
            .clip(shape = shape)
            .clickable(
                onClick = onClick,
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() },
                enabled = enabled
            )
            .padding(vertical = verticalPadding, horizontal = horizontalPadding),
        text = title,
        style = style,
        textAlign = TextAlign.Center
    )
}

@Composable
fun OrLine() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            modifier = Modifier
                .padding(horizontal = 5.dp),
            text = "or",
            style = MaterialTheme.typography.bodySmall.copy(
                fontFamily = fontFamily(
                    font = Fonts.REGULAR
                ),
                color = MaterialTheme.colorScheme.onSecondary
            )
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}