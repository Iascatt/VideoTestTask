package com.example.videotesttask.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.videotesttask.data.local.room.database.VideoDatabase
import com.example.videotesttask.data.local.room.entities.ItemEntity
import com.example.videotesttask.data.local.room.mappers.toItemEntity
import com.example.videotesttask.data.local.room.store.VideoStore
import com.example.videotesttask.data.remote.VideoApi

@OptIn(ExperimentalPagingApi::class)
class VideoRemoteMediator (
    private val store: VideoStore,
    private val api: VideoApi
) : RemoteMediator<Int, ItemEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ItemEntity>
    ): MediatorResult {
        Log.d("JLTS", "mediator start")

        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) 1 else (lastItem.itemId / state.config.pageSize) + 1

                }
            }
            Log.d("JLTS", "page $page")

            val response = api.getCollection(page, state.config.pageSize)

            val items = response.body()?.collection?.items
            Log.d("JLTS", response.body().toString())
            Log.d("JLTS", response.body()?.collection?.toString() ?: "collect null")
            Log.d("JLTS", response.body()?.collection?.items?.toString() ?: " items null")



            if (items != null) {
                Log.d("JLTS", "entities " +items.map { it.toItemEntity() }.toString())


                store.insertItems(items.map { it.toItemEntity() })
                MediatorResult.Success(endOfPaginationReached = items.isEmpty())
            } else MediatorResult.Error(Exception("Collection is null"))

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}