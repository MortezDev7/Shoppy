package com.morteza.shoppy.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.morteza.shoppy.ui.state.DataUiState

@Composable
fun <T> DataUiStateHandler(
    state: DataUiState<T>,
    modifier: Modifier = Modifier,
    loadingHeight: Dp = 200.dp,
    errorHeight: Dp = 200.dp,
    loadingContent: @Composable (() -> Unit)? = null,
    errorContent: @Composable ((String) -> Unit)? = null,
    content: @Composable (List<T>) -> Unit
) {
    Box(modifier = modifier) {
        when {
            state.isLoading -> {
                loadingContent?.invoke() ?: Loading(
                    Modifier
                        .fillMaxWidth()
                        .height(loadingHeight)
                )
            }

            state.error != null -> {
                errorContent?.invoke(state.error) ?: ErrorBox(
                    state.error,
                    modifier = Modifier.height(errorHeight)
                )
            }

            state.data != null -> {
                content(state.data)
            }
        }
    }
}

