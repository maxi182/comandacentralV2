package com.mcba.comandacentralv2.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.mcba.comandacentralv2.AppDatabase
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.R
import com.mcba.comandacentralv2.Utils.DateUtils
import com.mcba.comandacentralv2.data.DataSource
import com.mcba.comandacentralv2.data.model.ListData
import com.mcba.comandacentralv2.data.model.ProductDataEntity
import com.mcba.comandacentralv2.data.model.ProductListData
import com.mcba.comandacentralv2.data.model.ProductListDataEntity
import com.mcba.comandacentralv2.domain.InitComandaUseCase
import com.mcba.comandacentralv2.ui.base.BaseActivity
import com.mcba.comandacentralv2.ui.viewmodel.InitComandaViewModel
import com.mcba.comandacentralv2.ui.viewmodel.InitVIewModelFactory
import kotlinx.android.synthetic.main.init_comanda_activity.*
import java.io.IOException

class InitComandaActivity : BaseActivity() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            InitVIewModelFactory(
                ComandaCentralApplication(),
                InitComandaUseCase((DataSource(AppDatabase.getDatabase(this))))
            )
        ).get(InitComandaViewModel::class.java)
    }


    companion object {

        fun getNewIntent(context: Context): Intent {
            val intent = Intent(context, InitComandaActivity::class.java)
            return intent
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.init_comanda_activity

    }

    override fun setupActivity(savedInstanceState: Bundle?) {

        viewModel.getUser()

        setListeners()
        setupObservers()
        txtdate.setText(
            DateUtils.getDateFromTimestamp(
                DateUtils.getCurrentTimeInMs(),
                "EEE, MMM d"
            )
        )
    }

    private fun setupObservers() {

        viewModel.isUserFetched.observe(this, Observer { dealer ->
            txtusr.setText(dealer?.mName)
        })

        viewModel.products.observe(this, Observer { products ->

            products

        })
    }

    @Throws(IOException::class)
    fun Context.readJsonAsset(fileName: String): String {
        val inputStream = assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        return String(buffer, Charsets.UTF_8)
    }

    fun saveProducts() {

        val gson = Gson()
        var json = this.readJsonAsset("products.json")
        val productdata = gson.fromJson(json, ProductListData::class.java)
        productdata.data
        val productListdata = ProductListDataEntity(1, productdata.data)
        viewModel.saveProducts(productListdata)
    }

    fun setListeners() {

        test_save.setOnClickListener {
            saveProducts()
        }

        test_load.setOnClickListener {
            viewModel.getProducts()
        }


        linear_init_cmd.setOnClickListener {
             startMain()

        }
    }

    private fun startMain() {
        startActivity(ClienteSelectionActivity.getNewIntent(this, true));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}