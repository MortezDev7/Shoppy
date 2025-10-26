package com.morteza.shoppy.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.morteza.shoppy.model.api.products.Product
import com.morteza.shoppy.repository.products.ProductRepository
import com.morteza.shoppy.ui.state.DataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {

    var product by mutableStateOf<DataUiState<Product>>(DataUiState())
        private set

    private var pageIndex = 0
    private val pageSize = 5
    private var endReached = false

    fun loadProducts(categoryId: Long) {

        if (product.isLoading || endReached) return

        loadApi(state = {
            if (it.isLoading) {
                product = product.copy(isLoading = true)
            } else {
                if (it.data?.isEmpty() == true) {
                    endReached = true
                }
                pageIndex++
                val current = it.data?.toMutableList() ?: mutableListOf()
                current.addAll(product.data ?: listOf())
                product = product.copy(isLoading = false, data = current)
            }
        }) {
            productRepository.getProductsByCategoryId(categoryId, pageIndex, pageSize)
        }

    }
}