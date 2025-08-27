package com.vaasudev.kmptest.data.remote_ktor

import com.vaasudev.kmptest._global.controller.printLog
import com.vaasudev.kmptest.domain.ktor.KtorUtility
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(engine: HttpClientEngine): HttpClient {
    return HttpClient(engine) {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    printLog("Logger Ktor =>", message)
                }
            }
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    prettyPrint = true
                    allowStructuredMapKeys = true
                }
            )

            register(
                ContentType.Text.Html, KotlinxSerializationConverter(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            )
        }

        defaultRequest {
            url("https://dev.shoppinggate.app/api/v5/")
            header(HttpHeaders.ContentType, ContentType.Application.Json)
            header(KtorUtility.ApiHeaderKey.KEY, KtorUtility.ApiHeaderValue.KEY_VALUE)
        }
    }
}