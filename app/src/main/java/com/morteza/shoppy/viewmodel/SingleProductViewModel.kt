package com.morteza.shoppy.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.morteza.shoppy.model.products.Product
import com.morteza.shoppy.model.products.ProductColor
import com.morteza.shoppy.model.products.ProductSize
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

    var selectedSize by mutableStateOf<ProductSize?>(null)

    var selectedColor by mutableStateOf<ProductColor?>(null)


    fun loadProduct(id: Long) {
        loadApi(state = {
            productState = it
            product = productState.data?.firstOrNull()
            product?.sizes?.firstOrNull()?.let { size->
                selectedSize = size
            }
            product?.colors?.firstOrNull()?.let { color->
                selectedColor = color
            }
        }) {
            productRepository.getProductsById(id)
        }
    }
}