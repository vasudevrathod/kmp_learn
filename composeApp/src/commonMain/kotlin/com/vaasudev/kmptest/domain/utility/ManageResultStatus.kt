package com.vaasudev.kmptest.domain.utility

import com.vaasudev.kmptest.domain.enum_classes.Error
import com.vaasudev.kmptest.domain.model.ErrorModel

typealias RootError = Error

sealed interface Result<out D, out E: RootError> {
    data class Success<out D, out E: RootError>(val data: D): Result<D, E>
    data class Error<out D, out E: RootError>(val error: E): Result<D, E>
}

sealed class Status {
    data object Loading : Status()
    data class Success<out D>(val data: D): Status()
    //data class Error<out E>(val error: E): Status()
    data class Error(val error: ErrorModel): Status()
}