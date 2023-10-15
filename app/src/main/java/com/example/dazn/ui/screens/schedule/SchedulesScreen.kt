package com.example.dazn.ui.screens.schedule

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SchedulesScreen() {

    Text("SchedulesScreen")

}


sealed class SchedulesScreenViewState {
    object Success : SchedulesScreenViewState()
    data object Error : SchedulesScreenViewState()
    data object Loading : SchedulesScreenViewState()
}