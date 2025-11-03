package com.morteza.shoppy.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var userId : Long? = 0,
    var username : String?,
    var address : String?,
    var firstName : String?,
    var lastName : String?,
    var phone : String?,
    var postalCode : String ?,
    var customerId : Long?,
    var token : String? = "",
)