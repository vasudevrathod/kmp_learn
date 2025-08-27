package com.vaasudev.kmptest.presentation.trip_details

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.ic_arrow_forward
import com.vaasudev.kmptest.library.resources.ic_info
import com.vaasudev.kmptest.library.resources.ic_note
import com.vaasudev.kmptest.library.resources.ic_star_fill
import com.vaasudev.kmptest.library.resources.ic_wallet
import com.vaasudev.kmptest.library.resources.ride_moto
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun RiderTypeName(
    rideIcon: DrawableResource,
    rideType: String,
    driverName: String,
    rideStatusId: Int,
    cancelTripFee: String
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(15.dp))
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.5F)
            )
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            text = buildAnnotatedString {
                append(rideType)
                withStyle(
                    style = SpanStyle(
                        fontFamily = fontFamily(font = Fonts.UBUNTU_LIGHT),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                ) {
                    if (rideStatusId != 4 ) {
                        append(" ride with ")
                    } else {
                        append(" (₹ $cancelTripFee cancel fee) ")
                    }
                }
                if (rideStatusId != 4 ) {
                    append(driverName)
                } else {
                    append("trip")
                }
            },
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD),
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )

        Image(
            modifier = Modifier
                .size(60.dp)
                .aspectRatio(1F),
            painter = painterResource(rideIcon),
            contentDescription = null
        )
    }
}

@Composable
fun DateTimePrice(value: String) {
    Text(
        modifier = Modifier
            .padding(horizontal = 15.dp),
        text = value,
        style = MaterialTheme.typography.titleLarge.copy(
            fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD),
            color = MaterialTheme.colorScheme.onSurface,
        ),
    )
}

@Composable
fun RoundedIconTextButton(icon: DrawableResource, title: String) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .clickable(
                onClick = {},
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            )
            .padding(horizontal = 10.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            modifier = Modifier,
            text = title,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontFamily = fontFamily(font = Fonts.SEMI_BOLD),
                color = MaterialTheme.colorScheme.onSurface,
            ),
        )

    }
}

@Composable
fun ReceiptInvoice() {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp)
    ) {
        RoundedIconTextButton(
            icon = Res.drawable.ic_note,
            title = "Receipt"
        )
        Spacer(modifier = Modifier.width(15.dp))
        RoundedIconTextButton(
            icon = Res.drawable.ic_note,
            title = "Invoice"
        )
    }
}

@Composable
fun LocationDetails(pickUpLocation: String, pickUpTime: String, dropOffLocation: String, dropOffTime: String) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .border(width = 1.dp, color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 1F), shape = RoundedCornerShape(10.dp))
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onSurface)
                )

                VerticalDottedLine(
                    modifier = Modifier
                        .weight(1F),
                    color = MaterialTheme.colorScheme.onSurface,
                    strokeWidth = 2.dp,
                    dotInterval = 8.dp,
                    dotSize = 6.dp // Creates a dashed effect
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Row(
                    modifier = Modifier
                ) {
                    Text(
                        text = "Pick-UP",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = fontFamily(font = Fonts.BOLD),
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                    )

                    Text(
                        text = " - ($pickUpTime)",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = fontFamily(font = Fonts.LIGHT),
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .fillMaxWidth(),
                    text = pickUpLocation,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = fontFamily(font = Fonts.REGULAR),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                )
            }
        }

        Row {
            Column {
                Box(
                    modifier = Modifier
                        .padding(top = 2.dp)
                        .size(10.dp)
                        .background(MaterialTheme.colorScheme.onSurface)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Row {
                    Text(
                        text = "Drop-Off",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = fontFamily(font = Fonts.BOLD),
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                    )

                    Text(
                        text = " - ($dropOffTime)",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontFamily = fontFamily(font = Fonts.LIGHT),
                            color = MaterialTheme.colorScheme.onSurface,
                        ),
                    )
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = dropOffLocation,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = fontFamily(font = Fonts.REGULAR),
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                )
            }
        }
    }
}

@Composable
fun VerticalDottedLine(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    strokeWidth: Dp = 1.dp,
    dotInterval: Dp = 4.dp,
    dotSize: Dp = 0.dp // Use 0 for a circular dot with StrokeCap.Round
) {
    val pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(dotSize.value, dotInterval.value), 0f
    )

    Canvas(
        modifier = modifier
            .fillMaxHeight()
            .width(strokeWidth)
    ) {
        drawLine(
            color = color,
            start = Offset(x = size.width / 2, y = 0f),
            end = Offset(x = size.width / 2, y = size.height),
            strokeWidth = strokeWidth.toPx(),
            pathEffect = pathEffect
        )
    }
}

@Composable
fun TipsRating(tipped: String, rating: String) {

    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.5F))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TipsRatingView(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            icon = Res.drawable.ic_wallet,
            title = "Tipped $ $tipped",
            isRating = false
        )

        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight(),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3F)
        )

        TipsRatingView(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            icon = Res.drawable.ic_star_fill,
            title = "Rated $rating",
            isRating = true
        )
    }
}

@Composable
fun TipsRatingView(modifier: Modifier, isRating: Boolean, icon: DrawableResource, title: String) {

    val textStyle: TextStyle = MaterialTheme.typography.bodyLarge.copy(
        fontFamily = fontFamily(font = Fonts.BOLD),
        color = MaterialTheme.colorScheme.onSurface,
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(25.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary.copy(alpha = 1F)
        )

        Spacer(modifier = Modifier.height(5.dp))

        if (isRating) {
            val idRating = "ic_rating"

            val text = buildAnnotatedString {
                append(title)
                append(" ")
                appendInlineContent(idRating, "[image]")
            }

            val inlineContent = mapOf(
                idRating to InlineTextContent(
                    placeholder = Placeholder(
                        width = textStyle.fontSize,
                        height = textStyle.fontSize,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                    )
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_star_fill), // your image here
                        contentDescription = "Verified",
                    )
                }
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = text,
                inlineContent = inlineContent,
                style = textStyle,
                textAlign = TextAlign.Center
            )
        } else {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = title,
                style = textStyle,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CustomerSupport() {
    Row(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .clickable(
                onClick = {},
                indication = ripple(color = MaterialTheme.colorScheme.surfaceContainer),
                interactionSource = remember { MutableInteractionSource() },
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .size(30.dp)
                .padding(5.dp),
            painter = painterResource(Res.drawable.ic_info),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            text = "Customer Support",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = fontFamily(font = Fonts.REGULAR),
                color = MaterialTheme.colorScheme.onPrimary,
            ),

        )

        Icon(
            modifier = Modifier
                .size(30.dp)
                .clip(shape = CircleShape)
                .background(color = MaterialTheme.colorScheme.surfaceContainer)
                .padding(5.dp),
            painter = painterResource(Res.drawable.ic_arrow_forward),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CancelledTag() {
    Text(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = MaterialTheme.colorScheme.error)
            .padding(horizontal = 5.dp, vertical = 3.dp),
        text = "Cancelled",
        style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = fontFamily(font = Fonts.REGULAR),
            color = MaterialTheme.colorScheme.onError,
        ),

        )
}