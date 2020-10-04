package com.mcba.comandacentralv2

import android.app.Application
import android.content.Context
import com.mcba.comandacentralv2.Utils.StorageProvider


class ComandaCentralApplication : Application() {

    private var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()

        appContext = applicationContext
        StorageProvider.init(this)

    }

    fun getAppContext(): Context? {
        return appContext
    }

}