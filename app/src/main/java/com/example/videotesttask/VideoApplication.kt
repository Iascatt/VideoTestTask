package com.example.videotesttask

import android.app.Application
import com.example.videotesttask.di.dataModule
import com.example.videotesttask.di.useCasesModule
import com.example.videotesttask.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class VideoApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@VideoApplication)
            modules(dataModule, useCasesModule, viewModelsModule)
        }
    }
}