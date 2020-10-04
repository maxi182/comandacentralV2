package com.mcba.comandacentralv2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.domain.LoginUseCase

class LoginViewModelFactory(
    private val application: ComandaCentralApplication,
    private val repo: LoginUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(application, repo) as T
    }
}