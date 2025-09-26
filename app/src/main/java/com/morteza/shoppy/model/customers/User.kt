package com.morteza.shoppy.model.customers

data class User(
    var id : Long?,
    var password : String?,
    var username : String?,
    var customer: Customer? = null,

)
