package com.example.dazn.ui.screens.schedule

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dazn.ui.screens.schedule.vw.SchedulesViewModel

@Composable
fun SchedulesScreen() {
    val viewModel: SchedulesViewModel = viewModel()

    Text("SchedulesScreen")

}


sealed class SchedulesScreenViewState {
    object Success : SchedulesScreenViewState()
    data object Error : SchedulesScreenViewState()
    data object Loading : SchedulesScreenViewState()
}