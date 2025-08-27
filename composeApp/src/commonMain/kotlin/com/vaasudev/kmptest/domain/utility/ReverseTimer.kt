package com.vaasudev.kmptest.domain.utility

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class ReverseTimer(
    private val initialSeconds: Int,
    private val coroutineScope: CoroutineScope
) {
    private val _remainingSeconds = MutableStateFlow(initialSeconds)
    val remainingSeconds: StateFlow<Int> = _remainingSeconds.asStateFlow()

    private val _isTimerRunning = MutableStateFlow(false)
    val isTimerRunning: StateFlow<Boolean> = _isTimerRunning.asStateFlow()

    private val _canResend = MutableStateFlow(false)
    val canResend: StateFlow<Boolean> = _canResend.asStateFlow()

    private var timerJob: Job? = null

    fun start() {
        if (timerJob?.isActive == true) return // Timer already running

        _remainingSeconds.value = initialSeconds
        _isTimerRunning.value = true
        _canResend.value = false

        timerJob = coroutineScope.launch {
            while (_remainingSeconds.value > 0) {
                delay(1.seconds)
                _remainingSeconds.value--
            }
            _isTimerRunning.value = false
            _canResend.value = true
        }
    }

    fun stop() {
        timerJob?.cancel()
        _isTimerRunning.value = false
        _remainingSeconds.value = 0 // Optionally reset or keep last value
        _canResend.value = true
    }

    fun reset() {
        stop()
        _remainingSeconds.value = initialSeconds
        _canResend.value = false
    }
}