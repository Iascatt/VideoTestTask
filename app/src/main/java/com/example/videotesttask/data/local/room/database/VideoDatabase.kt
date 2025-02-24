package com.example.videotesttask.data.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskdive.data.room.converters.Converters
import com.example.videotesttask.data.local.room.dao.ItemDao
import com.example.videotesttask.data.local.room.entities.ItemEntity

@Database(
    entities = [
        ItemEntity::class,
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class VideoDatabase : RoomDatabase() {
    abstract val itemDao: ItemDao
}