package com.vaasudev.kmptest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.vaasudev.kmptest._global.controller.createDataStore
import com.vaasudev.kmptest._global.controller.getClientEngine
import com.vaasudev.kmptest.data.remote_ktor.createHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                prefs = remember {createDataStore(applicationContext)},
                remoteClient = remember { createHttpClient(engine = getClientEngine()) }
            )
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
}