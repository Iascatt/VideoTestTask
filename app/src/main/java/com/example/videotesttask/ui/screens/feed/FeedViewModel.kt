package com.example.videotesttask.ui.screens.feed

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.videotesttask.domain.models.Item
import com.example.videotesttask.domain.usecases.FeedUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class FeedViewModel(
    private val feedUseCase: FeedUseCase
) : ViewModel() {

    private val _feedState: MutableState<Flow<PagingData<Item>>> = mutableStateOf(emptyFlow())
    val feedState: State<Flow<PagingData<Item>>> = _feedState

    init {
        viewModelScope.launch {

            _feedState.value = feedUseCase.getItems()
                .cachedIn(viewModelScope)
            Log.d("JLTS", _feedState.toString())

        }
    }
}