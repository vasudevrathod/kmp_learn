package com.vaasudev.kmptest

import com.vaasudev.kmptest.domain.model.PaymentMethods
import com.vaasudev.kmptest.domain.parcelable.MakeDefaultPaymentMethod
import kotlinx.serialization.Serializable


/** # `Auth` - Route
 *
 * @author - Vaasudev J. Rathod
 * */
@Serializable
object ScreenSplash

@Serializable
object ScreenHome

@Serializable
object ScreenSignIn

@Serializable
data class ScreenOtp(val signInId: String)

@Serializable
object ScreenSignInEmail

@Serializable
object ScreenUserAccount

@Serializable
object ScreenUserInfo

@Serializable
object ScreenUserName

@Serializable
object ScreenUserGender

@Serializable
object ScreenUserPhoneNumber

@Serializable
object ScreenUserEmail

@Serializable
object ScreenServices

@Serializable
object ScreenRideBook

@Serializable
object ScreenDashboard

@Serializable
object ScreenWallet

@Serializable
data class ScreenPaymentInfo(val paymentTypeId: Int)

@Serializable
data class ScreenTripDetails(val tripId: Int)

@Serializable
object ScreenTripsList


