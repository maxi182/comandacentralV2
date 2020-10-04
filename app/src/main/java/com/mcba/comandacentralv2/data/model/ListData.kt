package com.mcba.comandacentralv2.data.model

import java.util.*

class ListData<T> {
    var data: List<T> = ArrayList()
    var codigoError = Int
    var errorMessage: String? = null
}
