package com.morteza.shoppy.api.invoices

import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.invoices.PaymentTransaction
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {

    @POST("trx/gotoPayment")
    suspend fun gotoPayment(
        @Body data : PaymentTransaction
    ): ApiResponse<String>
}