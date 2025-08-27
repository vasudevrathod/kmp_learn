package com.vaasudev.kmptest.presentation.dashboard

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.vaasudev.kmptest.ScreenHome
import com.vaasudev.kmptest.ScreenServices
import com.vaasudev.kmptest.ScreenUserAccount
import com.vaasudev.kmptest._global.controller.printLog
import com.vaasudev.kmptest.presentation.home.HomeScreen
import com.vaasudev.kmptest.presentation.services.ServicesScreen
import com.vaasudev.kmptest.presentation.user_account.UserAccountScreen

@Composable
fun DashboardScreen(
    navController: NavController
) {

    val dashBoardNavController = rememberNavController()
    var selectedTab by remember { mutableIntStateOf(0) }

    val navBackStackEntry by dashBoardNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    printLog("Route", currentRoute.toString())

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        bottomBar = {
            HomeBottomNavigation(
                onClick = { position ->
                    selectedTab = position
                    when (position) {

                        0 -> {
                            dashBoardNavController.navigate(ScreenHome) {
                                popUpTo(dashBoardNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }

                        1 -> {
                            dashBoardNavController.navigate(ScreenServices) {
                                popUpTo(dashBoardNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }

                        2 -> {
                            dashBoardNavController.navigate(ScreenUserAccount) {
                                popUpTo(dashBoardNavController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                },
                selectedTab = selectedTab
            )
        }
    ) { innerPadding ->
        DashboardScreenContent(
            navController = navController,
            dashBoardNavController = dashBoardNavController,
            onHomeSeeAllClick = {
                selectedTab = 1
                dashBoardNavController.navigate(ScreenServices) {
                    popUpTo(dashBoardNavController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(innerPadding)
        )
    }

    LaunchedEffect(currentRoute) {
        when {
            currentRoute?.contains("ScreenHome") == true -> {
                selectedTab = 0
            }
            currentRoute?.contains("ScreenServices") == true -> {
                selectedTab = 1
            }
            currentRoute?.contains("ScreenUserAccount") == true -> {
                selectedTab = 2
            }
        }
    }
}

@Composable
fun DashboardScreenContent(
    navController: NavController,
    dashBoardNavController: NavHostController,
    onHomeSeeAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.surface))
    {
        NavHost(
            navController = dashBoardNavController, startDestination = ScreenHome,
            enterTransition = {
                fadeIn(tween(1000))
            },
            exitTransition = {
                fadeOut(tween(1000))
            },
            popExitTransition = {
                fadeOut(tween(1000))
            }) {
            composable<ScreenHome> {
                HomeScreen(navController = navController, onSeeAllClick = onHomeSeeAllClick)
            }

            composable<ScreenServices> {
                ServicesScreen(navController = navController)
            }

            composable<ScreenUserAccount> {
                UserAccountScreen(navController = navController)
            }
        }
    }
}