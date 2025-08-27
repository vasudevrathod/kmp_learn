package com.vaasudev.kmptest._global.controller

actual fun getSecretData(): GetSecretData {
    return object : GetSecretData {
        override val googleApiKey: String = "YOUR_GOOGLE_API_KEY"
    }
}