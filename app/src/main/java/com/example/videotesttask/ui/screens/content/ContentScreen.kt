package com.example.videotesttask.ui.screens.content

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.example.videotesttask.domain.models.Item
import org.koin.androidx.compose.getViewModel

class ContentScreen(private val item: Item): Screen {
    @Composable
    override fun Content() {
        ContentView(getViewModel(), item)
    }
}