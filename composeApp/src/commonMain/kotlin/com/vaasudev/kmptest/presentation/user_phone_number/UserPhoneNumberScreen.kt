package com.vaasudev.kmptest.presentation.user_phone_number

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.presentation.sign_in.ButtonCustom
import com.vaasudev.kmptest.presentation.widgets.AuthTitle
import com.vaasudev.kmptest.presentation.widgets.HeaderCommon
import com.vaasudev.kmptest.presentation.widgets.NumberTextField

@Composable
fun UserPhoneNumberScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        UserPhoneNumberScreenContent(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun UserPhoneNumberScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val localFocusManager = LocalFocusManager.current
    var phoneNumberValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { localFocusManager.clearFocus() }
                )
            },
        horizontalAlignment = Alignment.Start
    ) {
        HeaderCommon(
            title = "SafarCab Account",
            onBackClick = {
                navController.popBackStack()
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            AuthTitle("Phone Number")
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 5.dp),
                text = "You'll use this number to get notification, sign in, and recover your account.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = fontFamily(font = Fonts.REGULAR),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(bottom = 5.dp),
                text = "Phone number",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = fontFamily(font = Fonts.UBUNTU_MEDIUM),
                    color = MaterialTheme.colorScheme.onSurface,
                ),
            )

            NumberTextField(
                textValue = phoneNumberValue,
                onValueChange = { newText ->
                    if (newText.length <= 10) {
                        phoneNumberValue = newText
                    }
                },
                onDone = {
                    localFocusManager.clearFocus()
                }
            )

            Text(
                modifier = Modifier
                    .padding(horizontal = 15.dp)
                    .padding(top = 5.dp),
                text = "A verification code will be sent to this number",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontFamily = fontFamily(font = Fonts.REGULAR),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5F),
                ),
            )

            Spacer(modifier = Modifier.height(30.dp))
            ButtonCustom(
                modifier = Modifier.padding(horizontal = 15.dp),
                title = "Update",
                onClick = {
                    localFocusManager.clearFocus()
                },
                verticalPadding = 10.dp
            )
        }
    }
}
