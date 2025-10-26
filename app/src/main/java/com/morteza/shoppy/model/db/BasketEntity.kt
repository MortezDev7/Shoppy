package com.morteza.shoppy.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Long,
    val colorId: Long,
    val sizeId: Long,
    val quantity : Int,
    val title: String?,
    val price: Long?,
    val image : String?,
    val colorHex : String?,
    val size : String?

)