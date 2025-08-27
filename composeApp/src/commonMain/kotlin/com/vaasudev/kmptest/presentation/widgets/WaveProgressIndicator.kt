package com.vaasudev.kmptest.presentation.widgets

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.sin

@Composable
fun WaveProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    height: androidx.compose.ui.unit.Dp = 8.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = EaseInOutCubic)
    )

    val waveOffset by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 2 * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(height / 2))
    ) {
        val width = size.width
        val height = size.height

        // Background
        drawRect(backgroundColor, size = size)

        // Wave progress
        val progressWidth = width * animatedProgress
        val amplitude = height * 0.3f
        val frequency = 0.02f

        val path = Path().apply {
            moveTo(0f, height / 2)

            for (x in 0..progressWidth.toInt()) {
                val y = height / 2 + amplitude * sin(frequency * x + waveOffset)
                lineTo(x.toFloat(), y)
            }

            lineTo(progressWidth, height)
            lineTo(0f, height)
            close()
        }

        drawPath(path, color)
    }
}

@Composable
fun PulseProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    height: androidx.compose.ui.unit.Dp = 8.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = EaseInOutCubic)
    )

    val pulseScale by rememberInfiniteTransition().animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(height / 2))
    ) {
        val width = size.width
        val height = size.height

        // Background
        drawRect(backgroundColor, size = size)

        // Pulsing progress
        val progressWidth = width * animatedProgress
        val pulseHeight = height * pulseScale
        val yOffset = (height - pulseHeight) / 2

        drawRect(
            color = color,
            topLeft = Offset(0f, yOffset),
            size = androidx.compose.ui.geometry.Size(progressWidth, pulseHeight)
        )
    }
}

@Composable
fun GradientProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    startColor: Color = MaterialTheme.colorScheme.primary,
    endColor: Color = MaterialTheme.colorScheme.secondary,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    height: androidx.compose.ui.unit.Dp = 8.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = EaseInOutCubic)
    )

    val shimmerOffset by rememberInfiniteTransition().animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(height / 2))
    ) {
        val width = size.width
        val height = size.height

        // Background
        drawRect(backgroundColor, size = size)

        // Gradient progress with shimmer
        val progressWidth = width * animatedProgress
        val shimmerWidth = width * 0.3f
        val shimmerX = shimmerOffset * (width + shimmerWidth) - shimmerWidth / 2

        val gradient = Brush.linearGradient(
            colors = listOf(
                startColor,
                endColor,
                startColor.copy(alpha = 0.7f),
                Color.White.copy(alpha = 0.8f),
                startColor.copy(alpha = 0.7f),
                endColor,
                startColor
            ),
            start = Offset(shimmerX, 0f),
            end = Offset(shimmerX + shimmerWidth, 0f)
        )

        drawRect(
            brush = gradient,
            topLeft = Offset(0f, 0f),
            size = androidx.compose.ui.geometry.Size(progressWidth, height)
        )
    }
}

@Composable
fun BouncingDotsProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    height: androidx.compose.ui.unit.Dp = 8.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = EaseInOutCubic)
    )

    val bounceOffset by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 2 * PI.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(height / 2))
    ) {
        val width = size.width
        val height = size.height

        // Background
        drawRect(backgroundColor, size = size)

        // Bouncing dots progress
        val progressWidth = width * animatedProgress
        val dotRadius = height * 0.2f
        val dotSpacing = dotRadius * 4
        val dotsCount = (progressWidth / dotSpacing).toInt()

        for (i in 0 until dotsCount) {
            val x = i * dotSpacing + dotRadius
            val bounceY = height / 2 + height * 0.2f * sin(bounceOffset + i * 0.5f)

            drawCircle(
                color = color,
                radius = dotRadius,
                center = Offset(x, bounceY)
            )
        }
    }
}

@Composable
fun StripedProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    height: androidx.compose.ui.unit.Dp = 8.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = EaseInOutCubic)
    )

    val stripeOffset by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 20f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(height / 2))
    ) {
        val width = size.width
        val height = size.height

        // Background
        drawRect(backgroundColor, size = size)

        // Base progress
        val progressWidth = width * animatedProgress
        drawRect(
            color = color,
            topLeft = Offset(0f, 0f),
            size = androidx.compose.ui.geometry.Size(progressWidth, height)
        )

        // Animated stripes
        val stripeWidth = 10f
        val stripeSpacing = 20f

        for (i in 0..((progressWidth / stripeSpacing).toInt() + 2)) {
            val x = i * stripeSpacing + stripeOffset
            if (x < progressWidth) {
                drawRect(
                    color = color.copy(alpha = 0.3f),
                    topLeft = Offset(x, 0f),
                    size = androidx.compose.ui.geometry.Size(stripeWidth, height)
                )
            }
        }
    }
}

@Composable
fun GlowProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    height: androidx.compose.ui.unit.Dp = 8.dp
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(1000, easing = EaseInOutCubic)
    )

    val glowIntensity by rememberInfiniteTransition().animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        )
    )

    Canvas(
        modifier = modifier
            .height(height)
            .clip(RoundedCornerShape(height / 2))
    ) {
        val width = size.width
        val height = size.height

        // Background
        drawRect(backgroundColor, size = size)

        // Glowing progress
        val progressWidth = width * animatedProgress

        // Glow effect
        val glowBrush = Brush.radialGradient(
            colors = listOf(
                color.copy(alpha = glowIntensity),
                color.copy(alpha = glowIntensity * 0.5f),
                Color.Transparent
            ),
            center = Offset(progressWidth, height / 2),
            radius = height * 2
        )

        drawRect(
            color = color,
            topLeft = Offset(0f, 0f),
            size = androidx.compose.ui.geometry.Size(progressWidth, height)
        )

        // Add glow at the end
        if (progressWidth > 0) {
            drawRect(
                brush = glowBrush,
                topLeft = Offset(0f, 0f),
                size = androidx.compose.ui.geometry.Size(progressWidth + height * 2, height)
            )
        }
    }
}

//@Preview
@Composable
fun CustomProgressIndicatorsPreview() {
    var progress by remember { mutableStateOf(0.6f) }

    LaunchedEffect(Unit) {
        while (true) {
            progress = (progress + 0.1f) % 1f
            kotlinx.coroutines.delay(1000)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Wave Progress")
        WaveProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )

        Text("Pulse Progress")
        PulseProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )

        Text("Gradient Progress")
        GradientProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )

        Text("Bouncing Dots Progress")
        BouncingDotsProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )

        Text("Striped Progress")
        StripedProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )

        Text("Glow Progress")
        GlowProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxWidth()
        )
    }
}