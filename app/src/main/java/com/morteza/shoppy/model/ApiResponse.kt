package com.morteza.shoppy.model

data class ApiResponse <T>(
    val data : List<T>? = listOf(),
    val status : String? = null,
    val message: String? = null,
    val totalCount : Int? =null
)
