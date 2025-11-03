package com.morteza.shoppy.repository.customers

import com.morteza.shoppy.dao.UserEntityDao
import com.morteza.shoppy.model.db.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserEntityRepository @Inject constructor(
    private val dao : UserEntityDao
) {
    fun insert(data: UserEntity) {
        dao.deleteAll()
        return dao.add(data)
    }

    fun getCurrentUser(): Flow<UserEntity?> {
        return dao.get()
    }

    fun deleteAll(){
        dao.deleteAll()
    }
}