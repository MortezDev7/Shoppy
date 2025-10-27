package com.morteza.shoppy.repository.basket

import com.morteza.shoppy.dao.BasketEntityDao
import com.morteza.shoppy.model.db.BasketEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketEntityRepository @Inject constructor(
    private val dao: BasketEntityDao
) {
    fun getAllBasketList(): Flow<List<BasketEntity>> {
        return dao.getAll()
    }
     fun findBasketItem(productId: Long, sizeId: Long, colorId: Long): BasketEntity? {
        return dao.findItem(productId, sizeId, colorId)
    }

    fun incrementQuantity(item: BasketEntity) {
        dao.incrementQuantity(item.id)
    }

    fun insert(item: BasketEntity) {
        dao.add(item)
    }

    fun delete(productId: Long, sizeId: Long, colorId: Long){
        dao.deleteByIds(productId, sizeId, colorId)
    }



}