package com.example.videotesttask.ui.screens.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.ui.PlayerView
import com.example.videotesttask.R
import com.example.videotesttask.domain.LoadingState
import com.example.videotesttask.domain.models.Item

@Composable
fun ContentView(viewModel: ContentViewModel, item: Item) {
    val videosLink = item.videoFiles
    val videoState by viewModel.videoState.collectAsState()


    if (videosLink != null) {
        LaunchedEffect(videosLink) {
            viewModel.getVideos(videosLink)
        }

        when (videoState) {
            is LoadingState.Empty -> {
                Text(stringResource(R.string.empty_list))
            }

            is LoadingState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        stringResource(id = R.string.loading_error) +
                                ": " + (videoState as LoadingState.Error).message,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            is LoadingState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is LoadingState.Success -> {
                val videos = (videoState as LoadingState.Success).data
                if (videos.isNullOrEmpty()) {
                    Text("Видеофайлы не найдены")
                } else {
                    val mediaItem = remember(videos) {
                        val uri = if (videos[0].contains("https")) videos[0]
                        else videos[0].replace("http", "https")
                        MediaItem.fromUri(uri)
                    }

                    LaunchedEffect(mediaItem) {
                        if (viewModel.savedPosition == null) {
                            viewModel.setMediaItem(mediaItem)
                        } else viewModel.restorePlayer()
                    }

                    DisposableEffect(Unit) {
                        onDispose {
                            viewModel.savePlayerState()
                        }
                    }
                    AndroidView(
                        factory = { ctx ->
                            PlayerView(ctx).apply {
                                player = viewModel.getPlayer()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
            }
        }
    } else {
        Text("Ссылка на коллекцию видеофайлов не найдена")
    }
}