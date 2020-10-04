package com.mcba.comandacentralv2.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mcba.comandacentralv2.AppDatabase
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.R
import com.mcba.comandacentralv2.Utils.KEY_COMANDA_ID
import com.mcba.comandacentralv2.Utils.StorageProvider
import com.mcba.comandacentralv2.data.DataSource
import com.mcba.comandacentralv2.data.model.Client
import com.mcba.comandacentralv2.data.model.Lote
import com.mcba.comandacentralv2.data.model.ProductData
import com.mcba.comandacentralv2.data.model.ProductType
import com.mcba.comandacentralv2.domain.CantPriceUseCase
import com.mcba.comandacentralv2.ui.base.BaseActivity
import com.mcba.comandacentralv2.ui.viewmodel.CantPriceVMFactory
import com.mcba.comandacentralv2.ui.viewmodel.CantPriceViewModel
import kotlinx.android.synthetic.main.cant_prince_activity.*
import kotlinx.android.synthetic.main.layout_toolbar_search.*


class CantPriceActivity : BaseActivity(), View.OnClickListener, View.OnLongClickListener {

    private var productoData: ProductData? = null
    private var client: Client? = null
    private var productType: ProductType? = null
    private var lote: Lote? = null

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            CantPriceVMFactory(
                ComandaCentralApplication(),
                CantPriceUseCase((DataSource(AppDatabase.getDatabase(this))))
            )
        ).get(CantPriceViewModel::class.java)
    }

    companion object {
        const val INITIAL_QTY = "1"
        const val INITIAL_PRICE = "0"

        val EXTRA_CLIENT = "client"
        val EXTRA_PRODUCT = "product"
        val EXTRA_PRODUCT_TYPE = "product_type"
        val EXTRA_PRODUCT_LOTE = "product_lote"

        fun getNewIntent(
            context: Context,
            clientType: Client,
            productSelected: ProductData,
            productTypeSelected: ProductType,
            lote: Lote
        ): Intent {
            val intent = Intent(context, CantPriceActivity::class.java)
            intent.putExtra(EXTRA_CLIENT, clientType)
            intent.putExtra(EXTRA_PRODUCT, productSelected)
            intent.putExtra(EXTRA_PRODUCT_TYPE, productTypeSelected)
            intent.putExtra(EXTRA_PRODUCT_LOTE, lote)
            return intent
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.cant_prince_activity
    }

    override fun setupActivity(savedInstanceState: Bundle?) {

        setupToolbar()
        setupObservers()
        cant_edit_text.setText(INITIAL_QTY)
        validateNotZero()

        intent?.extras.let {
            client = intent?.getParcelableExtra(EXTRA_CLIENT) as Client
            productoData = intent?.getParcelableExtra(EXTRA_PRODUCT)
            productType = intent?.getParcelableExtra(EXTRA_PRODUCT_TYPE)
            lote = intent?.getParcelableExtra(EXTRA_PRODUCT_LOTE)
        }

        txt_selection_progress.text= productoData?.descripcion+ "-> "+productType?.descripcion+ "-> "+ lote?.descripcion

        amount_edittext.setInputType(InputType.TYPE_CLASS_PHONE)

        img_btn_add.setOnClickListener(this)
        img_btn_minus.setOnClickListener(this)
        img_btn_minus.setOnLongClickListener(this)

        cant_edit_text.requestFocus()
    }

    private fun setupObservers() {


        viewModel.updateQtyText.observe(this, Observer { qty ->

            cant_edit_text.setText(qty.toString())
        })

    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toolbarTitle = findViewById<TextView>(R.id.center_text)
        val comandId = StorageProvider.getPreferences().getInt(KEY_COMANDA_ID, 0)
        nbr_comanda.setText("N°: " + String.format("%05d", comandId))
        toolbarTitle.text = "Cantidad y Precio"
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun validateNotZero() {

        cant_edit_text.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {


            }

            override fun onTextChanged(str: CharSequence, i: Int, i1: Int, i2: Int) {
                if (str.isNotEmpty() && str.toString().toInt() < 1) {
                    cant_edit_text.setText(INITIAL_QTY)
                }

            }

            override fun afterTextChanged(editable: Editable) {
                cant_edit_text.text?.length?.let { cant_edit_text.setSelection(it) }

//                 if(editable.isEmpty()){
//                     cant_edit_text.setText(INITIAL_QTY)
//                     cant_edit_text.text?.length?.let { cant_edit_text.setSelection(it) }
//                 }

            }
        })

        amount_edittext.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {


            }

            override fun onTextChanged(str: CharSequence, i: Int, i1: Int, i2: Int) {


            }

            override fun afterTextChanged(editable: Editable) {
                    if(editable.isEmpty()){
                        amount_edittext.setText("0.00")
                    }


            }
        })

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.img_btn_add -> if (cant_edit_text?.text?.length!! > 0) {
                viewModel.setQtyText(cant_edit_text.text.toString().toInt(), true)

            } else {
                cant_edit_text.setText(INITIAL_QTY)
            }
            R.id.img_btn_minus -> if (cant_edit_text.getText()?.length!! > 0) {
                viewModel.setQtyText(cant_edit_text.text.toString().toInt(), false)
            }

            else -> {
            }
        }
    }

    override fun onLongClick(v: View?): Boolean {

        when (v!!.id) {
            R.id.img_btn_minus -> {
                cant_edit_text?.setText(INITIAL_QTY)
            }
            else -> {
            }
        }
        return true
    }


}