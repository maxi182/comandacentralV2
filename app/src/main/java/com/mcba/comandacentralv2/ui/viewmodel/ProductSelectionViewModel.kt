package com.mcba.comandacentralv2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.model.ProductListDataEntity
import com.mcba.comandacentralv2.domain.ProductSelectionUseCase
import kotlinx.coroutines.*

class ProductSelectionViewModel (application: Application, val initComandaUseCase: ProductSelectionUseCase) :
    AndroidViewModel(application) {

    var products = MutableLiveData<ProductListDataEntity>()

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun getProducts() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { initComandaUseCase.getProducts()}
            when (result) {
                is UseCaseResult.Success -> products.postValue(result.data)
                is UseCaseResult.Failure -> products.postValue(null)
            }
        }
    }
}