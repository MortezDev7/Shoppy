package com.morteza.shoppy.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class InvoiceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val userId: Long,
    val status: String,
    val addDate: String
)