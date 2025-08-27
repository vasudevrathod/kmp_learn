package com.vaasudev.kmptest._global.controller

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun getClientEngine(): HttpClientEngine {
    return Darwin.create()
}