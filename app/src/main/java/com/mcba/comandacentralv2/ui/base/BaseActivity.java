package com.mcba.comandacentralv2.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mcba.comandacentralv2.R;


/**
 * Created by mac on 30/05/2018.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();

    private Toolbar mToolbar;
    private ProgressBar mContentProgressBar;
    private ProgressDialog mProgressDialog;


    protected abstract int getLayoutResource();

    protected abstract void setupActivity(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        mContentProgressBar = (ProgressBar) findViewById(R.id.content_progress_bar);
        setupToolbar();

        setupActivity(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void setupToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            setupActionBar(getSupportActionBar());
        }
    }


    protected void showProgressBar() {
        if (mContentProgressBar != null) {
            mContentProgressBar.setVisibility(View.VISIBLE);
        }

    }

    protected void hideProgressBar() {
        if (mContentProgressBar != null) {
            mContentProgressBar.setVisibility(View.GONE);
        }

    }

    public void showProgressDialog(String title, String message) {
        if ((mProgressDialog != null) && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.show();

    }

    public void hideProgressDialog() {
        if ((mProgressDialog != null) && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

    public void setupActionBar(ActionBar actionBar) {
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);

    }

    public static void confirmarMesaje(Context context, int titulo, int mensaje, boolean cancelable, DialogInterface.OnClickListener positivoListener) {
        try {
                new android.app.AlertDialog.Builder(context)
                        .setTitle(titulo)
                        .setMessage(mensaje)
                        .setCancelable(cancelable)
                        .setPositiveButton("ACEPTAR", positivoListener)
                        .setNegativeButton("CANCELAR", positivoListener)
                        .show();

        } catch (IllegalArgumentException ex) {

        }
    }


    public void showGenericErrorDialog(boolean finishOnOk) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this).setPositiveButton("OK", null);
        dialogBuilder.setTitle(R.string.error_generic_title)
                .setMessage(R.string.error_generic_msg);
        if (finishOnOk) {
            dialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    finish();
                }
            });
        }
        dialogBuilder.show();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
