package com.vaasudev.kmptest._global.controller

import platform.Foundation.NSLog

actual fun printLog(tag: String, message: String) {
    NSLog("[$tag] Log =====> 🧐🧐🧐 $message")
}