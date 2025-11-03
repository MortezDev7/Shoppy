package com.morteza.shoppy.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morteza.shoppy.dao.BasketEntityDao
import com.morteza.shoppy.dao.UserEntityDao
import com.morteza.shoppy.model.db.BasketEntity
import com.morteza.shoppy.model.db.UserEntity

@Database(version = 1, entities = [BasketEntity::class, UserEntity::class])
abstract class AppDataBase : RoomDatabase(){

    abstract fun basketDao() : BasketEntityDao

    abstract fun userDao() : UserEntityDao

}