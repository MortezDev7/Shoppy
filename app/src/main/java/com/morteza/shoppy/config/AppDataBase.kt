package com.morteza.shoppy.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.morteza.shoppy.dao.BasketEntityDao
import com.morteza.shoppy.model.db.BasketEntity

@Database(version = 1, entities = [BasketEntity::class])
abstract class AppDataBase : RoomDatabase(){

    abstract fun basketDao() : BasketEntityDao
}