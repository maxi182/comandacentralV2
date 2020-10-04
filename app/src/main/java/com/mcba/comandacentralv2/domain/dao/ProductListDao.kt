package com.mcba.comandacentralv2.domain.dao

import androidx.room.*
import com.mcba.comandacentralv2.data.model.ProductDataEntity
import com.mcba.comandacentralv2.data.model.ProductListDataEntity

@Dao
interface ProductListDao {

    @Query("SELECT * FROM productListDataEntity")
    suspend fun getProducts(): ProductListDataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(item: ProductListDataEntity):Long

    @Delete
    suspend fun deleteProduct(item: ProductListDataEntity)

}