package com.vaasudev.kmptest.presentation.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import kotlinx.coroutines.delay
import kotlin.random.Random

data class Raindrop(
    var x: Float,
    var y: Float,
    var ySpeed: Float,
    var length: Float
)

@Composable
fun RainAnimation(modifier: Modifier = Modifier) {
    val raindrops = remember { mutableStateListOf<Raindrop>() }

    LaunchedEffect(Unit) {
        while (true) {
            raindrops.add(
                Raindrop(
                    x = Random.nextFloat(),
                    y = -0.1f,
                    ySpeed = Random.nextFloat() * 0.01f + 0.005f,
                    length = Random.nextFloat() * 20f + 10f
                )
            )
            delay(10)
        }
    }

    val color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)

    Canvas(modifier = modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        val updatedRaindrops = raindrops.map {
            it.y += it.ySpeed
            it
        }.filter { it.y < 1.1f } // Remove raindrops that are off-screen

        raindrops.clear()
        raindrops.addAll(updatedRaindrops)

        raindrops.forEach {
            drawLine(
                color = color,
                start = Offset(it.x * width, it.y * height),
                end = Offset(it.x * width, (it.y * height) + it.length),
                strokeWidth = 2f
            )
        }
    }
}