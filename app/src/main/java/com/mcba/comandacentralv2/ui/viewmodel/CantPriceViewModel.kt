package com.mcba.comandacentralv2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mcba.comandacentralv2.domain.CantPriceUseCase
import com.prosegur.solmobile.utils.SingleLiveEvent

class CantPriceViewModel(application: Application, val cantPriceUseCase: CantPriceUseCase) :
    AndroidViewModel(application) {

    val updateQtyText = SingleLiveEvent<Int>()

    fun setQtyText(currentValue: Int, operation: Boolean) {

        if (currentValue >= 1) {
            if (currentValue == 1 && !operation) {
                return
            } else {
                updateQtyText.postValue(if (operation) currentValue + 1 else currentValue - 1)
            }
        }

    }
}