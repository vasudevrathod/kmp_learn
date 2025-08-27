package com.vaasudev.kmptest

import androidx.compose.ui.window.ComposeUIViewController
import com.vaasudev.kmptest._global.controller.createDataStore
import com.vaasudev.kmptest._global.controller.getClientEngine
import com.vaasudev.kmptest.data.remote_ktor.createHttpClient

fun MainViewController() = ComposeUIViewController { App(prefs = createDataStore(), remoteClient = createHttpClient(
    getClientEngine()
)) }