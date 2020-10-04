package com.mcba.comandacentralv2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.domain.ProductSelectionUseCase

class ProductSelectionVMFactory(
    private val application: ComandaCentralApplication,
    private val repo: ProductSelectionUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductSelectionViewModel(application, repo) as T
    }
}