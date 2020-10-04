package com.mcba.comandacentralv2.domain

import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.DataSource
import com.mcba.comandacentralv2.data.model.ProductDataEntity
import com.mcba.comandacentralv2.data.model.ProductListDataEntity
import com.mcba.comandacentralv2.data.model.UserEntity

class InitComandaUseCase constructor(private val dataSource: DataSource) :
    InitComandaUseCaseRepository {


    override suspend fun getUser(): UseCaseResult<UserEntity> {
        return try {
            var result = dataSource.getUser()
            result?.let {
                result
            }
        } catch (ex: Exception) {
            UseCaseResult.Exception(ex)
        }
    }

    override suspend fun getProducts(): UseCaseResult<ProductListDataEntity> {
        return try {
            var result = dataSource.getProducts()
            result?.let {
                result
            }
        } catch (ex: Exception) {
            UseCaseResult.Exception(ex)
        }
    }

    override suspend fun saveProducts(products: ProductDataEntity): UseCaseResult<Boolean> {
        return try {
            var result = dataSource.saveProducts(products)
            if (result == 1L)
                UseCaseResult.Success(true)
            else
                UseCaseResult.Failure(false)
        } catch (ex: Exception) {
            UseCaseResult.Exception(ex)
        }
    }

    override suspend fun saveListProducts(products: ProductListDataEntity): UseCaseResult<Boolean> {
        return try {
            var result = dataSource.saveListProducts(products)
            if (result == 1L)
                UseCaseResult.Success(true)
            else
                UseCaseResult.Failure(false)
        } catch (ex: Exception) {
            UseCaseResult.Exception(ex)
        }
    }
}