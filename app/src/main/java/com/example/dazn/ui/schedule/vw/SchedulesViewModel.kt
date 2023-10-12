package com.example.dazn.ui.schedule.vw

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dazn.domain.usecase.GetSchedulesUseCase
import kotlinx.coroutines.launch

class SchedulesViewModel(
    private val getSchedulesUseCase: GetSchedulesUseCase,
) : ViewModel() {

    fun launch() {
        viewModelScope.launch {
            getSchedulesUseCase.execute()
                .onSuccess {
                    it.forEach {
                        Log.d("****", it.toString())
                    }
                }
                .onFailure {
                    Log.d("****", "failure + ${it.cause} + ${it.message}")

                }
        }

    }
}