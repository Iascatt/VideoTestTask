package com.example.videotesttask.ui.screens.feed

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.koin.androidx.compose.getViewModel

class FeedScreen: Screen {
    @Composable
    override fun Content() {
        FeedView(getViewModel())
    }
}