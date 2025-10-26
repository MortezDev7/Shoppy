package com.morteza.shoppy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morteza.shoppy.model.api.products.Product
import com.morteza.shoppy.model.api.products.ProductColor
import com.morteza.shoppy.model.api.products.ProductSize
import com.morteza.shoppy.model.db.BasketEntity
import com.morteza.shoppy.repository.basket.BasketEntityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val repository : BasketEntityRepository
): ViewModel() {

    val basket =  repository.getAllBasketList()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),emptyList())

    fun addToBasket(product : Product?,size: ProductSize?,color: ProductColor?){
        viewModelScope.launch(Dispatchers.IO) {
            val oldItem = repository.findBasketItem(
                product?.id?:0,
                size?.id?:0,
                color?.id?:0
            )
            if (oldItem != null){
                repository.incrementQuantity(oldItem)
            }else{
                repository.insert(BasketEntity(
                    productId = product?.id!!,
                    sizeId = size?.id!!,
                    colorId = color?.id!!,
                    quantity = 1,
                    image = product.image!!,
                    price = product.price!!,
                    title = product.title,
                    colorHex = color.hexValue,
                    size = size.title
                ))
            }
        }
    }
}