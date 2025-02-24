package com.example.videotesttask.ui.screens.feed.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemsList(
    refresh: () -> Unit,
    content: LazyListScope.() -> Unit
) {
    val lazyListState = rememberLazyListState()
    val refreshing by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(refreshing, refresh)
    val scope = rememberCoroutineScope()
    val firstItemVisible by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex == 0
        }
    }
    Box(modifier = Modifier.fillMaxWidth()) {
        Box(Modifier.pullRefresh(pullRefreshState)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = content
            )
            PullRefreshIndicator(
                refreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                AnimatedVisibility(
                    visible = !firstItemVisible,
                    enter = slideInVertically(initialOffsetY = { it / 2 }),
                    exit = slideOutVertically(targetOffsetY = { it / 2 })
                ) {
                    IconButton(
                        modifier = Modifier.clip(CircleShape),
                        onClick = {
                            scope.launch {
                                lazyListState.animateScrollToItem(index = 0)
                            }
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = Color.LightGray.copy(alpha = 0.7f)
                        ),
                        content = {
                            Icon(
                                painter = rememberVectorPainter(image = Icons.Default.ArrowUpward),
                                contentDescription = null,
                                tint = Color.Black,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    )
                }
            }
        }
    }
}