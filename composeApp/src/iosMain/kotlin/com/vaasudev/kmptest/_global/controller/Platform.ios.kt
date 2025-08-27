package com.vaasudev.kmptest._global.controller

import platform.Foundation.NSBundle
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
        override val name: String = UIDevice.currentDevice.systemName() /*+ " " + UIDevice.currentDevice.systemVersion*/
        override val versionName: String = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String ?: "N/A"
        override val versionCode: String = NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion") as? String ?: "N/A"
}

actual fun getPlatform(): Platform = IOSPlatform()
