package com.vaasudev.kmptest.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun HeaderCommon(title: String, onBackClick: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceContainer)
            .padding(top = 30.dp)
            .padding(horizontal = 15.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(35.dp)
                .clip(shape = CircleShape)
                .clickable(
                    onClick = onBackClick,
                    indication = ripple(),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(5.dp),
            painter = painterResource(Res.drawable.ic_arrow_back),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onSurface
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = fontFamily(font = Fonts.SEMI_BOLD),
                color = MaterialTheme.colorScheme.onSurface,
            ),
            maxLines = 1,
            overflow = TextOverflow.Clip,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .size(35.dp)
        )
    }
}

@Composable
fun AuthTitle(title: String) {
    Text(
        modifier = Modifier
            .padding(horizontal = 15.dp),
        text = title,
        style = MaterialTheme.typography.titleLarge.copy(
            fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD),
            color = MaterialTheme.colorScheme.onSurface,
        ),
    )
}