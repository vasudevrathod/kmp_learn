package com.vaasudev.kmptest.domain.ktor

import com.vaasudev.kmptest.domain.enum_classes.NetworkError
import com.vaasudev.kmptest.domain.handle_error.checkStatus
import com.vaasudev.kmptest.domain.utility.Result
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType

suspend inline fun <reified T> manageResponse(response: HttpResponse): Result<T, NetworkError>  {
    return if (response.status.value == 200) {
        Result.Success(response.body())
    } else {
        Result.Error(response.status.value.checkStatus())
    }
}

/*fun setupMultipartContent(param: Map<String, String>, fileParam: String,  file: File): MultiPartFormDataContent {
    return MultiPartFormDataContent(
        formData {
            param.forEach {
                append(it.key, it.value)
            }
            append(fileParam, file.readBytes(),
                Headers.build {
                    append(HttpHeaders.ContentType, "image/jpg")
                    append(HttpHeaders.ContentDisposition, "filename=${file.name}")
                })
        }
    )
}*/

fun HttpRequestBuilder.formUrlEncoded(param: Map<String, String>, basicAuthHeader: String) {
    contentType(ContentType.Application.FormUrlEncoded)
    header("Authorization", "Bearer $basicAuthHeader")
    setBody(FormDataContent(Parameters.build { param.forEach {
        append(it.key, it.value)
    } }))
}