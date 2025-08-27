package com.vaasudev.kmptest.domain.repository

import com.vaasudev.kmptest.domain.enum_classes.NetworkError
import com.vaasudev.kmptest.domain.response.InitResponse
import com.vaasudev.kmptest.domain.utility.Result
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun init(url: String): Flow<Result<InitResponse, NetworkError>>
}