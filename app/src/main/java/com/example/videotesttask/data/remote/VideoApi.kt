package com.example.videotesttask.data.remote

import com.example.videotesttask.data.remote.responses.CollectionResponse
import com.example.videotesttask.data.remote.responses.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface VideoApi {
    @GET("search?media_type=video")
    suspend fun getCollection(
        @Query("page") page: Int?,
        @Query("page_size") pageSize: Int?
    ): Response<VideoResponse?>

    @GET
    suspend fun getVideoFiles(@Url url: String): Response<List<String>?>
}
