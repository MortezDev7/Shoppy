package com.morteza.shoppy.model.api.invoices.mapper

import com.morteza.shoppy.model.api.invoices.InvoiceItem
import com.morteza.shoppy.model.api.products.Product
import com.morteza.shoppy.model.api.products.ProductColor
import com.morteza.shoppy.model.api.products.ProductSize
import com.morteza.shoppy.model.db.BasketEntity

fun BasketEntity.toInvoiceItem(): InvoiceItem{
    return InvoiceItem(
        product = Product(
            id = this.productId,
            colors = listOf(ProductColor(id = this.colorId)),
            sizes = listOf(ProductSize(id = this.sizeId)),
        ),
        quantity = this.quantity
    )
}