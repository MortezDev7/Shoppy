package com.morteza.shoppy.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morteza.shoppy.model.products.ProductCategory
import com.morteza.shoppy.model.slider.Sliders
import com.morteza.shoppy.repository.products.ProductCategoryRepository
import com.morteza.shoppy.repository.products.ProductRepository
import com.morteza.shoppy.repository.slider.SliderRepository
import com.morteza.shoppy.ui.state.DataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val silderRepository: SliderRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    var slider by mutableStateOf<DataUiState<Sliders>>(DataUiState())
        private set

    init {
        loadSliders()
    }

    fun loadSliders(){
        slider = DataUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = silderRepository.getSliders()
                if (response.status != "OK"){
                    throw Exception(response.message)
                }
                slider = DataUiState(data = response.data)
            }catch (e: Exception){
                slider = DataUiState(error = e.message)
            }
        }
    }

}