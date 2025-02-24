package com.example.videotesttask.ui.screens.feed

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.videotesttask.R
import com.example.videotesttask.ui.screens.feed.components.ItemsList
import com.example.videotesttask.ui.screens.feed.components.PreviewCard

@Composable
fun FeedView(viewModel: FeedViewModel) {

    val previews = viewModel.feedState.value.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text("Список видео NASA")
        ItemsList(
            refresh = { previews.refresh() }
        ) {
            previews.let {
                if (it.loadState.refresh is LoadState.NotLoading) {
                    items(previews.itemCount) { index ->
                        if (previews[index] != null) {
                            Log.d("JLTS", "fview: ${previews[index]!!}")
                            PreviewCard(previews[index]!!)
                        }
                    }
                    if (it.loadState.append is LoadState.Loading) {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                    if (it.loadState.append is LoadState.Error) {
                        item {
                            Text(
                                stringResource(R.string.loading_error) +
                                        ": " + (it.loadState.append as LoadState.Error).error
                            )
                            Button(onClick = { it.retry() }) {
                                Text(stringResource(R.string.retry))
                            }
                        }
                    }
                } else if (it.loadState.refresh is LoadState.Loading) {
                    item {
                        Box(modifier = Modifier.fillParentMaxSize()) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                } else if (it.loadState.refresh is LoadState.Error) {
                    item {
                        Box(modifier = Modifier.fillParentMaxSize()) {
                            Text(
                                stringResource(id = R.string.loading_error) +
                                        ": " + (it.loadState.refresh as LoadState.Error).error,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}