package com.vaasudev.kmptest._global

import com.vaasudev.kmptest._global.controller.getPlatform

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}