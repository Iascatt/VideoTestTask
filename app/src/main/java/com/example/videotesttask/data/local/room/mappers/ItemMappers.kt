package com.example.videotesttask.data.local.room.mappers

import com.example.videotesttask.data.local.room.entities.ItemEntity
import com.example.videotesttask.data.remote.responses.ItemResponse
import com.example.videotesttask.domain.models.Item

fun ItemResponse.toItemEntity(): ItemEntity = ItemEntity(
    itemId = 0,
    title = data?.get(0)?.title,
    preview = links?.find { it.rel == "preview" }?.link,
    videoFiles = link,
    description = data?.get(0)?.description
)

fun ItemEntity.toItem(): Item = Item(itemId, title, preview, videoFiles, description)