package com.morteza.shoppy.api.products


import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.api.products.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("product")
    suspend fun getProducts(
        @Query("pageIndex") pageIndex : Int,
        @Query("pageSize") pageSize : Int,
        ) : ApiResponse<Product>

    @GET("product/{id}")
    suspend fun getProductById(
        @Path("id") id : Long
    ) : ApiResponse<Product>

    @GET("product/cat/{id}")
    suspend fun getProductsByCategoryId(
        @Path("id") id : Long,
        @Query("pageIndex") pageIndex : Int,
        @Query("pageSize") pageSize : Int,
    ) : ApiResponse<Product>

    @GET("product/new")
    suspend fun getNewProducts() : ApiResponse<Product>

    @GET("product/popular")
    suspend fun getPopularProducts() : ApiResponse<Product>
}