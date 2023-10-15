package com.example.dazn.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dazn.R
import com.example.dazn.ui.theme.Typography

@Composable
fun ListItem(viewState: ListItemViewState) {
    Row(
        Modifier
            .height(72.dp)
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.06f), shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Image(viewState.imageUrl)
        Spacer(modifier = Modifier.width(16.dp))
        Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text(
                    text = viewState.title,
                    style = Typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = viewState.description,
                    style = Typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                style = Typography.bodySmall,
                text = viewState.date
            )
        }
    }
}

@Composable
private fun Image(url: String) {
    AsyncImage(
        modifier = Modifier.fillMaxHeight(),
        model = url,
        contentDescription = null,
        placeholder = painterResource(id = R.drawable.ic_downloading),
        error = painterResource(id = R.drawable.image_error),
    )
}

data class ListItemViewState(
    val imageUrl: String,
    val title: String,
    val description: String,
    val date: String,
    val videoUrl: String? = null
)

@Preview
@Composable
fun ListItemPreview() {
    Surface {
        Column {
            ListItem(viewState = ListItem.Preview.viewState)
            ListItem(viewState = ListItem.Preview.viewState)
            ListItem(viewState = ListItem.Preview.viewState)

        }
    }
}

object ListItem {
    object Preview {
        val viewState = ListItemViewState(
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/311471173073_image-header_pDach_1554571998000.jpeg?alt=media&token=a69da8e4-d2d1-45f0-a005-977311981d66",
            title = "Rockets @ Thunder",
            description = "NBA",
            date = "2023-10-15T10:18:38.272Z",
            videoUrl = "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media",
        )
    }
}