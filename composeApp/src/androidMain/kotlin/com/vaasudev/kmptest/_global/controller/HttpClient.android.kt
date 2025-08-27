package com.vaasudev.kmptest._global.controller

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun getClientEngine(): HttpClientEngine {
    return OkHttp.create()
}