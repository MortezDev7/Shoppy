package com.morteza.shoppy.model.api.customers

data class UserDto(
    var id : Long? = null,
    var password : String?  = null,
    var username : String?  = null,
    var address : String?  = null,
    var firstName : String?  = null,
    var lastName : String?  = null,
    var phone : String?  = null,
    var postalCode : String ?  = null,
    var customerId : Long?  = null,
    var oldPassword : String? = "",
    var repeatPassword : String? = "",
    var token : String? = "",
)
