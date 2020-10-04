package com.mcba.comandacentralv2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.domain.DetailListUseCase

class DetailListViewModelFactory(
    private val application: ComandaCentralApplication,
    private val repo: DetailListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailListViewModel(application, repo) as T
    }
}