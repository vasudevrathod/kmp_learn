package com.vaasudev.kmptest.presentation.widgets

import androidx.compose.animation.Animatable
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun GoogleLinearLoader(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary, // Google Blue
    strokeWidth: Dp = 1.dp,
    segmentLength: Float = 0.2f, // 20% of the total width
    animationDuration: Int = 1200 // milliseconds
) {
    val transition = rememberInfiniteTransition(label = "alternating-loader-transition")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = { it } // Linear easing for constant speed
            ),
            repeatMode = RepeatMode.Reverse // This makes it go back and forth
        ),
        label = "progress"
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(strokeWidth)
    ) {
        val canvasWidth = size.width
        val segmentWidthInPixels = canvasWidth * segmentLength.coerceIn(0f, 1f)

        // The total distance the line needs to travel is the canvas width plus its own width.
        // This ensures it starts completely off-screen and finishes completely off-screen.
        val totalTravelDistance = canvasWidth + segmentWidthInPixels

        // Calculate the start and end x-coordinates of the line segment
        val lineStartX = progress * totalTravelDistance - segmentWidthInPixels
        val lineEndX = progress * totalTravelDistance

        // Draw the active line segment
        drawLine(
            color = color,
            start = Offset(x = lineStartX, y = center.y),
            end = Offset(x = lineEndX, y = center.y),
            strokeWidth = strokeWidth.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun GoogleLinearLoader1(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary, // Google Blue
    strokeWidth: Dp = 1.dp,
    segmentLength: Float = 0.2f, // 20% of the total width
    animationDuration: Int = 1200 // milliseconds
) {
    val transition = rememberInfiniteTransition(label = "pulsating-loader-transition")
    val progress by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDuration,
                easing = LinearEasing // Ensures constant speed throughout the cycle
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "progress"
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(strokeWidth)
    ) {
        val canvasWidth = size.width
        val centerX = center.x
        val centerY = center.y

        val lineStartX: Float
        val lineEndX: Float

        if (progress < 0.5f) {
            // Phase 1: Expansion (from center to full width)
            val expansionProgress = progress * 2f // Map [0, 0.5] to [0, 1]
            lineStartX = centerX - (centerX * expansionProgress)
            lineEndX = centerX + (centerX * expansionProgress)
        } else {
            // Phase 2: Contraction (from full width to center)
            val contractionProgress = (progress - 0.5f) * 2f // Map [0.5, 1] to [0, 1]
            lineStartX = 0f + (centerX * contractionProgress)
            lineEndX = canvasWidth - (centerX * contractionProgress)
        }

        // Draw the active line segment
        drawLine(
            color = color,
            start = Offset(lineStartX, centerY),
            end = Offset(lineEndX, centerY),
            strokeWidth = strokeWidth.toPx(),
            cap = StrokeCap.Round
        )

        /*val maxSegmentWidth = canvasWidth * segmentLength.coerceIn(0f, 1f)

        val currentWidth: Float
        // Determine the current width based on the animation progress
        if (progress < 0.5f) {
            // Expanding phase (from 0% to 100% of max width)
            val expansionProgress = progress * 2
            currentWidth = maxSegmentWidth * expansionProgress
        } else {
            // Contracting phase (from 100% to 0% of max width)
            val contractionProgress = (progress - 0.5f) * 2
            currentWidth = maxSegmentWidth * (1 - contractionProgress)
        }

        val lineStartX = centerX - (currentWidth / 2)
        val lineEndX = centerX + (currentWidth / 2)

        // Draw the active line segment
        drawLine(
            color = color,
            start = Offset(x = lineStartX, y = center.y),
            end = Offset(x = lineEndX, y = center.y),
            strokeWidth = strokeWidth.toPx(),
            cap = StrokeCap.Round
        )*/
    }
}

@Composable
fun GoogleLinearLoader2(
    modifier: Modifier = Modifier,
    isAnimating: Boolean,
    color: Color = MaterialTheme.colorScheme.primary, // Google Blue
    strokeWidth: Dp = 1.dp,
    segmentLength: Float = 0.2f, // 20% of the total width
    animationDuration: Int = 1200 // milliseconds
) {
    val progress = remember { Animatable(0f) }

    // This effect runs the animation loop when isAnimating is true.
    // When isAnimating becomes false, the coroutine is cancelled, stopping the animation.
    LaunchedEffect(key1 = isAnimating) {
        if (isAnimating) {
            // Loop indefinitely while the effect is active.
            while (true) {
                progress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(
                        durationMillis = animationDuration,
                        easing = LinearEasing
                    )
                )
                // Snap back to the beginning for the next loop iteration.
                progress.snapTo(0f)
            }
        }
    }

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(strokeWidth)
    ) {
        val canvasWidth = size.width
        val centerX = center.x
        val centerY = center.y

        val lineStartX: Float
        val lineEndX: Float
        val currentProgress = progress.value

        if (currentProgress < 0.5f) {
            // Phase 1: Expansion (from center to full width)
            val expansionProgress = currentProgress * 2f // Map [0, 0.5] to [0, 1]
            lineStartX = centerX - (centerX * expansionProgress)
            lineEndX = centerX + (centerX * expansionProgress)
        } else {
            // Phase 2: Contraction (from full width to center)
            val contractionProgress = (currentProgress - 0.5f) * 2f // Map [0.5, 1] to [0, 1]
            lineStartX = 0f + (centerX * contractionProgress)
            lineEndX = canvasWidth - (centerX * contractionProgress)
        }

        // Draw the active line segment
        drawLine(
            color = color,
            start = Offset(lineStartX, centerY),
            end = Offset(lineEndX, centerY),
            strokeWidth = strokeWidth.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun ContinuousWormLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "worm_loader_transition")

    // Animates the starting position of the worm
    val startX by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 800, delayMillis = 200),
            repeatMode = RepeatMode.Reverse // This makes it go back and forth
        ),
        label = "worm_position"
    )

    val fillColor = MaterialTheme.colorScheme.primary

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
    ) {
        val strokeWidth = size.height
        val wormLength = size.width / 4f // The worm is 1/4 of the total width
        val canvasWidth = size.width

        // Background track
        drawLine(
            color = Color.Transparent,
            start = Offset(0f, center.y),
            end = Offset(canvasWidth, center.y),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // The moving worm
        val wormStart = startX * (canvasWidth - wormLength)
        drawLine(
            color = fillColor,
            start = Offset(wormStart, center.y),
            end = Offset(wormStart + wormLength, center.y),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun ContinuousDashedLineLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "dashed_line_transition")
    val dashPhase by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 200f, // This value determines the speed
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ),
        label = "dash_phase"
    )

    val fillColor = MaterialTheme.colorScheme.primary

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
    ) {
        val strokeWidth = size.height
        // A dash of 40 pixels, followed by a gap of 20 pixels
        val dashPattern = floatArrayOf(40f, 20f)
        val pathEffect = PathEffect.dashPathEffect(dashPattern, dashPhase)

        drawLine(
            color = fillColor,
            start = Offset(0f, center.y),
            end = Offset(size.width, center.y),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Butt, // Use Butt for crisp dashes
            pathEffect = pathEffect
        )
    }
}

