package com.vaasudev.kmptest.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaasudev.kmptest._global.controller.Platform
import com.vaasudev.kmptest._global.controller.getPlatform
import com.vaasudev.kmptest.domain.handle_error.asNetworkErrorString
import com.vaasudev.kmptest.domain.ktor.KtorUtility
import com.vaasudev.kmptest.domain.model.ErrorModel
import com.vaasudev.kmptest.domain.utility.Result
import com.vaasudev.kmptest.domain.repository.AuthRepository
import com.vaasudev.kmptest.domain.utility.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val authRepository: AuthRepository,
): ViewModel() {
    private val eventChannel = Channel<Status>()
    val event = eventChannel.receiveAsFlow()

    fun restGetAPI() {
        viewModelScope.launch(Dispatchers.IO) {
            eventChannel.send(Status.Loading)
            authRepository.init(createInitUrl()).collect { response ->
                when (response) {
                    is Result.Error -> {
                        eventChannel.send(
                            Status.Error(
                                ErrorModel(
                                    statusCode = response.error.status,
                                    message = response.error.asNetworkErrorString()
                                )
                            )
                        )
                    }

                    is Result.Success -> {
                        if (response.data.status) {
                            eventChannel.send(Status.Success(response.data))
                        } else {
                            response.data.let { data ->
                                data.state?.let { state ->
                                    if (state == 0 || state == 1 || state == 2) {
                                        eventChannel.send(Status.Success(response.data))
                                        return@collect
                                    }
                                }
                            }
                            eventChannel.send(Status.Error(ErrorModel(message = response.data.message)))
                        }
                    }
                }
            }
        }
    }

    private fun createInitUrl(): String {
        return KtorUtility.EndPoint.INIT.plus(getPlatform().versionName).plus("/").plus(getPlatform().name).plus("/").plus("427")
    }

    fun getAlertDialogTitle(state: Int): String {
        return when(state) {
            0 -> "Update"
            1 -> "Update"
            2 -> "Maintenance"
            else -> ""
        }
    }
}