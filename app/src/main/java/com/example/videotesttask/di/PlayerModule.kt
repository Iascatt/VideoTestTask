package com.example.videotesttask.di

import android.util.Log
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val playerModule = module {
    single<ExoPlayer> {
        ExoPlayer.Builder(androidContext()).build()
            .apply {
                addListener(object : Player.Listener {
                    override fun onPlayerError(error: PlaybackException) {
                        Log.e("FAFWA", "Error: ${error.message}")
                    }
                })
            }
    }
}