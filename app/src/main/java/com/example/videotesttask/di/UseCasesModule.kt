package com.example.videotesttask.di

import com.example.videotesttask.domain.usecases.ContentUseCase
import com.example.videotesttask.domain.usecases.FeedUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single { FeedUseCase(get()) }
    single { ContentUseCase(get()) }
}