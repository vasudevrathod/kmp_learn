package com.vaasudev.kmptest

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vaasudev.kmptest._global.controller.isSystemInDarkTheme
import com.vaasudev.kmptest._global.ui.theme.CommonTheme
import com.vaasudev.kmptest.presentation.dashboard.DashboardScreen
import com.vaasudev.kmptest.presentation.otp.OtpScreen
import com.vaasudev.kmptest.presentation.payment_info.PaymentInfoScreen
import com.vaasudev.kmptest.presentation.ride_book.RideBookScreen
import com.vaasudev.kmptest.presentation.sign_in.SignInScreen
import com.vaasudev.kmptest.presentation.sign_in_email.SignInEmailScreen
import com.vaasudev.kmptest.presentation.splash.SplashScreen
import com.vaasudev.kmptest.presentation.trip_details.TripDetailsScreen
import com.vaasudev.kmptest.presentation.trips_list.TripsListScreen
import com.vaasudev.kmptest.presentation.user_email.UserEmailScreen
import com.vaasudev.kmptest.presentation.user_gender.UserGenderScreen
import com.vaasudev.kmptest.presentation.user_info.UserInfoScreen
import com.vaasudev.kmptest.presentation.user_name.UserNameScreen
import com.vaasudev.kmptest.presentation.user_phone_number.UserPhoneNumberScreen
import com.vaasudev.kmptest.presentation.wallet.WalletScreen
import io.ktor.client.HttpClient
import org.jetbrains.compose.ui.tooling.preview.Preview


/** # Create New Project
* Redirect to [Kotlin Multiplatform Wizard ](https://kmp.jetbrains.com/)
* */
@Composable
@Preview
fun App(
    prefs: DataStore<Preferences>,
    remoteClient: HttpClient
) {
    CommonTheme(isDarkTheme = isSystemInDarkTheme()) {

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = ScreenSplash,
            enterTransition = {
                fadeIn(tween(1000))
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Start, tween(1000)
                )
            }/*, popEnterTransition = {
                        fadeIn(tween(1000))
                    }*/, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End, tween(1000)
                )
            }) {
            composable<ScreenSplash> {
                SplashScreen(navController = navController, remoteClient = remoteClient)
            }

            composable<ScreenSignIn> {
                SignInScreen(navController = navController, prefs = prefs)
            }

            composable<ScreenOtp> {
                val arg = it.toRoute<ScreenOtp>()
                OtpScreen(navController = navController, signInId = arg.signInId)
            }

            composable<ScreenSignInEmail> {
                SignInEmailScreen(navController = navController)
            }

            composable<ScreenUserInfo> {
                UserInfoScreen(navController = navController)
            }

            composable<ScreenUserName> {
                UserNameScreen(navController = navController)
            }

            composable<ScreenUserGender> {
                UserGenderScreen(navController = navController)
            }

            composable<ScreenUserPhoneNumber> {
                UserPhoneNumberScreen(navController = navController)
            }

            composable<ScreenUserEmail> {
                UserEmailScreen(navController = navController)
            }

            composable<ScreenRideBook> {
                RideBookScreen(navController = navController)
            }

            composable<ScreenDashboard> {
                DashboardScreen(navController = navController)
            }

            composable<ScreenWallet> {
                WalletScreen(navController = navController)
            }

            composable<ScreenPaymentInfo> {
                val arg = it.toRoute<ScreenPaymentInfo>()
                PaymentInfoScreen(
                    navController = navController,
                    paymentTypeId = arg.paymentTypeId
                )
            }

            composable<ScreenTripDetails> {
                val arg = it.toRoute<ScreenTripDetails>()
                TripDetailsScreen(navController = navController, tripId = arg.tripId)
            }

            composable<ScreenTripsList> {
                TripsListScreen(navController = navController)
            }
        }

        /*var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "Hello Vaasudev Welcome TO KMP First Project",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.primary
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                shape = RoundedCornerShape(18.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                onClick = {
                    showContent = !showContent
                },
            ) {
                Text(
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    text = "Click me!",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }*/
    }
}