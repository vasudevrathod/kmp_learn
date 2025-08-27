package com.vaasudev.kmptest.presentation.otp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest.ScreenDashboard
import com.vaasudev.kmptest.ScreenHome
import com.vaasudev.kmptest.presentation.widgets.GoogleLinearLoader2
import kotlinx.coroutines.delay
import kotlin.math.sign

@Composable
fun OtpScreen(
    navController: NavController,
    signInId: String
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        OtpScreenContent(
            navController = navController,
            signInId = signInId,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun OtpScreenContent(
    navController: NavController,
    signInId: String,
    modifier: Modifier = Modifier,
) {

    val localFocusManager = LocalFocusManager.current
    var otpValue by remember { mutableStateOf("") }
    var isNextEnable by remember { mutableStateOf(false) }
    var isAnimating by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { localFocusManager.clearFocus() }
                )
            }
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(isAnimating) {
                GoogleLinearLoader2(
                    modifier = Modifier,
                    isAnimating = isAnimating,
                    segmentLength = 0.5f,
                    animationDuration = 1000 //
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            WelcomeText("Test")
            Spacer(modifier = Modifier.height(10.dp))
            OtpMessageWithNumber(signInId = signInId)
            Spacer(modifier = Modifier.height(10.dp))
            ChangedNumber(onClick = {})
            Spacer(modifier = Modifier.height(30.dp))
            OtpTextField(
                otpLength = 4,
                onOtpChange = {otp ->
                    otpValue = otp
                }
            )
            Spacer(modifier = Modifier.height(30.dp))
            ResendCode(onClick = {})
            Spacer(modifier = Modifier.height(15.dp))
        }
        NextPreviousButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 15.dp, end = 15.dp, bottom = 20.dp),
            isNextEnable = isNextEnable,
            onBackClick = { navController.popBackStack() },
            onNextClick = {
                isAnimating = true
                isNextEnable = false
            }
        )
    }

    LaunchedEffect(isAnimating) {
        if (!isAnimating) {
            isNextEnable = false
            return@LaunchedEffect
        }
        delay(3000)
        isAnimating = false
        navController.navigate(ScreenDashboard){
            popUpTo(0)
            launchSingleTop = true
        }
    }

    LaunchedEffect(otpValue) {
        if (otpValue.length == 4) {
            localFocusManager.clearFocus()
            isNextEnable = true
        } else {
            isNextEnable = false
        }
    }
}