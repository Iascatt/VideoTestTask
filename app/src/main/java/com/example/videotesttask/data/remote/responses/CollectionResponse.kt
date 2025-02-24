package com.example.videotesttask.data.remote.responses

import com.google.gson.annotations.SerializedName

data class CollectionResponse (
    @SerializedName("href") val href: String?,
    @SerializedName("items") val items: List<ItemResponse>?,
)