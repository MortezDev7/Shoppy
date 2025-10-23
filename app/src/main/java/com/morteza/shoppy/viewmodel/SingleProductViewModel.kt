package com.morteza.shoppy.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.morteza.shoppy.model.products.Product
import com.morteza.shoppy.repository.products.ProductRepository
import com.morteza.shoppy.ui.state.DataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingleProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    private var productState by mutableStateOf<DataUiState<Product>>(DataUiState())

    var product by mutableStateOf<Product?>(null)

    fun loadProduct(id: Long) {
        loadApi(state = {
            productState = it
            product = productState.data?.firstOrNull()
        }) {
            productRepository.getProductsById(id)
        }
    }
}