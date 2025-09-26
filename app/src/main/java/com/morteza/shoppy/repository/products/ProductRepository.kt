package com.morteza.shoppy.repository.products

import com.morteza.shoppy.api.products.ProductApi
import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.products.Product
import com.morteza.shoppy.repository.base.BaseRepository
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api : ProductApi
): BaseRepository() {

    suspend fun getProducts(pageIndex : Int, pageSize : Int): ApiResponse<Product> =
        safeApiCall {
            api.getProducts(pageIndex,pageSize)
        }

    suspend fun getProductsById(id : Long): ApiResponse<Product> =
        safeApiCall {
            api.getProductById(id)
        }

    suspend fun getProductsByCategoryId(id: Long,pageIndex : Int, pageSize : Int): ApiResponse<Product> =
        safeApiCall {
            api.getProductsByCategoryId(id,pageIndex,pageSize)
        }

    suspend fun getNewProducts(): ApiResponse<Product> =
        safeApiCall {
            api.getNewProducts()
        }

    suspend fun getPopularProducts(): ApiResponse<Product> =
        safeApiCall {
            api.getPopularProducts()
        }
}