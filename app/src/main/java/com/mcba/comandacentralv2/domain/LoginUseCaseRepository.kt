package com.mcba.comandacentralv2.domain

import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.model.ComandaEntity
import com.mcba.comandacentralv2.data.model.ComandaItemEntity
import com.mcba.comandacentralv2.data.model.User
import com.mcba.comandacentralv2.data.model.UserEntity

interface LoginUseCaseRepository {

    suspend fun getLoginUser(name: String, password: String): UseCaseResult<User>
    suspend fun saveItem(item: ComandaItemEntity): UseCaseResult<Boolean>
    suspend fun getProductItems(): UseCaseResult<List<ComandaItemEntity>>
    suspend fun saveComanda(item: ComandaEntity): UseCaseResult<Boolean>
    suspend fun getComandas(): UseCaseResult<List<ComandaEntity>>
    suspend fun saveUser(item: UserEntity)
}