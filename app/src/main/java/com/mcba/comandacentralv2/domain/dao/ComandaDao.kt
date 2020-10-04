package com.mcba.comandacentralv2.domain.dao

import androidx.room.*
import com.mcba.comandacentralv2.data.model.ComandaEntity

@Dao
interface ComandaDao {

    @Query("SELECT * FROM comandaEntity")
    suspend fun getAllComandas():List<ComandaEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComanda(item: ComandaEntity):Long

    @Delete
    suspend fun deleteComanda(item: ComandaEntity)
}