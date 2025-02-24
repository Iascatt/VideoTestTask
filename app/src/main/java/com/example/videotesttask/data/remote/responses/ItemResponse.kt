package com.example.videotesttask.data.remote.responses

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("href") val link: String?,
    @SerializedName("data") val data: List<DataResponse>?,
    @SerializedName("links") val links: List<LinkResponse>?,
)
