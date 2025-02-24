package com.example.videotesttask.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingConfig.Companion.MAX_SIZE_UNBOUNDED
import androidx.paging.PagingData
import com.example.videotesttask.data.local.room.entities.ItemEntity
import com.example.videotesttask.data.local.room.store.VideoStore
import com.example.videotesttask.data.paging.VideoRemoteMediator
import com.example.videotesttask.data.remote.VideoApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class VideoRepositoryImpl (
    private val api: VideoApi,
    private val store: VideoStore
): VideoRepository {
    @OptIn(ExperimentalPagingApi::class)
    override suspend fun loadFeed(): Flow<PagingData<ItemEntity>> {
        Log.d("JLTS", "rep start")

        val pager = Pager(
            PagingConfig(
                pageSize = 5,
                maxSize = MAX_SIZE_UNBOUNDED,
                initialLoadSize = 5
            ),
            remoteMediator = VideoRemoteMediator(store, api),
            pagingSourceFactory = { store.getItems() }
        )
        Log.d("JLTS", pager.flow.toString())

        return pager.flow
    }

    override suspend fun loadVideos(uri: String): Response<List<String>?> {
        return api.getVideoFiles(uri)
    }
}