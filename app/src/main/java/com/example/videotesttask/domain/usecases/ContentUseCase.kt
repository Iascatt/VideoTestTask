package com.example.videotesttask.domain.usecases

import android.util.Log
import com.example.videotesttask.data.repository.VideoRepository
import com.example.videotesttask.domain.LoadingState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContentUseCase(
    private val repository: VideoRepository
) {
    fun getVideos(uri: String): Flow<LoadingState<List<String>>> = flow {
        try {
            emit(LoadingState.Loading())
            val videos = repository.loadVideos(uri).body()
            Log.d("OWATB", "2 $videos")

            if (videos.isNullOrEmpty()) {
                emit(LoadingState.Empty())
            } else {
                emit(
                    LoadingState.Success(videos)
                )
            }
        } catch (e: Exception) {
            emit(LoadingState.Error(e.localizedMessage))
        }
    }
}