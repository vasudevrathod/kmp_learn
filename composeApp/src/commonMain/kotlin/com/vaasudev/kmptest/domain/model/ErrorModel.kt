package com.vaasudev.kmptest.domain.model

data class ErrorModel(
    val statusCode: Int = 500,
    val message: String = "Something went wrong",
)
