package com.example.videotesttask.domain.usecases

import androidx.paging.map
import com.example.videotesttask.data.local.room.mappers.toItem
import com.example.videotesttask.data.repository.VideoRepository
import kotlinx.coroutines.flow.map

class FeedUseCase (
    private val repository: VideoRepository
) {
    suspend fun getItems() = repository.loadFeed().map { pagingData ->
        pagingData.map { it.toItem() }
    }

}