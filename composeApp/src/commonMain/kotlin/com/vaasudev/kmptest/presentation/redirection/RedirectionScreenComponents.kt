package com.vaasudev.kmptest.presentation.redirection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(InternalResourceApi::class)
@Composable
fun RedirectionItemView(data: RedirectionModel, onItemClick: (data: RedirectionModel) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(20.dp)
            )
            .clip(shape = RoundedCornerShape(20.dp))
            .clickable(
                onClick = {
                    onItemClick.invoke(data)
                },
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .clip(shape = RoundedCornerShape(20.dp))
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically,
        ) {

        Image(
            modifier = Modifier
                .size(25.dp),
            painter = painterResource(data.icon),
            contentDescription = "Button",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )

        Spacer(modifier = Modifier.width(15.dp))

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(25.dp)
                .background(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3F)
                )
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            modifier = Modifier
                .weight(1F),
            text = data.title,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontFamily = fontFamily(font = Fonts.REGULAR),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.width(10.dp))

        Image(
            modifier = Modifier
                .size(17.dp),
            painter = painterResource(Res.drawable.ic_arrow_forward),
            contentDescription = "Button",
            colorFilter = ColorFilter.tint(
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }
}

@Preview
@Composable
fun RedirectionItemViewPreview() {
    RedirectionItemView(
        data =
            RedirectionModel(
                id = 1,
                title = "Redirection",
                icon = Res.drawable.ic_language
            ),
        onItemClick = { data ->

        }
    )
}