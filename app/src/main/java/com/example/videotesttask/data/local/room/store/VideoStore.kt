package com.example.videotesttask.data.local.room.store

import androidx.paging.PagingSource
import com.example.videotesttask.data.local.room.entities.ItemEntity

interface VideoStore {
    fun getItems(): PagingSource<Int, ItemEntity>

    suspend fun insertItems(elements: List<ItemEntity>)
}