@Composable
fun ContinuousPulsingLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "pulsing_loader_transition")

    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulsing_alpha"
    )

    val fillColor = MaterialTheme.colorScheme.primary

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
    ) {
        val strokeWidth = size.height

        // The pulsing line
        drawLine(
            color = fillColor.copy(alpha = alpha), // Animate the alpha channel
            start = Offset(0f, center.y),
            end = Offset(size.width, center.y),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun ChasingDotsLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "chasing_dots_transition")

    // The first dot (leader)
    val leaderPosition by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "leader_dot"
    )

    // The second dot (follower), starts with a delay
    val followerPosition by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500, delayMillis = 200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "follower_dot"
    )

    val fillColor = MaterialTheme.colorScheme.primary

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
    ) {
        val strokeWidth = size.height
        val canvasWidth = size.width

        // Background Track
        drawLine(
            color = Color.Transparent,
            start = Offset(0f, center.y),
            end = Offset(canvasWidth, center.y),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // Draw Follower Dot
        drawLine(
            color = fillColor,
            start = Offset(followerPosition * canvasWidth, center.y),
            end = Offset(followerPosition * canvasWidth, center.y),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )

        // Draw Leader Dot
        drawLine(
            color = fillColor,
            start = Offset(leaderPosition * canvasWidth, center.y),
            end = Offset(leaderPosition * canvasWidth, center.y),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun GradientSweepLoader() {
    val infiniteTransition = rememberInfiniteTransition(label = "gradient_sweep_transition")

    val progress by infiniteTransition.animateFloat(
        initialValue = -0.5f, // Start off-screen to the left
        targetValue = 1.5f,  // End off-screen to the right
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Restart
        ),
        label = "gradient_progress"
    )

    val fillColor = MaterialTheme.colorScheme.primary

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(2.dp)
    ) {
        val sweepWidth = 0.4f // The width of the bright part of the gradient
        val colors = listOf(Color.Transparent, fillColor, Color.Transparent)

        val brush = Brush.horizontalGradient(
            colors = colors,
            startX = (progress - sweepWidth) * size.width,
            endX = progress * size.width
        )

        // Background
        drawLine(
            color = Color.Transparent,
            start = Offset(0f, center.y),
            end = Offset(size.width, center.y),
            strokeWidth = size.height,
            cap = StrokeCap.Round
        )

        // Sweeping Gradient
        drawLine(
            brush = brush,
            start = Offset(0f, center.y),
            end = Offset(size.width, center.y),
            strokeWidth = size.height,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun SegmentedWaveLoader(
    modifier: Modifier = Modifier,
    numSegments: Int = 20,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val infiniteTransition = rememberInfiniteTransition(label = "constant_wave_transition")

    val waveSpread = 3f
    val waveHead by infiniteTransition.animateFloat(
        initialValue = -waveSpread,
        targetValue = numSegments.toFloat() + waveSpread,
        animationSpec = infiniteRepeatable(
            // Use LinearEasing for a constant speed
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "wave_head_position"
    )

    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp)
    ) {
        val segmentWidth = size.width / numSegments
        val segmentSpacing = 4.dp.toPx()

        for (i in 0 until numSegments) {
            val distanceToWaveHead = abs(i - waveHead)
            val segmentAlpha = (1f - (distanceToWaveHead / waveSpread)).coerceIn(0f, 1f)
            val segmentColor = color.copy(alpha = segmentAlpha)

            if (segmentAlpha > 0) {
                val startX = i * segmentWidth + segmentSpacing / 2
                val endX = (i + 1) * segmentWidth - segmentSpacing / 2

                drawLine(
                    color = segmentColor,
                    start = Offset(startX, center.y),
                    end = Offset(endX, center.y),
                    strokeWidth = size.height,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}