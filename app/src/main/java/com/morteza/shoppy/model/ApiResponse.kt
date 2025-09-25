package com.morteza.shoppy.model

data class ApiResponse <T>(
    val data : List<T>,
    val status : String,
    val message: String,
    val totalCount : Int
)
