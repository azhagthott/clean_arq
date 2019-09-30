package com.appy.android.appycore.presentation.activity;

import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.appy.android.appycore.R;

import java.util.TimerTask;

/**
 * Created by Francisco Barrios 19/10/2018
 */

public abstract class BaseSplashScreenActivity<BINDER extends ViewDataBinding> extends BaseActivity<BINDER> {

    protected abstract void openNextActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void injectDependencies() {
    }

    @Override
    public void initView() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        hideActionBar();
        new Handler().postDelayed(new TimerTask() {
            @Override
            public void run() {
                openNextActivity();
            }
        }, getSplashTime());

    }

    protected abstract int getSplashTime();

    @Override
    protected void setTransitionAnimations() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    protected void startActivity(Class activityClass) {
        super.startActivity(activityClass);
        finish();
    }
}