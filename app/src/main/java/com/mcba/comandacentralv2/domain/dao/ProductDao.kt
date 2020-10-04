package com.mcba.comandacentralv2.domain.dao

import androidx.room.*
import com.mcba.comandacentralv2.data.model.ProductDataEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM productDataEntity")
    suspend fun getProducts(): ProductDataEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(item: ProductDataEntity):Long

    @Delete
    suspend fun deleteProduct(item: ProductDataEntity)
}