package com.mcba.comandacentralv2.domain

import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.DataSource
import com.mcba.comandacentralv2.data.model.*

class LoginUseCase constructor(private val dataSource: DataSource) : LoginUseCaseRepository {

    override suspend fun getLoginUser(user: String, password: String): UseCaseResult<User> {

        return try {
            var result = dataSource.getLogin(user, password)
            UseCaseResult.Success(result!!)
        } catch (ex: Exception) {
            UseCaseResult.Exception(ex)
        }
    }

    override suspend fun saveItem(item: ComandaItemEntity): UseCaseResult<Boolean> {
        return try {
            var result = dataSource.insertComandaItem(item)
            if (result == 1L)
                UseCaseResult.Success(true)
            else
                UseCaseResult.Failure(false)
        } catch (ex: Exception) {
            UseCaseResult.Exception(ex)
        }
    }

    override suspend fun saveUser(item: UserEntity) {
           dataSource.saveLogin(item)
    }

    override suspend fun saveComanda(comanda: ComandaEntity): UseCaseResult<Boolean> {

        return try {
            var result = dataSource.insertComanda(comanda)
            if (result == 1L)
                UseCaseResult.Success(true)
            else
                UseCaseResult.Failure(false)
        } catch (ex: Exception) {
            UseCaseResult.Exception(ex)
        }
    }


    override suspend fun getProductItems(): UseCaseResult<List<ComandaItemEntity>> {

        return try {
            var result = dataSource.getComandaItems()
            result?.let {
                result
            }
        } catch (ex: Exception) {
            UseCaseResult.Exception(ex)
        }
    }

    override suspend fun getComandas(): UseCaseResult<List<ComandaEntity>> {

        return try {
            var result = dataSource.getComandas()
            result?.let {
                result
            }
        } catch (ex: Exception) {
            UseCaseResult.Exception(ex)
        }
    }
}