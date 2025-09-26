package com.morteza.shoppy.repository.base

import com.morteza.shoppy.model.ApiResponse

open class BaseRepository {

    fun prepareToken(token : String) : String {
        var result = token

        if (!token.lowercase().startsWith("bearer")){
            result = "Bearer $token"
        }
        return result
    }

    suspend fun <T> safeApiCall(apiCall : suspend () -> ApiResponse<T>) : ApiResponse<T>{
            return try {
                apiCall()

            } catch (e : Exception){
                ApiResponse(status = "Exception", message = e.message)
            }
        }
    }
