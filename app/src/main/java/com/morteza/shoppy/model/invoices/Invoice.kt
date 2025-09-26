package com.morteza.shoppy.model.invoices

import com.morteza.shoppy.model.customers.User

data class Invoice(
    val id : Long?,
    var addDate : String?,
    var paymentData : String?,
    var status : String?,
    var user : User? = null,
    var items : List<InvoiceItem>? = listOf()

)
