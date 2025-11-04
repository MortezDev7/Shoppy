package com.morteza.shoppy.viewmodel

import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import com.morteza.shoppy.api.invoices.TransactionApi
import com.morteza.shoppy.model.api.customers.UserDto
import com.morteza.shoppy.model.api.invoices.InvoiceItem
import com.morteza.shoppy.model.api.invoices.PaymentTransaction
import com.morteza.shoppy.model.api.invoices.mapper.toInvoiceItem
import com.morteza.shoppy.model.db.BasketEntity
import com.morteza.shoppy.repository.basket.BasketEntityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPaymentViewModel @Inject constructor(
    private val api: TransactionApi,
    private val basketRepo: BasketEntityRepository
) : BaseViewModel() {


    fun goToPayment(
        userDto: UserDto,
        basketItems: List<BasketEntity>,
        onLoading: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: (Uri) -> Unit,
        loginVm: LoginViewModel
    ) {
        val items = mutableListOf<InvoiceItem>()

        basketItems.forEach {
            items.add(
                it.toInvoiceItem()
            )
        }
        val payTrx = PaymentTransaction(
            items = items,
            user = userDto
        )
        loadApi(state = {
            when {
                it.isLoading -> onLoading()
                it.error != null -> onError(it.error)
                it.data != null -> {
                    loginVm.login(
                        userDto.username!!,
                        userDto.password!!,
                        {},{},{}
                    )

                    viewModelScope.launch(Dispatchers.IO) {
                        basketRepo.deleteAll()
                    }
                    onSuccess(it.data[0].toUri())
                }

            }
        }) {
            api.gotoPayment(payTrx)
        }
    }
}