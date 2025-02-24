package com.example.videotesttask.domain.models

data class Item(
    val itemId: Int,
    val title: String?,
    val preview: String?,
    val videoFiles: String?,
    val description: String?,
)
