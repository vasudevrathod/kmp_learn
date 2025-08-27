package com.vaasudev.kmptest.presentation.redirection

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun RedirectionScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        RedirectionScreenContent(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun RedirectionScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: RedirectionScreenViewModel = viewModel()
) {

    var isStart by remember {
        mutableStateOf(false)
    }
    var showSettingDialog by remember {
        mutableStateOf(false)
    }

    val density = LocalDensity.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /*UserGreetingWithSetting(isStart = isStart, onSettingClick = {
            showSettingDialog = true
            //navController.navigate(ScreenSettings)
        })*/

        Spacer(modifier = Modifier.height(20.dp))

        AnimatedVisibility(
            visible = isStart,
            enter = slideInVertically {
                // Slide in from 40 dp from the top.
                with(density) { -100.dp.roundToPx() }
            } + expandVertically(
                // Expand from the top.
                expandFrom = Alignment.Top
            ) + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically() + shrinkVertically() + fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F)
            ) {
                items(viewModel.redirectionScreen()) { item ->
                    RedirectionItemView(data = item, onItemClick = { itemData: RedirectionModel ->
                        viewModel.redirectScreen(
                            id = itemData.id,
                            navController = navController
                        )
                    })
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(1000)
        isStart = true
    }
}