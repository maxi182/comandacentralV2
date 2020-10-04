package com.mcba.comandacentralv2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.model.*
import com.mcba.comandacentralv2.domain.InitComandaUseCase
import com.prosegur.solmobile.utils.SingleLiveEvent
import kotlinx.coroutines.*

class InitComandaViewModel (application: Application, val initComandaUseCase: InitComandaUseCase) :
    AndroidViewModel(application) {

    var isUserFetched = MutableLiveData<UserEntity>()
    var products = MutableLiveData<ProductListDataEntity>()
    var isProductStored = SingleLiveEvent<Boolean>()


    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getUser() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { initComandaUseCase.getUser() }
            when (result) {
                is UseCaseResult.Success -> isUserFetched.postValue(result.data)
                is UseCaseResult.Failure -> isUserFetched.postValue(null)
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { initComandaUseCase.getProducts()}
            when (result) {
                is UseCaseResult.Success -> products.postValue(result.data)
                is UseCaseResult.Failure -> products.postValue(null)
            }
        }
    }

    fun saveProducts(products: ProductListDataEntity) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { initComandaUseCase.saveListProducts(products) }
            when (result) {
                is UseCaseResult.Success -> isProductStored.postValue(result.data)
                is UseCaseResult.Failure -> isProductStored.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}