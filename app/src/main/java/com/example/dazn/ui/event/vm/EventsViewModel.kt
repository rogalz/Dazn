package com.example.dazn.ui.event.vm

import androidx.lifecycle.ViewModel
import com.example.dazn.domain.usecase.GetEventsUseCase

class EventsViewModel(
    private val getEventsUseCase: GetEventsUseCase
) : ViewModel() {
}