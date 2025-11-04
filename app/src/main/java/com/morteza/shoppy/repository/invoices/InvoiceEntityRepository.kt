package com.morteza.shoppy.repository.invoices

import com.morteza.shoppy.dao.InvoiceEntityDao
import com.morteza.shoppy.model.db.InvoiceEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class InvoiceEntityRepository @Inject constructor(
    private val dao : InvoiceEntityDao
) {
    fun add(data: InvoiceEntity){
        dao.add(data)
    }

    fun delete(){
        dao.deleteAllInvoices()
    }

    fun getInvoices(): Flow<List<InvoiceEntity>> {
        return dao.getInvoices()
    }
}