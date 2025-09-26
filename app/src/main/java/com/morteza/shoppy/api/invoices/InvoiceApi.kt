package com.morteza.shoppy.api.invoices

import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.invoices.Invoice
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceApi {

    @GET("invoice/{id}")
    suspend fun getInvoiceById(
        @Path("id") id : Long,
        @Header("Authorization") token : String
    ) : ApiResponse<Invoice>

    @GET("invoice/user/{userId}")
    suspend fun getInvoiceByUserId(
        @Path("userId") userId : Long,
        @Query("pageIndex") pageIndex : Int,
        @Query("pageSize") pageSize : Int,
        @Header("Authorization") token : String,
        ): ApiResponse<Invoice>
}