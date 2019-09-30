package com.appy.android.appycore.presentation.activity;

import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.Window;

import com.appy.android.appycore.R;
import com.appy.android.appycore.presentation.fragment.BaseFragment;

/**
 * Created by Francisco Barrios 19/10/2018
 */

public abstract class BaseDrawerActivity<BINDER extends ViewDataBinding> extends BaseActivity<BINDER>
        implements NavigationView.OnNavigationItemSelectedListener {

    protected Fragment topFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    protected abstract int getFragmentContainerId();

    protected void setFragment(Fragment fragment) {
        setFragment(fragment, false);
    }

    protected void setFragment(Fragment fragment, int idContainer) {
        setFragment(fragment, idContainer, false);
    }

    protected void setFragment(Fragment fragment, boolean addToBackStack) {
        setFragment(fragment, getFragmentContainerId(), addToBackStack);
    }

    protected void setFragment(Fragment fragment, int idContainer, boolean addToBackStack) {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        if (addToBackStack)
            trans.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        trans.replace(idContainer, fragment);
        if (addToBackStack)
            trans.addToBackStack(fragment.getTag());
        trans.commit();
    }


    protected abstract void initView();

    protected abstract @LayoutRes
    int getLayoutId();

    protected void addFragment(Fragment fragment, boolean show) {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction()
                .add(getFragmentContainerId(), fragment);
        if (!show)
            trans.hide(fragment);
        else
            topFragment = fragment;
        trans.commit();
    }

    protected void hideFragment(Fragment fragment) {
        if (fragment == null) return;
        getSupportFragmentManager().beginTransaction().hide(fragment).commit();
    }

    protected void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
        if (topFragment != null && topFragment != fragment)
            hideFragment(topFragment);
        topFragment = fragment;
    }

    @Override
    public void onBackPressed() {
        BaseFragment actualFragment = getActualFragment();
        boolean canBack = actualFragment == null || actualFragment.allowBackPressed();
        try {
            if (canBack)
                super.onBackPressed();
        } catch (Exception ignored) {
        }
    }

    protected BaseFragment getActualFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentById(getFragmentContainerId());
    }

    protected Fragment getTopFragment() {
        return topFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

}
