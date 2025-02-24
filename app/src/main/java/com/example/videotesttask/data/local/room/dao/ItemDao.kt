package com.example.videotesttask.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.videotesttask.data.local.room.entities.ItemEntity


@Dao
interface ItemDao {
    @Query("SELECT * FROM ItemEntity")
    fun getItems(): PagingSource<Int, ItemEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAllItems(items: List<ItemEntity>)
}