package com.example.videotesttask.ui.screens.feed.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.videotesttask.R

@Composable
fun PreviewImage(
    modifier: Modifier,
    imageUrl: String?,
    cashed: Boolean = true
) {
    val context = LocalContext.current
    val placeholderImage = R.drawable.img_placeholder
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .placeholder(placeholderImage)
        .error(placeholderImage)
        .fallback(placeholderImage)
        .memoryCachePolicy(if (cashed) CachePolicy.ENABLED else CachePolicy.DISABLED)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop,
        onError = { Log.e("IMAGES LOADING", "Error when loading image: "+ it.result.throwable) },
        onLoading = { Log.e("IMAGES LOADING", "Loading image: $imageUrl") },
        onSuccess = { Log.e("IMAGES LOADING", "Loaded image successfully: $imageUrl") },
    )
}