package com.example.dazn.ui.screens.schedule.vw

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dazn.domain.usecase.GetSchedulesUseCase
import com.example.dazn.ui.screens.schedule.SchedulesScreenViewState
import com.example.dazn.ui.screens.schedule.mapper.ScheduleViewStateMapper
import com.example.dazn.ui.utils.isTomorrow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class SchedulesViewModel(
    private val getSchedulesUseCase: GetSchedulesUseCase,
    private val mapper: ScheduleViewStateMapper
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<SchedulesScreenViewState>(SchedulesScreenViewState.Loading)
    val uiState: StateFlow<SchedulesScreenViewState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repeat(Int.MAX_VALUE) {
                getSchedules()
                delay(THIRTY_SECOND_IN_MILLIS)
            }
        }
    }

    private fun getSchedules() {
        val now = ZonedDateTime.now()
        viewModelScope.launch {
            getSchedulesUseCase.execute()
                .onSuccess { schedules ->
                    val onlyForTomorrow = schedules.filter { it.date.isTomorrow(now) }
                    _uiState.update { mapper.map(onlyForTomorrow) }
                }
                .onFailure { _uiState.update { SchedulesScreenViewState.Error } }
        }
    }

    companion object {
        const val THIRTY_SECOND_IN_MILLIS = 30000L
    }
}