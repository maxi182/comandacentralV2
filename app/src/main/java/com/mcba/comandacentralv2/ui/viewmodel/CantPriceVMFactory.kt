package com.mcba.comandacentralv2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.domain.CantPriceUseCase

class CantPriceVMFactory(
    private val application: ComandaCentralApplication,
    private val repo: CantPriceUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CantPriceViewModel(application, repo) as T
    }
}