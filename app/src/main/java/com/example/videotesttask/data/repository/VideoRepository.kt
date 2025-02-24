package com.example.videotesttask.data.repository

import androidx.paging.PagingData
import com.example.videotesttask.data.local.room.entities.ItemEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface VideoRepository {
    suspend fun loadFeed(): Flow<PagingData<ItemEntity>>

    suspend fun loadVideos(uri: String): Response<List<String>?>
}
