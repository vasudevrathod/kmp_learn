package com.vaasudev.kmptest.presentation.sign_in_email

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
import com.vaasudev.kmptest.ScreenOtp
import com.vaasudev.kmptest.domain.utility.emailAddressRegex
import com.vaasudev.kmptest.presentation.otp.NextPreviousButton
import com.vaasudev.kmptest.presentation.widgets.EmailTextField
import com.vaasudev.kmptest.presentation.widgets.GoogleLinearLoader2
import kotlinx.coroutines.delay

@Composable
fun SignInEmailScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SignInEmailScreenContent(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun SignInEmailScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val localFocusManager = LocalFocusManager.current
    var isNextEnable by remember { mutableStateOf(true) }
    var isAnimating by remember { mutableStateOf(false) }
    var textValue by remember { mutableStateOf("") }

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
            WhatYourEmailText()
            Spacer(modifier = Modifier.height(20.dp))
            EmailTextField(
                textValue = textValue,
                onValueChange = { newText ->
                    if (newText.length <= 40) {
                        textValue = newText
                    }
                },
                onDone = {
                    if (textValue.isEmpty()) return@EmailTextField
                    if (textValue.matches(emailAddressRegex)) {
                        localFocusManager.clearFocus()
                        isAnimating = true
                    }
                }
            )
        }
        NextPreviousButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 15.dp, end = 15.dp, bottom = 20.dp),
            isNextEnable = true,
            onBackClick = { navController.popBackStack() },
            onNextClick = {
                if (textValue.isEmpty()) return@NextPreviousButton
                if (textValue.matches(emailAddressRegex)) {
                    localFocusManager.clearFocus()
                    isAnimating = true
                }
            }
        )
    }

    LaunchedEffect(isAnimating) {
        if (!isAnimating) {
            return@LaunchedEffect
        }
        delay(3000)
        isAnimating = false
        navController.navigate(ScreenOtp(signInId = textValue))
    }
}