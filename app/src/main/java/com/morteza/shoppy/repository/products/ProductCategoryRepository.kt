package com.morteza.shoppy.repository.products

import com.morteza.shoppy.api.products.ProductCategoryApi
import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.products.ProductCategory
import com.morteza.shoppy.repository.base.BaseRepository
import javax.inject.Inject

class ProductCategoryRepository @Inject constructor(
    private val api: ProductCategoryApi,

): BaseRepository() {

    suspend fun getCategories(): ApiResponse<ProductCategory> =
        safeApiCall {
        api.getCategories()
    }

}