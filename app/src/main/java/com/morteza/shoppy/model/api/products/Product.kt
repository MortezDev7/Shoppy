package com.morteza.shoppy.model.api.products

data class Product(
    var id : Long? = null,
    var addDate : String? = null ,
    var category: ProductCategory? = null,
    var colors: List <ProductColor?> = listOf() ,
    var description : String? = null,
    var image : String? = null,
    var price : Long? = null,
    var sizes : List<ProductSize?> = listOf(),
    var title : String? = null,
    var visitCount : Int? = null
    )
