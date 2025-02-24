package com.example.videotesttask.data.remote.responses

import com.google.gson.annotations.SerializedName

data class VideoResponse (
    @SerializedName("collection") val collection: CollectionResponse?
)