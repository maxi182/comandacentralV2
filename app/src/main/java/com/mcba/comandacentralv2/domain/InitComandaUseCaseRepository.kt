package com.mcba.comandacentralv2.domain

import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.model.ProductDataEntity
import com.mcba.comandacentralv2.data.model.ProductListDataEntity
import com.mcba.comandacentralv2.data.model.UserEntity

interface InitComandaUseCaseRepository {

   suspend fun getUser(): UseCaseResult<UserEntity>
   suspend fun getProducts(): UseCaseResult<ProductListDataEntity>
   suspend fun saveProducts(products: ProductDataEntity):  UseCaseResult<Boolean>
   suspend fun saveListProducts(products: ProductListDataEntity):  UseCaseResult<Boolean>

}