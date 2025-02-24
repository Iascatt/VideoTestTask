package com.example.videotesttask.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["title"], unique = true)])
data class ItemEntity(
    @PrimaryKey(autoGenerate = true) val itemId: Int,
    @ColumnInfo("title") val title: String?,
    @ColumnInfo("preview") val preview: String?,
    @ColumnInfo("video_files") val videoFiles: String?,
    @ColumnInfo("description") val description: String?,
)
