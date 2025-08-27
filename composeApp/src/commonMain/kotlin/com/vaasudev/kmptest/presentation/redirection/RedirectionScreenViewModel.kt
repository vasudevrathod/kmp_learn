package com.vaasudev.kmptest.presentation.redirection


import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*

class RedirectionScreenViewModel : ViewModel() {

    fun redirectionScreen(): List<RedirectionModel> {
        return listOf(
            RedirectionModel(
                id = 1,
                title = "My Expense App",
                icon = Res.drawable.ic_currency_rupee
            ),
            RedirectionModel(
                id = 2,
                title = "Ktor Api Call",
                icon = Res.drawable.ic_language
            ),
            RedirectionModel(
                id = 3,
                title = "NFC",
                icon = Res.drawable.ic_contactless
            ),
            RedirectionModel(
                id = 4,
                title = "Sunmi Scanner",
                icon = Res.drawable.ic_qr_code_scanner
            ),
            RedirectionModel(
                id = 5,
                title = "Biometric Authentication",
                icon = Res.drawable.ic_fingerprint
            ),
            RedirectionModel(
                id = 6,
                title = "Permissions",
                icon = Res.drawable.ic_permissions
            ),
            RedirectionModel(
                id = 7,
                title = "Stripe Payment",
                icon = Res.drawable.ic_payment
            ),
            RedirectionModel(
                id = 8,
                title = "Google QR Scanner",
                icon = Res.drawable.ic_qr_code_scanner
            ),
            RedirectionModel(
                id = 9,
                title = "Multi Screen Size",
                icon = Res.drawable.ic_devices_fold
            ),
            RedirectionModel(
                id = 10,
                title = "Notifications",
                icon = Res.drawable.ic_notification
            ),
        )
    }

    fun redirectScreen(
        id: Int,
        navController: NavController
    ) {
    }

    enum class ThemeBrand(val type: Int) {
        DEFAULT(1),
        ANDROID(2),
    }

    enum class DarkThemeConfig(val type: Int) {
        FOLLOW_SYSTEM(1),
        LIGHT(2),
        DARK(3),
    }
}