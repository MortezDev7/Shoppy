package com.morteza.shoppy.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.morteza.shoppy.model.db.BasketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketEntityDao {

    @Query("SELECT * FROM BasketEntity")
    fun getAll(): Flow<List<BasketEntity>>
    @Query("SELECT * FROM BasketEntity WHERE productId = :productId AND sizeId = :sizeId AND colorId = :colorId LIMIT 1")
    fun findItem(productId: Long, sizeId: Long, colorId: Long) : BasketEntity?
    @Insert
    fun add(basketEntity: BasketEntity)
    @Query("UPDATE BasketEntity SET quantity = quantity + 1 WHERE id = :id")
    fun incrementQuantity(id: Int)
}