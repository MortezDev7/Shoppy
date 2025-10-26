package com.morteza.shoppy.model.api.invoices

import com.morteza.shoppy.model.api.customers.User

data class Invoice(
    val id : Long?,
    var addDate : String?,
    var paymentData : String?,
    var status : String?,
    var user : User? = null,
    var items : List<InvoiceItem>? = listOf()

)
