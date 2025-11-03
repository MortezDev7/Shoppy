package com.morteza.shoppy.model.api.invoices

import com.morteza.shoppy.model.api.products.Product

data class InvoiceItem(
    var id : Long ? = null,
    var product: Product? = null,
    var quantity : Int? = null,
    var unitPrice : Long? = null
)
