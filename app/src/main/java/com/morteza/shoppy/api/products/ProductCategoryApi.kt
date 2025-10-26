package com.morteza.shoppy.api.products

import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.api.products.ProductCategory
import retrofit2.http.GET

interface ProductCategoryApi {

    @GET("productCategory")
    suspend fun getCategories() : ApiResponse<ProductCategory>
}