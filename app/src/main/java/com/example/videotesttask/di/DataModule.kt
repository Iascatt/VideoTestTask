package com.example.videotesttask.di

import androidx.room.Room
import com.example.videotesttask.data.local.room.database.VideoDatabase
import com.example.videotesttask.data.local.room.store.VideoStore
import com.example.videotesttask.data.local.room.store.VideoStoreImpl
import com.example.videotesttask.data.remote.VideoApi
import com.example.videotesttask.data.repository.VideoRepository
import com.example.videotesttask.data.repository.VideoRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<VideoDatabase> {
        Room.databaseBuilder(
            androidContext(),
            VideoDatabase::class.java, "video-db"
        ).build()
    }
    single<VideoStore> { VideoStoreImpl(get()) }
    single<VideoRepository> { (get()) }

    single<VideoRepository> {

        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://images-api.nasa.gov/")
            .client(okHttpClient)
            .build()
            .create(VideoApi::class.java)

        VideoRepositoryImpl(api, get())
    }
}