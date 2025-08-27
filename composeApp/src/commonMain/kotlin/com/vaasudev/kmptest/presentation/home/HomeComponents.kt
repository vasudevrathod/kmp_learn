package com.vaasudev.kmptest.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.Services
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchTextField() {
    BasicTextField(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(40.dp),
                color = MaterialTheme.colorScheme.surfaceContainer
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        value = TextFieldValue(""),
        onValueChange = {
            // it is crucial that the update is fed back into BasicTextField in order to
            // see updates on the text
        },
        enabled = false,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Enter Pick-Up Location",
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyMedium.copy(fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD))
                )
                innerTextField() // This draws the actual text field
            }
        }
    )
}

@Composable
fun RecentLocationItem(onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(
                shape = RoundedCornerShape(10.dp),
                color = MaterialTheme.colorScheme.surface
            )
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(10.dp)
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable(
                onClick = onClick,
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(10.dp),
                    color = MaterialTheme.colorScheme.surfaceContainer
                )
                .padding(8.dp),
            painter = painterResource(Res.drawable.ic_recent),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Café im Naturgarten",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium.copy(fontFamily = fontFamily(font = Fonts.MEDIUM))
            )
            Text(
                text = "Burggasse 10/11a, 99947 Bad Langensalza, Germany",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodySmall.copy(fontFamily = fontFamily(font = Fonts.REGULAR))
            )
        }
    }
}

@Composable
fun Suggestions(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Suggestions",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD),
                fontSize = 16.sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .clickable(
                    onClick = onClick,
                    indication = ripple(),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(horizontal = 10.dp),
            text = "See All",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD),
                fontSize = 12.sp
            ),
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun ServiceType(rideTypeList: List<Services>, onClick: () -> Unit, isAutoManage: Boolean = true) {

    val rideTypesSize =
        if (isAutoManage) rideTypeList.size else (rideTypeList.size + (4 - rideTypeList.size))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        repeat(rideTypesSize) { index ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!isAutoManage && index >= rideTypeList.size) {
                    Spacer(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .aspectRatio(1F)
                    )
                } else {
                    Box(
                    ) {

                        Box(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .aspectRatio(1F)
                                .background(
                                    shape = RoundedCornerShape(20.dp),
                                    color = MaterialTheme.colorScheme.surfaceContainer
                                )
                                .clip(shape = RoundedCornerShape(20.dp))
                                .clickable(
                                    onClick = onClick,
                                    indication = ripple(),
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                        ) {
                            Image(
                                modifier = Modifier
                                    .aspectRatio(1F)
                                    .padding(10.dp),
                                painter = painterResource(rideTypeList[index].icon),
                                contentDescription = null
                            )
                        }

                        if (rideTypeList[index].isHavePromo) {
                            Text(
                                modifier = Modifier
                                    .background(
                                        color = MaterialTheme.colorScheme.primary,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .padding(3.dp)
                                    .align(Alignment.TopCenter),
                                text = "Promo",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontFamily = fontFamily(font = Fonts.MEDIUM)
                                ),
                                color = MaterialTheme.colorScheme.onPrimary,
                            )
                        }
                    }

                    Text(
                        text = rideTypeList[index].title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = fontFamily(font = Fonts.SEMI_BOLD),
                            fontSize = 12.sp
                        ),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }

        }
    }
}