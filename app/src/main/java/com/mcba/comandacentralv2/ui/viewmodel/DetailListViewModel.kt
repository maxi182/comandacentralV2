package com.mcba.comandacentralv2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mcba.comandacentralv2.domain.DetailListUseCase

class DetailListViewModel(application: Application, val loginUseCase: DetailListUseCase) :
    AndroidViewModel(application) {}