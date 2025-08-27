package com.vaasudev.kmptest._global.controller

import com.vaasudev.kmptest.BuildConfig

actual fun getSecretData(): GetSecretData {
    return object : GetSecretData {
        override val googleApiKey: String = BuildConfig.TEST_KEY
    }
}