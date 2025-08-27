package com.vaasudev.kmptest.presentation.sign_in

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.navigation.NavController
import com.vaasudev.kmptest.ScreenOtp
import com.vaasudev.kmptest.ScreenSignInEmail
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.presentation.widgets.NumberTextField
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun SignInScreen(
    navController: NavController,
    prefs: DataStore<Preferences>
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SignInScreenContent(
            navController = navController,
            prefs = prefs,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun SignInScreenContent(
    navController: NavController,
    prefs: DataStore<Preferences>,
    modifier: Modifier = Modifier,
) {

    val localFocusManager = LocalFocusManager.current
    var textValue by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    val counter by prefs.data
        .map {
            val counterValue = intPreferencesKey("counter")
            it[counterValue] ?: "0"
        }
        .collectAsState(initial = 0)

    Column(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { localFocusManager.clearFocus() }
                )
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Spacer(modifier = Modifier.height(20.dp))
        Image(
            modifier = Modifier
                .size(70.dp)
                .clip(shape = RoundedCornerShape(20.dp)),
            painter = painterResource(Res.drawable.app_logo_auth),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Get started with SafarCab",
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = fontFamily(font = Fonts.BOLD)
            ),
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(modifier = Modifier.height(15.dp))
        NumberTextField(
            textValue = textValue,
            onValueChange = { newText ->
                if (newText.length <= 10) {
                    textValue = newText
                }
            },
            onDone = {
                localFocusManager.clearFocus()
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        ButtonCustom(
            modifier = Modifier.padding(horizontal = 15.dp),
            title = "Continue",
            onClick = {
                if (textValue.isEmpty()) return@ButtonCustom
                scope.launch {
                    prefs.edit { prefs ->
                        prefs[stringPreferencesKey("mobile")] = textValue
                    }
                    localFocusManager.clearFocus()
                    navController.navigate(ScreenOtp(signInId = textValue))
                }
            },
            verticalPadding = 10.dp
        )
        Spacer(modifier = Modifier.height(15.dp))
        OrLine()
        Spacer(modifier = Modifier.height(15.dp))
        ButtonCustom(
            modifier = Modifier.padding(horizontal = 15.dp),
            title = "Continue with Email",
            onClick = {
                navController.navigate(ScreenSignInEmail)
            },
            verticalPadding = 10.dp,
            backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
            style = MaterialTheme.typography.titleMedium.copy(
                fontFamily = fontFamily(font = Fonts.SEMI_BOLD),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 14.sp
            )
        )
    }

    LaunchedEffect(Unit) {
        prefs.data
            .map { prefs ->
                prefs[stringPreferencesKey("mobile")] ?: ""
            }
            .collect {
                textValue = it
            }
    }
}