package com.mcba.comandacentralv2.domain.dao

import androidx.room.*
import com.mcba.comandacentralv2.data.model.ComandaItemEntity

@Dao
interface ComandaItemDao {

    @Query("SELECT * FROM comandaItemEntity")
    suspend fun getAllComandaItems():List<ComandaItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComandaItem(item: ComandaItemEntity):Long

    @Delete
    suspend fun deleteItem(item: ComandaItemEntity)
}