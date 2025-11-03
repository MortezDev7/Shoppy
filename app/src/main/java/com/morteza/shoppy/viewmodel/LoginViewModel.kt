package com.morteza.shoppy.viewmodel

import androidx.lifecycle.viewModelScope
import com.morteza.shoppy.model.api.customers.LoginRequestDto
import com.morteza.shoppy.model.api.customers.UserDto
import com.morteza.shoppy.model.api.customers.mapper.toEntity
import com.morteza.shoppy.repository.customers.UserEntityRepository
import com.morteza.shoppy.repository.customers.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userEntityRepository: UserEntityRepository,

    ) : BaseViewModel() {

    fun login(
        username: String,
        password: String,
        onLoading: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: (UserDto) -> Unit
    ) {
        val data = LoginRequestDto(
            username = username,
            password = password
        )

        loadApi(state = {
            when {
                it.isLoading -> onLoading()
                it.error != null -> onError(it.error)
                it.data != null -> {
                    val user = it.data[0]
                    viewModelScope.launch(Dispatchers.IO) {
                        userEntityRepository.insert(it.data[0].toEntity())
                    }
                    onSuccess(user)
                }

            }
        }) {
            userRepository.login(data)
        }
    }
}