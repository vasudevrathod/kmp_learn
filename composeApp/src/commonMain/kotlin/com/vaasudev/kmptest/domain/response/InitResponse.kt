package com.vaasudev.kmptest.domain.response

import kotlinx.serialization.Serializable

@Serializable
data class InitResponse(
    var status: Boolean = false,
    var message: String = "",
    val state: Int? = null,
)
