package com.vaasudev.kmptest.presentation.trips_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.TripsListData
import com.vaasudev.kmptest.domain.utility.DateFormats
import com.vaasudev.kmptest.domain.utility.formatTimestampToDate
import org.jetbrains.compose.resources.painterResource

@Composable
fun TripsItem(data: TripsListData, onClick: (TripsListData) -> Unit) {

    val textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(
        fontFamily = fontFamily(font = Fonts.REGULAR),
        color = MaterialTheme.colorScheme.onSurface,
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.5F))
            .clickable(
                onClick = {
                    onClick.invoke(data)
                },
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(10.dp)
    ) {
        Image(
            modifier = Modifier
                .size(60.dp),
            painter = painterResource(data.serviceData.icon),
            contentDescription = null
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = data.dropOffLocation,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = fontFamily(font = Fonts.BOLD),
                    color = MaterialTheme.colorScheme.onSurface,
                ),)

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = formatTimestampToDate(data.bookDateTime, dateFormats = DateFormats.DD_MMM_HH_MM_AM_PM),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = fontFamily(font = Fonts.REGULAR),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4F),
                ),)

            val idRating = "ic_rating"

            val text = buildAnnotatedString {
                if (data.statusId == 4) {
                    append("$ 0.00")
                    append(" ")
                    appendInlineContent(idRating, "[image]")
                    append(" ")
                    append("Cancelled")
                } else {
                    append("$ ${data.tripPrice}")
                }
            }

            val inlineContent = mapOf(
                idRating to InlineTextContent(
                    placeholder = Placeholder(
                        width = textStyle.fontSize,
                        height = textStyle.fontSize,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                    )
                ) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                        .clip(shape = CircleShape)
                        .background(color = MaterialTheme.colorScheme.onSurface))
                }
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                inlineContent = inlineContent,
                style = textStyle,
                )
        }
    }
}