package com.example.dazn.ui.screens.event.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dazn.domain.usecase.GetEventsUseCase
import com.example.dazn.ui.screens.event.EventsScreenViewState
import com.example.dazn.ui.screens.event.mapper.EventViewStateMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EventsViewModel(
    private val getEventsUseCase: GetEventsUseCase,
    private val mapper: EventViewStateMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<EventsScreenViewState>(EventsScreenViewState.Loading)
    val uiState: StateFlow<EventsScreenViewState> = _uiState.asStateFlow()

    init {
        getEvents()
    }

    fun getEvents() {
        _uiState.update { EventsScreenViewState.Loading }
        viewModelScope.launch {
            getEventsUseCase.execute()
                .onSuccess { events -> _uiState.update { mapper.map(events) } }
                .onFailure { _uiState.update { EventsScreenViewState.Error } }
        }
    }
}
