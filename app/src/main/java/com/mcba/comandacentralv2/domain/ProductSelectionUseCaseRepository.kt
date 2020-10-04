package com.mcba.comandacentralv2.domain

import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.model.ProductListDataEntity

interface ProductSelectionUseCaseRepository {
    suspend fun getProducts(): UseCaseResult<ProductListDataEntity>
}