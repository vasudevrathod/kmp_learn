package com.vaasudev.kmptest.domain.parcelable

import kotlinx.serialization.Serializable

@Serializable
data class MakeDefaultPaymentMethod(
    val typedId: Int,
    val isDefault: Boolean
)
