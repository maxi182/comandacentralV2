package com.mcba.comandacentralv2.ui;

import android.content.Context;
import android.content.Intent;

import com.mcba.comandacentralv2.ui.base.BaseSplashScreenActivity;

public class SplashActivity extends BaseSplashScreenActivity {

    public static Intent getNewIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    @Override
    protected Intent proceedLoading() {
        return null;
    }
}
