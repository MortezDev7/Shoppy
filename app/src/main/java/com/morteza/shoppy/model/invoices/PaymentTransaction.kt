package com.morteza.shoppy.model.invoices

import com.morteza.shoppy.model.customers.UserDto

data class PaymentTransaction(
    var items: List<InvoiceItem> = listOf(),
    var user : UserDto? = null
)
