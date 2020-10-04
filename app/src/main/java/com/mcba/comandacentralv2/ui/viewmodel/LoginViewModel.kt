package com.mcba.comandacentralv2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mcba.comandacentralv2.api.UseCaseResult
import com.mcba.comandacentralv2.data.model.ComandaEntity
import com.mcba.comandacentralv2.data.model.ComandaItemEntity
import com.mcba.comandacentralv2.data.model.User
import com.mcba.comandacentralv2.data.model.UserEntity
import com.mcba.comandacentralv2.domain.LoginUseCase
import com.prosegur.solmobile.utils.SingleLiveEvent
import kotlinx.coroutines.*

class LoginViewModel(application: Application, val loginUseCase: LoginUseCase) :
    AndroidViewModel(application) {

    val showError = SingleLiveEvent<String>()
    var isUserFetched = MutableLiveData<User>()
    var isItemStored = SingleLiveEvent<Boolean>()
    var items = SingleLiveEvent<List<ComandaItemEntity>>()
    var comandasItems = SingleLiveEvent<List<ComandaEntity>>()


    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun fetchLoginUser() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { loginUseCase.getLoginUser("", "") }
            when (result) {
                is UseCaseResult.Success -> isUserFetched.postValue(result.data)
                is UseCaseResult.Exception -> showError.postValue(result.exception.message)
            }
        }
    }

    fun saveUser(user: UserEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { loginUseCase.saveUser(user) }
        }
    }

    fun saveItem(item: ComandaItemEntity) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { loginUseCase.saveItem(item) }
            when (result) {
                is UseCaseResult.Success -> isItemStored.postValue(result.data)
                is UseCaseResult.Failure -> isItemStored.postValue(false)
            }
        }
    }

    fun getItems() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { loginUseCase.getProductItems() }
            when (result) {
                is UseCaseResult.Success -> items.postValue(result.data)
                is UseCaseResult.Failure -> items.postValue(null)
            }
        }
    }


    fun saveComanda(comanda: ComandaEntity) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { loginUseCase.saveComanda(comanda) }
            when (result) {
                is UseCaseResult.Success -> isItemStored.postValue(result.data)
                is UseCaseResult.Failure -> isItemStored.postValue(false)
            }
        }
    }


    fun getComandas() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) { loginUseCase.getComandas() }
            when (result) {
                is UseCaseResult.Success -> comandasItems.postValue(result.data)
                is UseCaseResult.Failure -> comandasItems.postValue(null)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
