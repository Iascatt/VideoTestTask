package com.example.videotesttask.data.remote.responses

import com.google.gson.annotations.SerializedName

data class LinkResponse (
    @SerializedName("href") val link: String?,
    @SerializedName("rel") val rel: String?,
    @SerializedName("render") val render: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("size") val size: Int?,
    @SerializedName("height") val height: Int?
)