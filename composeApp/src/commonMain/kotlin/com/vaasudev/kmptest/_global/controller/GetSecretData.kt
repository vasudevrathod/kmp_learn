package com.vaasudev.kmptest._global.controller

interface GetSecretData {
    val googleApiKey: String
}

expect fun getSecretData(): GetSecretData