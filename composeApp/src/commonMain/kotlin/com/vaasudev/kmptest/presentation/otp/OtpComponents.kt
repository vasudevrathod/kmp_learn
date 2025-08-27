package com.vaasudev.kmptest.presentation.otp

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.utility.ReverseTimer
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun WelcomeText(userName: String = "") {
    Text(
        modifier = Modifier
            .padding(horizontal = 15.dp),
        text = if (userName.isEmpty()) "Welcome to SafarCab." else "Welcome back, ${userName.uppercase()}.",
        style = MaterialTheme.typography.titleLarge.copy(
            fontFamily = fontFamily(font = Fonts.BOLD),
            color = MaterialTheme.colorScheme.onSurface,
        ),
    )
}

@Composable
fun OtpMessageWithNumber(signInId: String) {
    Text(
        modifier = Modifier
            .padding(horizontal = 15.dp),
        text = "Enter the 4-digit code sent at $signInId",
        style = MaterialTheme.typography.bodySmall.copy(
            fontFamily = fontFamily(font = Fonts.REGULAR),
            color = MaterialTheme.colorScheme.onSurface,
        ),
    )
}

@Composable
fun ChangedNumber(onClick: () -> Unit) {
    Text(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clip(shape = RoundedCornerShape(3.dp))
            .clickable(
                onClick = onClick,
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() }
            ),
        text = "Changed your mobile number?",
        style = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = fontFamily(font = Fonts.MEDIUM),
            color = MaterialTheme.colorScheme.onSurface,
            textDecoration = TextDecoration.Underline,
        ),
    )
}

@Composable
fun ResendCode(onClick: () -> Unit) {

    val coroutineScope = rememberCoroutineScope()
    val otpTimer = remember { ReverseTimer(initialSeconds = 10, coroutineScope = coroutineScope) }

    val remainingSeconds by otpTimer.remainingSeconds.collectAsState()
    val isTimerRunning by otpTimer.isTimerRunning.collectAsState()
    val canResend by otpTimer.canResend.collectAsState()

    // Start the timer when the screen is first composed
    LaunchedEffect(Unit) {
        otpTimer.start()
    }

    Text(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .background(color = MaterialTheme.colorScheme.surfaceContainer, shape = RoundedCornerShape(10.dp))
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable(
                onClick = {
                    onClick.invoke()
                    otpTimer.start()
                },
                indication = ripple(),
                interactionSource = remember { MutableInteractionSource() },
                enabled = canResend
            )
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .animateContentSize(animationSpec = tween(durationMillis = 300)),
        text = if (canResend) "Resend code" else "Resend code in ${remainingSeconds}s",
        style = MaterialTheme.typography.bodySmall.copy(
            fontFamily = fontFamily(font = Fonts.SEMI_BOLD),
            color = if (canResend) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F),
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpLength: Int = 4,
    onOtpChange: (String) -> Unit
) {
    var otpValue by remember { mutableStateOf(TextFieldValue("")) }

    BasicTextField(
        value = otpValue,
        onValueChange = { newValue ->
            if (newValue.text.length <= otpLength && newValue.text.all { it.isDigit() }) {
                otpValue = newValue
                onOtpChange(newValue.text)
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        modifier = modifier
            .wrapContentHeight()
            .padding(horizontal = 15.dp),
        decorationBox = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(otpLength) { index ->
                    val char = when {
                        index < otpValue.text.length -> otpValue.text[index].toString()
                        else -> ""
                    }
                    val isFocused = otpValue.selection.collapsed && otpValue.selection.start == index

                    Box(
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .width(40.dp)
                            .height(40.dp)
                            .background(color = if (isFocused) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.surfaceContainer, shape = RoundedCornerShape(8.dp))
                            .border(
                                width = 2.dp,
                                color = if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainer,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = char,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 24.sp),
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun NextPreviousButton(modifier: Modifier = Modifier, isNextEnable: Boolean, onBackClick: () -> Unit, onNextClick: () -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .background(color = MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(50.dp))
                .clip(shape = RoundedCornerShape(50.dp))
                .clickable(
                    onClick = onBackClick,
                    indication = ripple(),
                    interactionSource = remember { MutableInteractionSource() }
                )
                .padding(10.dp),
            painter = painterResource(Res.drawable.ic_arrow_back),
            contentDescription = "Back"
        )

        Image(
            modifier = Modifier
                .size(40.dp)
                .background(color = if (isNextEnable) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceContainer, shape = RoundedCornerShape(50.dp))
                .clip(shape = RoundedCornerShape(50.dp))
                .clickable(
                    onClick = onNextClick,
                    indication = ripple(),
                    interactionSource = remember { MutableInteractionSource() },
                    enabled = isNextEnable
                )
                .padding(10.dp),
            painter = painterResource(Res.drawable.ic_arrow_forward),
            contentDescription = "Back"
        )
    }
}