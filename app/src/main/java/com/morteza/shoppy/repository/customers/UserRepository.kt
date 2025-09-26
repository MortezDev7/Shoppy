package com.morteza.shoppy.repository.customers

import com.morteza.shoppy.api.customers.UserApi
import com.morteza.shoppy.model.ApiResponse
import com.morteza.shoppy.model.customers.User
import com.morteza.shoppy.model.customers.UserDto
import com.morteza.shoppy.repository.base.BaseRepository
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi,

    ): BaseRepository() {

    suspend fun getUserInfo(token : String): ApiResponse<User> =
        safeApiCall {
            api.getUserInfo(prepareToken(token))
        }

    suspend fun changePassword (user : UserDto,token : String): ApiResponse<User> =
        safeApiCall {
            api.changePassword(user,prepareToken(token))
        }

    suspend fun login (user : UserDto): ApiResponse<UserDto> =
        safeApiCall {
            api.login(user)
        }

    suspend fun register (user : UserDto): ApiResponse<User> =
        safeApiCall {
            api.register(user)
        }

    suspend fun update (user : UserDto,token: String): ApiResponse<User> =
        safeApiCall {
            api.update(user,prepareToken(token))
        }

}