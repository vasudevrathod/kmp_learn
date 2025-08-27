package com.vaasudev.kmptest._global.controller

import com.vaasudev.kmptest.BuildConfig

class AndroidPlatform : Platform {
    override val name: String = "android" /*${Build.VERSION.SDK_INT}"*/
    override val versionName: String = BuildConfig.VERSION_NAME
    override val versionCode: String = BuildConfig.VERSION_CODE.toString()
}

actual fun getPlatform(): Platform = AndroidPlatform()