package com.morteza.shoppy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.ui.state.DataUiState
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    protected fun <T> loadApi(
        state: (DataUiState<T>) -> Unit ,
        apiCall: suspend () -> ApiResponse<T>
    ) {
        state (DataUiState(isLoading = true))

        viewModelScope.launch {
            try {
                val response = apiCall()
                if (response.status != "OK") {
                    throw Exception(response.message ?: "Unknown error")
                }
                state (DataUiState(data = response.data))
            } catch (e: Exception) {
                state (DataUiState(error = e.message))
            }
        }
    }
}


