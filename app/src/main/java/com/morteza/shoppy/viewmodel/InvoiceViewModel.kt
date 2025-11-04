package com.morteza.shoppy.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.morteza.shoppy.model.api.invoices.Invoice
import com.morteza.shoppy.repository.invoices.InvoiceEntityRepository
import com.morteza.shoppy.repository.invoices.InvoiceRepository
import com.morteza.shoppy.ui.state.DataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class InvoiceViewModel @Inject constructor(
    private val repository: InvoiceRepository,
    private val entityRepository: InvoiceEntityRepository
) : BaseViewModel() {

    var invoices by mutableStateOf<DataUiState<Invoice>>(DataUiState())
        private set

    val localInvoices = entityRepository.getInvoices()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private var pageIndex = 0
    private val pageSize = 18
    private var endReached = false

    fun loadInvoices(userId: Long, token: String) {
        if (invoices.isLoading || endReached) return

        loadApi(state = {
            if (it.isLoading) {
                invoices = invoices.copy(isLoading = true)
            } else {
                if (it.data?.isEmpty() == true) {
                    endReached = true
                }
                pageIndex++
                val current = it.data?.toMutableList() ?: mutableListOf()
                current.addAll(invoices.data ?: listOf())
                invoices = invoices.copy(isLoading = false, data = current)
            }
        }) {
            repository.getInvoiceByUserId(
                userId,
                pageSize = pageSize,
                pageIndex = pageIndex,
                token = token
            )
        }
    }

}