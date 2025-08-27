package com.vaasudev.kmptest.presentation.user_info

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.vaasudev.kmptest.domain.model.PersonalInfo
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun DetailsItem(personalInfo: PersonalInfo, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(0.dp))
            .clickable(
                onClick = onClick,
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = personalInfo.title,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = fontFamily(font = Fonts.UBUNTU_MEDIUM),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )
            Text(
                text = personalInfo.info,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = fontFamily(font = Fonts.LIGHT),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F),
                ),
            )
        }
        Icon(
            modifier = Modifier
                .size(20.dp)
                .clip(shape = CircleShape),
            painter = painterResource(Res.drawable.ic_arrow_forward),
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3F)
        )
    }
}