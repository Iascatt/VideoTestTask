package com.example.videotesttask.data.local.room.store

import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.paging.PagingSource
import com.example.videotesttask.data.local.room.database.VideoDatabase
import com.example.videotesttask.data.local.room.entities.ItemEntity

class VideoStoreImpl (
    database: VideoDatabase
): VideoStore {
    private val items = database.itemDao

    override fun getItems(): PagingSource<Int, ItemEntity> {
        return items.getItems()
    }

    override suspend fun insertItems(elements: List<ItemEntity>) {
        Log.d("JLTS", "store $elements")
        try {
            items.insertAllItems(elements)
        } catch (e: SQLiteConstraintException) {
            Log.e("FAFWA", "Duplicate item(s)")
        }
    }
}
