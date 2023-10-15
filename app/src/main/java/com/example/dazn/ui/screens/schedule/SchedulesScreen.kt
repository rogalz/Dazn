package com.example.dazn.ui.screens.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dazn.ui.components.ErrorScreen
import com.example.dazn.ui.components.ListItem
import com.example.dazn.ui.components.ListItemViewState
import com.example.dazn.ui.components.LoadingScreen
import com.example.dazn.ui.screens.schedule.vw.SchedulesViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SchedulesScreen(viewModel: SchedulesViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        SchedulesScreenViewState.Error -> ErrorScreen()
        SchedulesScreenViewState.Loading -> LoadingScreen()
        is SchedulesScreenViewState.Success -> Success(
            uiState as SchedulesScreenViewState.Success
        )
    }
}

@Composable
private fun Success(viewState: SchedulesScreenViewState.Success) {
    val schedules = viewState.schedules
    LazyColumn(
        modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(schedules.size) {
            ListItem(viewState = schedules[it])
        }
    }

}

sealed class SchedulesScreenViewState {
    data class Success(
        val schedules: List<ListItemViewState>
    ) : SchedulesScreenViewState()

    data object Error : SchedulesScreenViewState()
    data object Loading : SchedulesScreenViewState()
}