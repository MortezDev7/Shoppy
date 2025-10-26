package com.morteza.shoppy.model.api.customers

data class User(
    var id : Long?,
    var password : String?,
    var username : String?,
    var customer: Customer? = null,

)
