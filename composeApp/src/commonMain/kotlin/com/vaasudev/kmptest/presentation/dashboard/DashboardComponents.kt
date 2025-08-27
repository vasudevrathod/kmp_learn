package com.vaasudev.kmptest.presentation.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.bottomNavigationItemList
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeBottomNavigation(onClick: (type: Int) -> Unit, selectedTab: Int=0) {

    Column {
        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(bottom = 20.dp)
                .height(60.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomNavigationItemList.forEachIndexed { index, item ->
                if (item.isCenterIcon) {
                    Image(
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .size(50.dp)
                            .clip(shape = CircleShape)
                            .clickable(
                                onClick = {
                                    onClick.invoke(index)
                                },
                                indication = ripple(),
                                interactionSource = remember { MutableInteractionSource() }
                            ),
                        painter = painterResource(if (selectedTab == index) item.iconSelected else item.iconUnselected),
                        contentDescription = "Button",
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .clickable(
                                onClick = {
                                    if (selectedTab != index) {
                                        onClick.invoke(index)
                                    }
                                },
                                indication = ripple(),
                                interactionSource = remember { MutableInteractionSource() }
                            )
                            .weight(1F),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            modifier = Modifier
                                .size(20.dp),
                            painter = painterResource(if (selectedTab == index) item.iconSelected else item.iconUnselected),
                            contentDescription = "Button",
                        )

                        Spacer(modifier = Modifier.height(0.dp))

                        Text(
                            text = item.title,
                            modifier = Modifier,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = fontFamily(font = Fonts.UBUNTU_REGULAR),
                                color = if (selectedTab == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(alpha = 0.4F),
                            )
                        )
                    }
                }
            }
        }

    }


}