package com.appy.android.appycore.presentation.activity;

import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.appy.android.appycore.R;
import com.appy.android.appycore.presentation.fragment.BaseFragment;

/**
 * Created by Francisco Barrios 29/10/2018
 */

public abstract class BaseFragmentActivity<BINDER extends ViewDataBinding> extends BaseActivity<BINDER> {

    protected Fragment topFragment;

    @Override
    protected void initView() {
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    protected void setFragment(Fragment fragment) {
        setFragment(fragment, false);
    }

    protected void setFragment(Fragment fragment, int idContainer) {
        setFragment(fragment, idContainer, false);
    }

    protected void setFragment(Fragment fragment, boolean addToBackStack) {
        setFragment(fragment, getFragmentContainerId(), addToBackStack);
    }

    protected abstract int getFragmentContainerId();


    protected void setFragment(Fragment fragment, int idContainer, boolean addToBackStack) {
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) trans.setCustomAnimations(
                R.anim.slide_in_up,
                R.anim.slide_out_down,
                android.R.anim.fade_in,
                android.R.anim.fade_out);

        trans.replace(idContainer, fragment);

        if (addToBackStack)
            trans.addToBackStack(fragment.getTag());

        trans.commitAllowingStateLoss();
    }


    public void addFragment(Fragment fragment, boolean show) {
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

    public void showFragment(Fragment fragment) {
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
            if (canBack) {
                super.onBackPressed();
                overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
            }
        } catch (Exception ignored) {
        }
    }

    protected BaseFragment getActualFragment() {
        return (BaseFragment) getSupportFragmentManager().findFragmentById(getFragmentContainerId());
    }

    public Fragment getTopFragment() {
        return topFragment;
    }
}