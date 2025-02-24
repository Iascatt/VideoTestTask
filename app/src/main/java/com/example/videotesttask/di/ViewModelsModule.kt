package com.example.videotesttask.di

import com.example.videotesttask.ui.screens.content.ContentViewModel
import com.example.videotesttask.ui.screens.feed.FeedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { FeedViewModel(get()) }
    viewModel { ContentViewModel(get(), androidContext()) }
}