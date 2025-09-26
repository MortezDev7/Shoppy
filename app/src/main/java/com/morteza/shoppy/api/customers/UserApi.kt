package com.morteza.shoppy.api.customers

import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.customers.User
import com.morteza.shoppy.model.customers.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApi {

    @GET("users")
    suspend fun getUserInfo(
        @Header("Authorization") token : String
    ) : ApiResponse<User>

    @PUT("user/changePassword")
    suspend fun changePassword(
        @Body user : UserDto,
        @Header("Authorization") token : String
    ) : ApiResponse<User>

    @POST("user/login")
    suspend fun login(
        @Body user : UserDto,
    ) : ApiResponse<UserDto>

    @POST("user/register")
    suspend fun register(
        @Body user : UserDto,
    ) : ApiResponse<User>

    @PUT("user/update")
    suspend fun update(
        @Body user : UserDto,
        @Header("Authorization") token : String
    ) : ApiResponse<User>


}