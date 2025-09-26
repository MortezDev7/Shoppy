package com.morteza.shoppy.model.invoices

import com.morteza.shoppy.model.products.Product

data class InvoiceItem(
    var id : Long ?,
    var product: Product? = null,
    var quantity : Int?,
    var unitPrice : Long?
)
