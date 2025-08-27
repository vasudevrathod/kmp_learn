package com.vaasudev.kmptest._global.controller

interface Platform {
    val name: String
    val versionName: String
    val versionCode: String
}

expect fun getPlatform(): Platform