package com.morteza.shoppy.model.api.customers

data class UserDto(
    var id : Long?,
    var password : String?,
    var username : String?,
    var address : String?,
    var firstName : String?,
    var lastName : String?,
    var phone : String?,
    var postalCode : String ?,
    var customerId : Long?,
    var oldPassword : String?,
    var repeatPassword : String?,
    var token : String?,
)
