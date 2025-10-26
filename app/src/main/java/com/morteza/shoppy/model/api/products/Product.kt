package com.morteza.shoppy.model.api.products

data class Product(
    var id : Long?,
    var addDate : String?,
    var category: ProductCategory? = null,
    var colors: List <ProductColor?> = listOf(),
    var description : String?,
    var image : String?,
    var price : Long?,
    var sizes : List<ProductSize?> = listOf(),
    var title : String?,
    var visitCount : Int?
    )
