package com.example.videotesttask.ui.screens.content

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.test.core.app.ApplicationProvider
import com.example.videotesttask.domain.LoadingState
import com.example.videotesttask.domain.usecases.ContentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContentViewModel(
    private val contentUseCase: ContentUseCase,
    private val context: Context
) : ViewModel() {

    private var _videoState: MutableStateFlow<LoadingState<List<String>>> = MutableStateFlow(LoadingState.Loading())
    var videoState: StateFlow<LoadingState<List<String>>> = _videoState.asStateFlow()

    fun getVideos(uri: String){
        viewModelScope.launch {
            contentUseCase.getVideos(uri)
                .collect {
                    _videoState.value = it
                }
        }
    }
    private val exoPlayer: ExoPlayer by lazy {
        ExoPlayer.Builder(context).build()
    }
    fun setMediaItem(uri: String) {
        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    fun getPlayer(): ExoPlayer {
        return exoPlayer
    }

    override fun onCleared() {
        super.onCleared()
        exoPlayer.release()
    }
}