package com.example.dazn.ui.screens.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun VideoPlayer() {
    // This is the official way to access current context from Composable functions
    val context = LocalContext.current
    // Do not recreate the player everytime this Composable commits
//    val exoPlayer = remember(context) {
//
////        ExoPlayer.Builder(context).build().apply {
////
////            val source = ProgressiveMediaSource.Factory(DefaultDataSource.Factory(context))
////                .createMediaSource(
////                    MediaItem.Builder()
////                        .setUri(
////                            Uri.parse("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
////                        ).build()
////                )
////
////            this.prepare(source)
////        }
//    }


}