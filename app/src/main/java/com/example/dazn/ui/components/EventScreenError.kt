package com.example.dazn.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dazn.R

@Composable
fun ErrorScreen(onErrorButtonClick: (() -> Unit)? = null) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = R.drawable.ic_error),
                contentDescription = null
            )
            Text(text = stringResource(id = R.string.error_message))

            onErrorButtonClick?.let {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onErrorButtonClick.invoke() }) {
                    Text(text = stringResource(id = R.string.try_again))

                }
            }
        }
    }
}

@Preview
@Composable
private fun EventScreenError() {
    Surface {
        ErrorScreen {/* no-op */ }
    }
}

@Preview
@Composable
private fun EventScreenErrorWithoutButton() {
    Surface {
        ErrorScreen()
    }
}
