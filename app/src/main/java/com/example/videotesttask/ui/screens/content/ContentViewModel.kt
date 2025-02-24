package com.example.videotesttask.ui.screens.content

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
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
        ExoPlayer.Builder(context).build().apply {
            addListener(object : Player.Listener {
                override fun onPlayerError(error: PlaybackException) {
                    Log.e("FAFWA", "Error: ${error.message}")
                }
            })
        }
    }
    fun setMediaItem(mediaItem: MediaItem) {
        Log.d("FAFWA", "vm set")
        exoPlayer.setMediaItem(mediaItem)
        Log.d("FAFWA", "vm prepare")
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