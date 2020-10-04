package com.mcba.comandacentralv2.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mcba.comandacentralv2.AppDatabase
import com.mcba.comandacentralv2.ComandaCentralApplication
import com.mcba.comandacentralv2.R
import com.mcba.comandacentralv2.data.DataSource
import com.mcba.comandacentralv2.data.model.ComandaEntity
import com.mcba.comandacentralv2.data.model.ComandaItemEntity
import com.mcba.comandacentralv2.data.model.UserEntity
import com.mcba.comandacentralv2.domain.LoginUseCase
import com.mcba.comandacentralv2.ui.base.BaseActivity
import com.mcba.comandacentralv2.ui.viewmodel.LoginViewModel
import com.mcba.comandacentralv2.ui.viewmodel.LoginViewModelFactory
import kotlinx.android.synthetic.main.login_activity.*


class LoginActivity : BaseActivity() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            LoginViewModelFactory(
                ComandaCentralApplication(),
                LoginUseCase((DataSource(AppDatabase.getDatabase(this))))
            )
        ).get(LoginViewModel::class.java)
    }

    override fun getLayoutResource(): Int {
        return R.layout.login_activity
    }

    override fun setupActivity(savedInstanceState: Bundle?) {

        setListeners()
        setupObservers()


    }

    private fun saveUser() {
        val user = UserEntity(1,"maxii","owner")
        viewModel.saveUser(user)

//        val productItem = ComandaProductItemEntity(null, 5, 6, "")
//        val comandaItem = ComandaItemEntity(null, 1, productItem, 2.0, 10.0, 20.0)
//
//        viewModel.saveItem(comandaItem)

    }

    private fun saveComanda(comandaItems: List<ComandaItemEntity>) {

        val comanda =
            ComandaEntity(
                null,
                "04/09/2020",
                false,
                false,
                2,
                "Juan",
                32326456,
                5.0,
                500.0,
                comandaItems
            )

        viewModel.saveComanda(comanda)
    }

    private fun setListeners() {

        done_btn_textview.setOnClickListener(View.OnClickListener {
            saveUser()
            startMain()
            //   startActivity(MainActivity.getNewIntent(this, true));
            //save()
        })
        done_btn_textview_2.setOnClickListener(View.OnClickListener {
            //  startActivity(MainActivity.getNewIntent(this, true));
            viewModel.getItems()
        })

        done_btn_textview_3.setOnClickListener(View.OnClickListener {
            //  startActivity(MainActivity.getNewIntent(this, true));
            viewModel.getComandas()
        })

    }

    private fun setupObservers() {

        viewModel.isUserFetched.observe(this, Observer { user ->
            //saveuser
        })

        viewModel.items.observe(this, Observer { items ->
            saveComanda(items)
        })
        viewModel.comandasItems.observe(this, Observer { items ->
            items
        })
    }


    private fun startMain() {
        startActivity(InitComandaActivity.getNewIntent(this));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}