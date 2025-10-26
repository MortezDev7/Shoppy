package com.morteza.shoppy.model.api.invoices

import com.morteza.shoppy.model.api.customers.UserDto

data class PaymentTransaction(
    var items: List<InvoiceItem> = listOf(),
    var user : UserDto? = null
)
