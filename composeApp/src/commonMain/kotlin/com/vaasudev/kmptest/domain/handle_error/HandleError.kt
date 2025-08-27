package com.vaasudev.kmptest.domain.handle_error

import com.vaasudev.kmptest.domain.enum_classes.NetworkError
import io.ktor.client.plugins.ClientRequestException
import com.vaasudev.kmptest.library.resources.Res
import com.vaasudev.kmptest.library.resources.*
import kotlinx.io.files.FileNotFoundException

fun Int.checkStatus(): NetworkError = when (this) {
    400 -> NetworkError.CLIENT_BAD_REQUEST
    401 -> NetworkError.CLIENT_UNAUTHORIZED
    403 -> NetworkError.CLIENT_FORBIDDEN
    404 -> NetworkError.CLIENT_NOT_FOUND
    429 -> NetworkError.TOO_MANY_REQUEST
    else -> NetworkError.UNKNOWN
}

fun Throwable.toCustomExceptions(): NetworkError = when (this) {
    is ClientRequestException ->
        when (this.response.status.value) {
            400 -> NetworkError.CLIENT_BAD_REQUEST
            401 -> NetworkError.CLIENT_UNAUTHORIZED
            403 -> NetworkError.CLIENT_FORBIDDEN
            404 -> NetworkError.CLIENT_NOT_FOUND
            429 -> NetworkError.TOO_MANY_REQUEST
            else -> NetworkError.UNKNOWN
        }
    is IllegalArgumentException -> NetworkError.ILLEGAL_ARGUMENT_EXCEPTION
    is IllegalStateException -> NetworkError.UNKNOWN
    //is UnknownHostException -> NetworkError.NO_INTERNET_AVAILABLE
    is NullPointerException -> NetworkError.NULL_POINTER_EXCEPTION
    is FileNotFoundException -> NetworkError.FILE_NOT_FOUND_EXCEPTION
    else -> NetworkError.UNKNOWN
}

fun NetworkError.asNetworkErrorString(): String = when (this) {
    NetworkError.SERVER_RESPONSE_EXCEPTION -> "Server Response Error"
    NetworkError.CLIENT_BAD_REQUEST -> "Bad Request"
    NetworkError.CLIENT_UNAUTHORIZED -> "Unauthorized"
    NetworkError.CLIENT_FORBIDDEN -> "Forbidden"
    NetworkError.CLIENT_NOT_FOUND -> "Not Found"
    NetworkError.REDIRECT_RESPONSE_EXCEPTION -> "Redirect Response Error"
    NetworkError.UNKNOWN -> "Something Want To Wrong"
    NetworkError.ILLEGAL_ARGUMENT_EXCEPTION -> "Illegal Argument"
    NetworkError.NO_INTERNET_AVAILABLE -> "No Internet Available"
    NetworkError.NULL_POINTER_EXCEPTION -> "Something Want To Wrong"
    NetworkError.TOO_MANY_REQUEST -> "Too many requests"
    NetworkError.FILE_NOT_FOUND_EXCEPTION -> ""
}