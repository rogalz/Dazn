package com.example.dazn.ui.screens.event

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dazn.ui.components.ErrorScreen
import com.example.dazn.ui.components.ListItem
import com.example.dazn.ui.components.ListItemViewState
import com.example.dazn.ui.components.LoadingScreen
import com.example.dazn.ui.screens.event.vm.EventsViewModel
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun EventsScreen(viewModel: EventsViewModel = koinViewModel(), navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        EventsScreenViewState.Error -> ErrorScreen { viewModel.getEvents() }
        EventsScreenViewState.Loading -> LoadingScreen()
        is EventsScreenViewState.Success -> Success(
            uiState as EventsScreenViewState.Success, navController
        )
    }
}

@Composable
private fun Success(uiState: EventsScreenViewState.Success, navController: NavController) {
    val events = uiState.events
    LazyColumn(
        modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(events.size) {
            ListItem(viewState = events[it]) { url ->
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                navController.navigate("video/$encodedUrl")
            }
        }
    }
}

sealed class EventsScreenViewState {
    data object Loading : EventsScreenViewState()
    data object Error : EventsScreenViewState()
    data class Success(val events: List<ListItemViewState> = emptyList()) :
        EventsScreenViewState()
}


@Preview
@Composable
private fun EventScreenSuccess() {
    Surface {
        val list = buildList {
            repeat(10) {
                add(ListItem.Preview.viewState)
            }
        }
        Success(uiState = EventsScreenViewState.Success(list), NavController(LocalContext.current))
    }
}
