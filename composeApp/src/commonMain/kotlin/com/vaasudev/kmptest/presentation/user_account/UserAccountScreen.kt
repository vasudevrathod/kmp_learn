package com.vaasudev.kmptest.presentation.user_account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaasudev.kmptest.ScreenSignIn
import com.vaasudev.kmptest.ScreenTripsList
import com.vaasudev.kmptest.ScreenUserInfo
import com.vaasudev.kmptest.ScreenWallet
import com.vaasudev.kmptest._global.ui.theme.Fonts
import com.vaasudev.kmptest._global.ui.theme.fontFamily
import com.vaasudev.kmptest.domain.model.myAccountSettings

@Composable
fun UserAccountScreen(
    navController: NavController
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        UserAccountScreenContent(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }
}

@Composable
fun UserAccountScreenContent(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        UserNameRating("Test User")
        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                QuickAccess(onClick = { quickAccess ->
                    when(quickAccess.id) {
                        1 -> {}
                        2 -> {
                            navController.navigate(ScreenWallet)
                        }
                        3 -> {
                            navController.navigate(ScreenTripsList)
                        }
                    }
                })
                Spacer(modifier = Modifier.height(15.dp))
            }

            items(myAccountSettings.size) { index ->
                AccountSettingsItem(
                    data = myAccountSettings[index],
                    onClick = {
                        settingsRedirection(
                            navController = navController,
                            id = myAccountSettings[index].id
                        )
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Version - 1.0.0",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = fontFamily(font = Fonts.LIGHT),
                        color = MaterialTheme.colorScheme.onSurface,
                    ),
                )
            }
        }
    }
}

fun settingsRedirection(navController: NavController, id: Int) {
    when (id) {
        1 -> {}
        2 -> {}
        3 -> {}
        4 -> {}
        5 -> {}
        6 -> {}
        7 -> {}
        8 -> {}
        9 -> {navController.navigate(ScreenUserInfo)}
        10 -> {}
    }
}