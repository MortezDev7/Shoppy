package com.morteza.shoppy.module

import android.content.Context
import androidx.room.Room
import com.morteza.shoppy.config.AppDataBase
import com.morteza.shoppy.dao.BasketEntityDao
import com.morteza.shoppy.dao.InvoiceEntityDao
import com.morteza.shoppy.dao.UserEntityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context) : AppDataBase{
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "shoppy_db"
        ).build()
    }
    @Provides
    @Singleton
    fun provideBasketEntityDao(dataBase: AppDataBase): BasketEntityDao{
        return dataBase.basketDao()
    }

    @Provides
    @Singleton
    fun provideUserEntityDao(dataBase: AppDataBase): UserEntityDao{
        return dataBase.userDao()
    }

    @Provides
    @Singleton
    fun invoiceEntityDao(dataBase: AppDataBase): InvoiceEntityDao{
        return dataBase.invoiceDao()
    }
}