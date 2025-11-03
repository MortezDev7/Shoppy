package com.morteza.shoppy.model.api.customers.mapper

import com.morteza.shoppy.model.api.customers.UserDto
import com.morteza.shoppy.model.db.UserEntity

fun UserDto.toEntity() : UserEntity{
    return UserEntity(
        username = this.username,
        address = this.address,
        firstName = this.firstName,
        lastName = this.lastName,
        phone = this.phone,
        postalCode = this.postalCode,
        customerId = this.customerId,
        userId = this.id,
        token = this.token
    )
}