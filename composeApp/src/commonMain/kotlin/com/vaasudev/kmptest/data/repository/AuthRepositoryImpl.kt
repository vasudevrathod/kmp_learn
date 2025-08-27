package com.vaasudev.kmptest.data.repository

import com.vaasudev.kmptest.domain.enum_classes.NetworkError
import com.vaasudev.kmptest.domain.handle_error.toCustomExceptions
import com.vaasudev.kmptest.domain.ktor.KtorUtility
import com.vaasudev.kmptest.domain.ktor.manageResponse
import com.vaasudev.kmptest.domain.repository.AuthRepository
import com.vaasudev.kmptest.domain.response.BaseResponse
import com.vaasudev.kmptest.domain.response.InitResponse
import com.vaasudev.kmptest.domain.utility.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
): AuthRepository {
    override suspend fun init(url: String): Flow<Result<InitResponse, NetworkError>> = flow<Result<InitResponse, NetworkError>> {
        try {
            //if (!isConnected(context)) throw UnknownHostException()

            emit(manageResponse(httpClient.get(url)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Result.Error(e.toCustomExceptions()))
        }
    }.catch {
        emit(Result.Error(it.toCustomExceptions()))
    }
}