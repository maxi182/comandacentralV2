package com.mcba.comandacentralv2.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.mcba.comandacentralv2.R
import com.mcba.comandacentralv2.Utils.KEY_COMANDA_ID
import com.mcba.comandacentralv2.Utils.StorageProvider
import com.mcba.comandacentralv2.data.model.Client
import com.mcba.comandacentralv2.ui.base.BaseActivity
import kotlinx.android.synthetic.main.client_selection.*
import kotlinx.android.synthetic.main.layout_toolbar_search.*


class ClienteSelectionActivity : BaseActivity() {

    companion object {

        val EXTRA_INITIAL_GENERATION = "EXTRA_INITIAL_GENERATION"

        fun getNewIntent(context: Context, initialGenerate: Boolean): Intent {
            val intent = Intent(context, ClienteSelectionActivity::class.java)
            intent.putExtra(EXTRA_INITIAL_GENERATION, initialGenerate)
            return intent
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.client_selection
    }

    override fun setupActivity(savedInstanceState: Bundle?) {

        cardview_cash.isClickable = true

        cardview_cash.setOnClickListener {
            val client = Client(-1, "EFECTIVO")
            StorageProvider.savePreferences(KEY_COMANDA_ID, 14)

            startActivity(ProductSelectionActivity.getNewIntent(this, client))
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }

}