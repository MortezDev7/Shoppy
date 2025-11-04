package com.morteza.shoppy.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.morteza.shoppy.model.db.InvoiceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InvoiceEntityDao {

    @Query("SELECT * FROM InvoiceEntity")
    fun getInvoices(): Flow<List<InvoiceEntity>>
    @Query("DELETE FROM InvoiceEntity")
    fun deleteAllInvoices()

    @Insert
    fun add(invoiceEntity: InvoiceEntity)
}