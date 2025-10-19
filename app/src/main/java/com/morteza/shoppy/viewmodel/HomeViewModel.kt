package com.morteza.shoppy.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.morteza.shoppy.model.products.Product
import com.morteza.shoppy.model.products.ProductCategory
import com.morteza.shoppy.model.slider.Sliders
import com.morteza.shoppy.repository.products.ProductCategoryRepository
import com.morteza.shoppy.repository.products.ProductRepository
import com.morteza.shoppy.repository.slider.SliderRepository
import com.morteza.shoppy.ui.state.DataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val silderRepository: SliderRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val productRepository: ProductRepository
) : BaseViewModel() {

    var slider by mutableStateOf<DataUiState<Sliders>>(DataUiState())
        private set

    var productCategory by mutableStateOf<DataUiState<ProductCategory>>(DataUiState())
        private set

    var product by mutableStateOf<DataUiState<Product>>(DataUiState())
        private set


    init {
        loadSliders()
        loadProductCategories()
        loadAllProducts()
    }

    fun loadSliders() {
        loadApi(state = { slider = it }) {
            silderRepository.getSliders()
        }
    }

    fun loadProductCategories() {
        loadApi(state = { productCategory = it }) {
            productCategoryRepository.getCategories()
        }
    }

    fun loadAllProducts() {
        loadApi(state = {product = it}){
            productRepository.getProducts(0,6)
        }
    }

    fun loadNewProducts() {
        loadApi(state = { product = it }) {
            productRepository.getNewProducts()
        }
    }

    fun loadPopularProducts() {
        loadApi(state = {product = it}){
            productRepository.getPopularProducts()
        }
    }
}
