package com.mcba.comandacentralv2.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.mcba.comandacentralv2.MainActivity;
import com.mcba.comandacentralv2.R;


public abstract class BaseFragment extends Fragment {

    public final String TAG = this.getClass().getSimpleName();

    protected View mMainFragmentView;
    private ProgressBar mContentProgressBar;
    private View mContentMainContainerView;
    public ProgressDialog mProgressDialog;

    protected abstract int getLayoutResource();

    /**
     * Sets up view references. Called in the {@link Fragment#onActivityCreated(Bundle)}
     */
    protected abstract void setViewReferences();

    /**
     * Generic setup of the fragment. Called in the {@link Fragment#onActivityCreated(Bundle)}
     * after the {@link BaseFragment#setViewReferences()} method.
     */
    protected abstract void setupFragment(Bundle savedInstanceState);


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mMainFragmentView = inflater.inflate(getLayoutResource(), container, false);

        mContentMainContainerView = findViewById(R.id.container_body);
        mContentProgressBar = (ProgressBar) findViewById(R.id.content_progress_bar);
        return mMainFragmentView;
    }

    protected View findViewById(int id) {
        return mMainFragmentView.findViewById(id);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setViewReferences();
        setupFragment(savedInstanceState);
    }

    public void showToast(@NonNull String message) {
        Log.i(TAG, message);
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void showDialog(String title, @Nullable String message) {
        Log.i(TAG, "Dialog message: " + message);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setPositiveButton(R.string.dialog_ok, null);

        if (message != null) {
            dialogBuilder.setMessage(message);
        }
        dialogBuilder.show();
    }

    public void showDialog(int titleResId, int messageResId) {
        Log.i(TAG, "Dialog message: " + getString(messageResId));
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext())
                .setTitle(titleResId)
                .setPositiveButton(R.string.dialog_ok, null);

        if (messageResId != 0) {
            dialogBuilder.setMessage(messageResId);
        }
        dialogBuilder.show();
    }


    public void showProgressDialog(String title, String message) {
        if ((mProgressDialog != null) && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if ((mProgressDialog != null) && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void setTitle(int titleId) {
        getActivity().setTitle(titleId);
    }

    protected void setTitle(CharSequence title) {
        getActivity().setTitle(title);
    }


    protected void showProgressBar() {
        if (mContentProgressBar != null) {
            mContentProgressBar.setVisibility(View.VISIBLE);
        }
        if (mContentMainContainerView != null) {
            mContentMainContainerView.setVisibility(View.GONE);
        }
    }

    protected void hideProgressBar() {
        if (mContentProgressBar != null) {
            mContentProgressBar.setVisibility(View.GONE);
        }
        if (mContentMainContainerView != null) {
            mContentMainContainerView.setVisibility(View.VISIBLE);
        }
    }

    public void showGenericErrorDialog(boolean finishOnOk) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity()).setPositiveButton("OK", null);
        dialogBuilder.setTitle(R.string.error_generic_title)
                .setMessage(R.string.error_generic_msg);
        if (finishOnOk) {
            dialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    getActivity().finish();
                }
            });
        }
        dialogBuilder.show();
    }

    public View getContentMainContainerView() {
        return mContentMainContainerView;
    }

    public ProgressBar getContentProgressBar() {
        return mContentProgressBar;
    }

    public MainActivity getBaseActivity() {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            return (MainActivity) activity;
        } else {
            return null;
        }
    }
}
