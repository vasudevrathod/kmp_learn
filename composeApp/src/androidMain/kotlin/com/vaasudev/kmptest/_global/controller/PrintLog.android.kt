package com.vaasudev.kmptest._global.controller

import android.util.Log
import com.vaasudev.kmptest.BuildConfig

actual fun printLog(tag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.i(tag, "$tag Log =====> 🧐🧐🧐 $message")
    }
}