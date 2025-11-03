package com.morteza.shoppy.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.morteza.shoppy.model.db.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserEntityDao {

    @Insert
    fun add(data: UserEntity)
    @Query("DELETE FROM UserEntity")
    fun deleteAll()

    @Query("SELECT * FROM UserEntity LIMIT 1")
    fun get() : Flow<UserEntity?>
}