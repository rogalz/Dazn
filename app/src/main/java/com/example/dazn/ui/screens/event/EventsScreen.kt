package com.example.dazn.ui.screens.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dazn.R
import com.example.dazn.ui.components.ListItem
import com.example.dazn.ui.components.ListItemViewState
import com.example.dazn.ui.screens.event.vm.EventsViewModel
import org.koin.androidx.compose.koinViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun EventsScreen(viewModel: EventsViewModel = koinViewModel(), navController: NavController) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        EventsScreenViewState.Error -> Error { viewModel.getEvents() }
        EventsScreenViewState.Loading -> Loading()
        is EventsScreenViewState.Success -> Success(
            uiState as EventsScreenViewState.Success, navController
        )
    }
}

@Composable
private fun Success(uiState: EventsScreenViewState.Success, navController: NavController) {
    LazyColumn(
        modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(uiState.events.size) {
            ListItem(viewState = uiState.events[it]) { url ->
                val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                navController.navigate("video/$encodedUrl")
            }
        }
    }
}

@Composable
private fun Error(onErrorButtonClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.ic_error), contentDescription = null)
            Text(text = stringResource(id = R.string.error_message))

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { onErrorButtonClick.invoke() }) {
                Text(text = stringResource(id = R.string.try_again))

            }
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
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
private fun EventScreenLoading() {
    Surface {
        Loading()
    }
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

@Preview
@Composable
private fun EventScreenError() {
    Surface {
        Error {/* no-op */ }
    }
}
