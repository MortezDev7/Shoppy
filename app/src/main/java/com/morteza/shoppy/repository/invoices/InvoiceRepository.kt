package com.morteza.shoppy.repository.invoices

import com.morteza.shoppy.api.invoices.InvoiceApi
import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.api.invoices.Invoice
import com.morteza.shoppy.repository.base.BaseRepository
import javax.inject.Inject

class InvoiceRepository @Inject constructor(
    private val api: InvoiceApi,

    ): BaseRepository() {

    suspend fun getInvoiceById(id : Long, token: String): ApiResponse<Invoice> =
        safeApiCall {
            api.getInvoiceById(id,prepareToken(token))
        }

    suspend fun getInvoiceByUserId(userId : Long,pageIndex : Int,pageSize : Int, token: String): ApiResponse<Invoice> =
        safeApiCall {
            api.getInvoiceByUserId(userId,pageSize,pageSize,prepareToken(token))
        }

}