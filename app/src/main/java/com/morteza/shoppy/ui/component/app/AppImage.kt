package com.morteza.shoppy.ui.component.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.morteza.shoppy.ui.component.Loading

@Composable
fun AppImage(
    model: Any?,
    description: String,
    modifier: Modifier = Modifier,
            contentScale: ContentScale = ContentScale.Crop,
    ) {
    var loading by remember { mutableStateOf(false) }

    AsyncImage(
        model = model,
        contentDescription = description,
        modifier = modifier.fillMaxSize(),
        contentScale = contentScale,
        onLoading = {
            loading = true
        },
        onSuccess = {
            loading = false
        },
        onError = {
            loading = false
        }
    )
    if (loading) {
        Loading()
    }

}