package com.mcba.comandacentralv2.domain

import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.DataSource
import com.mcba.comandacentralv2.data.model.ProductListDataEntity

class ProductSelectionUseCase constructor(private val dataSource: DataSource) : ProductSelectionUseCaseRepository {

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
}