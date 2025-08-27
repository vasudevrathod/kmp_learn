package com.vaasudev.kmptest.presentation.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vaasudev.kmptest.ScreenHome
import com.vaasudev.kmptest.ScreenSignIn
import com.vaasudev.kmptest.ScreenSplash
import com.vaasudev.kmptest._global.controller.printLog
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.WhiteColor
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.data.repository.AuthRepositoryImpl
import com.vaasudev.kmptest.domain.response.BaseResponse
import com.vaasudev.kmptest.domain.response.InitResponse
import com.vaasudev.kmptest.domain.utility.Status
import com.vaasudev.kmptest.presentation.widgets.CustomAlertDialog
import io.ktor.client.HttpClient
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SplashScreen(
    navController: NavController,
    remoteClient: HttpClient
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SplashScreenContent(
            navController = navController,
            remoteClient = remoteClient,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun SplashScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    remoteClient: HttpClient
) {
    val viewModel: SplashViewModel = viewModel{SplashViewModel(AuthRepositoryImpl(remoteClient))}

    var isStart by remember { mutableStateOf(false) }
    var isShowDialog by remember { mutableStateOf(false) }
    var appState by remember { mutableStateOf(2) }
    var appMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppLogoAnimate(isStart = isStart)
        Spacer(modifier = Modifier.height(15.dp))
        AppName(isStart = isStart)
    }

    LaunchedEffect(Unit) {
        //viewModel.restGetAPI()
        delay(500)
        isStart = true
        delay(4000)
        navController.navigate(ScreenSignIn) {
            popUpTo(ScreenSplash) {
                inclusive = true
            }

        }
    }

    LaunchedEffect(Unit) {
        viewModel.event.collect { status ->
            when (status) {
                Status.Loading -> {

                }
                is Status.Error -> {
                    printLog("API Error", "Status - ${status.error.statusCode} -> ${status.error.message}")
                }
                is Status.Success<*> -> {
                    when(status.data) {
                        is InitResponse -> {
                            status.data.let { response ->
                                appMessage = response.message
                                response.state?.let { state ->
                                    appState = state
                                }
                                if (response.state == 1 || response.state == 2) {
                                    isShowDialog = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (isShowDialog) {
        CustomAlertDialog(
            onDismissRequest = { isShowDialog = false },
            onConfirmation = {
                isShowDialog = false
                navController.navigate(ScreenSignIn) {
                    popUpTo(ScreenSplash) {
                        inclusive = true
                    }

                }
            },
            state = appState,
            title = viewModel.getAlertDialogTitle(state = appState),
            message = appMessage
        )
    }
}

@Composable
fun AppLogoAnimate(isStart: Boolean, size: Dp = 150.dp) {

    AnimatedVisibility(
        visible = isStart,
        enter = fadeIn(initialAlpha = 0.0f, animationSpec = tween(2000)) // 2 seconds
    ) {
        AppLogo(size = size)
    }

}

@Composable
fun AppLogo(size: Dp = 150.dp) {
    Image(
        modifier = Modifier
            .size(size)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(WhiteColor.copy(alpha = 0.2F)),
        painter = painterResource(Res.drawable.app_logo),
        contentDescription = "App Logo",
        contentScale = ContentScale.Crop
    )
}

@Composable
fun AppName(isStart: Boolean) {
    AnimatedVisibility(
        visible = isStart,
        enter = slideInVertically(
            initialOffsetY = { it }, // Starts from the bottom
            animationSpec = tween(durationMillis = 2000) // 2 seconds duration
        ),
        exit = slideOutVertically(
            targetOffsetY = { it }, // Exits towards the bottom
            animationSpec = tween(durationMillis = 2000) // 2 seconds duration
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SafarCab",
                style = MaterialTheme.typography.headlineLarge.copy(fontFamily = fontFamily(font = Fonts.UBUNTU_BOLD)),
                color = MaterialTheme.colorScheme.onSurface,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = stringResource(Res.string.by_wolf_infinity),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
        }

    }
}
