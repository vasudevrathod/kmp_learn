package com.vaasudev.kmptest.presentation.user_gender

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import com.vaasudev.kmptest.domain.model.Gender
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.ic_radio_check
import com.vaasudev.kmptest.library.resources.ic_radio_uncheck
import org.jetbrains.compose.resources.painterResource

@Composable
fun GenderItem(gender: Gender, isSelected: Boolean = false, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(0.dp))
            .clickable(
                onClick = onClick,
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 10.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            text = gender.title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = fontFamily(font = Fonts.UBUNTU_MEDIUM),
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )
        Icon(
            modifier = Modifier
                .size(20.dp)
                .clip(shape = CircleShape),
            painter = if (isSelected) painterResource(Res.drawable.ic_radio_check) else painterResource(Res.drawable.ic_radio_uncheck),
            contentDescription = "Radio Selection",
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}