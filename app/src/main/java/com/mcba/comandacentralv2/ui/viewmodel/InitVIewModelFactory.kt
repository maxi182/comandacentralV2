package com.mcba.comandacentralv2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.domain.InitComandaUseCase

class InitVIewModelFactory (
    private val application: ComandaCentralApplication,
    private val repo: InitComandaUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InitComandaViewModel(application, repo) as T
    }
}