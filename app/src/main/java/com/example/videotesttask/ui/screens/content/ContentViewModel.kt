package com.example.videotesttask.ui.screens.content

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.util.EventLogger
import com.example.videotesttask.domain.LoadingState
import com.example.videotesttask.domain.usecases.ContentUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ContentViewModel(
    private val contentUseCase: ContentUseCase,
) : ViewModel(), KoinComponent {

    private var _videoState: MutableStateFlow<LoadingState<List<String>>> = MutableStateFlow(LoadingState.Loading())
    var videoState: StateFlow<LoadingState<List<String>>> = _videoState.asStateFlow()
    private val exoPlayer: ExoPlayer by inject()

    fun getVideos(uri: String){
        viewModelScope.launch {
            contentUseCase.getVideos(uri)
                .collect {
                    _videoState.value = it
                }
        }
    }


    fun setMediaItem(mediaItem: MediaItem) {
        Log.d("FAFWA", "vm set")
        exoPlayer.addAnalyticsListener(EventLogger())

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