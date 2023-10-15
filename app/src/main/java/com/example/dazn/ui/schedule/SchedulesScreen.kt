package com.example.dazn.ui.schedule

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dazn.ui.schedule.vw.SchedulesViewModel

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