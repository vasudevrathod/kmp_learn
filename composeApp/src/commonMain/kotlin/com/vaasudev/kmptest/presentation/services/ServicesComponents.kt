package com.vaasudev.kmptest.presentation.services

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.ServicesOffer
import com.vaasudev.kmptest.presentation.sign_in.ButtonCustom
import org.jetbrains.compose.resources.painterResource
import kotlin.math.absoluteValue

@Composable
fun ServiceOffersItem(data: ServicesOffer) {
    Card(modifier = Modifier
        .fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = data.bgColor
        )
    ) {
        Row {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1F),
                painter = painterResource(data.image),
                contentDescription = "offer",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp),
                    text = data.offer,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD),
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                )
                ButtonCustom(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    title = "Book Now",
                    onClick = {

                    },
                    verticalPadding = 10.dp
                )
            }
        }
    }
}