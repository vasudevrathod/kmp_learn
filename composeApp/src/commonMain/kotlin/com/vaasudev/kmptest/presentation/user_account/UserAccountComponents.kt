package com.vaasudev.kmptest.presentation.user_account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.AccountSettings
import com.vaasudev.kmptest.domain.model.QuickAccess
import com.vaasudev.kmptest.domain.model.getServicesData
import com.vaasudev.kmptest.domain.model.myQuickAccess
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.ic_arrow_forward
import com.vaasudev.kmptest.library.resources.ic_star_fill
import org.jetbrains.compose.resources.painterResource

@Composable
fun UserNameRating(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
        ) {
            Text(
                modifier = Modifier,
                text = userName,
                style = MaterialTheme.typography.displaySmall.copy(
                    fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )

            val idRating = "ic_rating"

            val text = buildAnnotatedString {
                appendInlineContent(idRating, "[image]")
                append(" ")
                append("5.0")
            }

            val inlineContent = mapOf(
                idRating to InlineTextContent(
                    placeholder = Placeholder(
                        width = 15.sp,
                        height = 15.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                    )
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_star_fill), // your image here
                        contentDescription = "Verified",
                        modifier = Modifier
                            .size(15.dp)
                    )
                }
            )

            Text(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainer, shape = RoundedCornerShape(5.dp))
                    .padding(3.dp),
                text = text,
                inlineContent = inlineContent,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = fontFamily(font = Fonts.REGULAR),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )
        }
    }
}

@Composable
fun QuickAccess(onClick: (QuickAccess) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(myQuickAccess.size) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .background(
                        shape = RoundedCornerShape(15.dp),
                        color = MaterialTheme.colorScheme.surfaceContainer
                    )
                    .clip(shape = RoundedCornerShape(15.dp))
                    .clickable(
                        onClick = {
                            onClick.invoke(myQuickAccess[index])
                        },
                        indication = ripple(),
                        interactionSource = remember { MutableInteractionSource() }
                    )
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(20.dp),
                    painter = painterResource(myQuickAccess[index].icon),
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = myQuickAccess[index].title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = fontFamily(font = Fonts.MEDIUM),
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                )
            }

        }
    }
}

@Composable
fun AccountSettingsItem(data: AccountSettings, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.5F))
            .clickable(
                onClick = onClick,
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 15.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(15.dp),
            painter = painterResource(data.icon),
            contentDescription = "Radio Selection",
            tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(15.dp))

        Box(
            modifier = Modifier
                .width(1.dp)
                .height(25.dp)
                .background(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3F))
        )

        Spacer(modifier = Modifier.width(15.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            text = data.title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = fontFamily(font = Fonts.LIGHT),
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )

        Icon(
            modifier = Modifier
                .size(15.dp),
            painter = painterResource(Res.drawable.ic_arrow_forward),
            contentDescription = "Radio Selection",
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3F)
        )
    }
}