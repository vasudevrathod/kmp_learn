package com.vaasudev.kmptest.domain.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse(
    val status: Boolean,
    val message: String,
)
