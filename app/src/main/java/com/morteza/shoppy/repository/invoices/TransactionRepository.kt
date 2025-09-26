package com.morteza.shoppy.repository.invoices

import com.morteza.shoppy.api.invoices.TransactionApi
import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.invoices.PaymentTransaction
import com.morteza.shoppy.repository.base.BaseRepository
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val api: TransactionApi,

    ): BaseRepository() {

    suspend fun goToPayment(data : PaymentTransaction): ApiResponse<String> =
        safeApiCall {
            api.gotoPayment(data)
        }

}