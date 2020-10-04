package com.mcba.comandacentralv2.domain.dao

import androidx.room.*
import com.mcba.comandacentralv2.data.model.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity")
    suspend fun getUser(): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(item: UserEntity)

    @Delete
    suspend fun deleteUser(item: UserEntity)
}