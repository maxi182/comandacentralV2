package com.mcba.comandacentralv2.ui

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.mcba.comandacentralv2.AppDatabase
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.R
import com.mcba.comandacentralv2.data.DataSource
import com.mcba.comandacentralv2.data.model.Client
import com.mcba.comandacentralv2.domain.DetailListUseCase
import com.mcba.comandacentralv2.ui.base.BaseActivity
import com.mcba.comandacentralv2.ui.viewmodel.DetailListViewModel
import com.mcba.comandacentralv2.ui.viewmodel.DetailListViewModelFactory
import kotlinx.android.synthetic.main.detail_list_activity.*
import kotlinx.android.synthetic.main.init_comanda_activity.*
import kotlinx.android.synthetic.main.layout_toolbar_search.*


class DetailListActivity : BaseActivity() {

    private var client: Client? = null
    private val viewModel by lazy {
        ViewModelProvider(
            this,
            DetailListViewModelFactory(
                ComandaCentralApplication(),
                DetailListUseCase((DataSource(AppDatabase.getDatabase(this))))
            )
        ).get(DetailListViewModel::class.java)
    }

    companion object {

        val EXTRA_CLIENT = "client"

        fun getNewIntent(context: Context, initialGenerate: Client): Intent {
            val intent = Intent(context, DetailListActivity::class.java)
            intent.putExtra(EXTRA_CLIENT, initialGenerate)
            return intent
        }
    }

    override fun getLayoutResource(): Int {
        return R.layout.detail_list_activity
    }

    private fun setupToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val nComanda = findViewById<TextView>(R.id.nbr_comanda)
        nComanda.setText("N°00001")
        val mCancel = findViewById<TextView>(R.id.right_text)
        mCancel.setText(R.string.canceler)

        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun setupActivity(savedInstanceState: Bundle?) {

        intent?.extras.let {
            client = intent.getParcelableExtra(EXTRA_CLIENT) as Client
            txt_client.append(client?.name)
        }

        setupToolbar()
        setlisteners()

        confirmarMesaje(
            this@DetailListActivity,
            R.string.dialog_title_back,
            R.string.dialog_message_back_detail,
            false
        ) { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    startActivity(InitComandaActivity.getNewIntent(this));
                    this.finish()
                }
                DialogInterface.BUTTON_NEGATIVE -> {
                }
            }
        }
    }

    private fun setlisteners() {

        right_text.setOnClickListener {
            confirmarMesaje(
                this@DetailListActivity,
                R.string.dialog_title_back,
                R.string.dialog_message_back_detail,
                false
            ) { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        startActivity(InitComandaActivity.getNewIntent(this));
                        this.finish()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                    }
                }
            }

        }

        btn_add_item.setOnClickListener {


        }

    }
}