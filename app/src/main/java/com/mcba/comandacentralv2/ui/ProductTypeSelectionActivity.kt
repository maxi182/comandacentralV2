package com.mcba.comandacentralv2.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mcba.comandacentralv2.AppDatabase
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.R
import com.mcba.comandacentralv2.Utils.KEY_COMANDA_ID
import com.mcba.comandacentralv2.Utils.KEY_PRODUCT_SELECTED_PREFERENCES
import com.mcba.comandacentralv2.Utils.KEY_PRODUCT_TYPE_SELECTED
import com.mcba.comandacentralv2.Utils.StorageProvider
import com.mcba.comandacentralv2.data.DataSource
import com.mcba.comandacentralv2.data.model.Client
import com.mcba.comandacentralv2.data.model.ProductData
import com.mcba.comandacentralv2.data.model.ProductType
import com.mcba.comandacentralv2.domain.ProductSelectionUseCase
import com.mcba.comandacentralv2.ui.adapter.MainAdapter
import com.mcba.comandacentralv2.ui.base.BaseActivity
import com.mcba.comandacentralv2.ui.viewmodel.ProductSelectionVMFactory
import com.mcba.comandacentralv2.ui.viewmodel.ProductSelectionViewModel
import kotlinx.android.synthetic.main.layout_toolbar_search.*
import kotlinx.android.synthetic.main.product_selection.*

class ProductTypeSelectionActivity : BaseActivity(), MainAdapter.OnItemTypeClickListener {

    private var client: Client? = null
    private var productoData: ProductData? = null
    private var productList = ArrayList<Any>()

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ProductSelectionVMFactory(
                ComandaCentralApplication(),
                ProductSelectionUseCase((DataSource(AppDatabase.getDatabase(this))))
            )
        ).get(ProductSelectionViewModel::class.java)
    }


    companion object {

        val EXTRA_CLIENT = "client"
        val EXTRA_PRODUCT = "product"

        fun getNewIntent(
            context: Context, clientType: Client, productSelected: ProductData
        ): Intent {
            val intent = Intent(context, ProductTypeSelectionActivity::class.java)
            intent.putExtra(EXTRA_CLIENT, clientType)
            intent.putExtra(EXTRA_PRODUCT, productSelected)
            return intent
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.product_selection
    }

    override fun setupActivity(savedInstanceState: Bundle?) {

        setupToolbar()
        setupObservers()
        viewModel.getProducts()

        intent?.extras.let {
            client = intent?.getParcelableExtra(EXTRA_CLIENT) as Client
            productoData = intent?.getParcelableExtra(EXTRA_PRODUCT)

        }

        productoData?.let {
            productList.addAll(productoData!!.loteList)
        }

        txt_selection_progress.text= productoData?.descripcion

        setupRecyclerView()
        recycler_product_selection.adapter = MainAdapter(this, productList, null, null, this, null)
        recycler_product_selection.adapter!!.notifyDataSetChanged()
    }

    private fun setupObservers() {

        viewModel.products.observe(this, Observer { products ->
            products.let {
                // productList.addAll(products.mProductList)
            }
            recycler_product_selection.adapter!!.notifyDataSetChanged()
        })
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toolbarTitle = findViewById<TextView>(R.id.center_text)
        toolbarTitle.text = "Variedad"
        val comandId = StorageProvider.getPreferences().getInt(KEY_COMANDA_ID, 0)
        nbr_comanda.setText("N°: " + String.format("%05d", comandId))
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupRecyclerView() {

        val gridLayoutManager =
            GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        recycler_product_selection?.layoutManager = gridLayoutManager
        recycler_product_selection?.setHasFixedSize(true)
    }

    override fun onItemProductSelected(productType: ProductType, position: Int) {

        StorageProvider.storeObjectPreference(KEY_PRODUCT_TYPE_SELECTED, productType)
        startActivity(
            LoteSelectionActivity.getNewIntent(
                this, client!!, productoData!!, productType
            )
        )
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)

    }

}