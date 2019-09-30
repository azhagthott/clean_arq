package com.appy.android.appycore.presentation.fragment;

import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.AnimRes;
import androidx.annotation.AnimatorRes;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appy.android.appycore.presentation.util.StackNavigationController;

public abstract class BaseStackFragment<BINDER extends ViewDataBinding> extends BaseFragment<BINDER> {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initNavigation();
        return view;
    }

    public void initNavigation() {
        stackNavigationController = new StackNavigationController(getChildFragmentManager(), getNavigationContainer());
    }

    protected abstract int getNavigationContainer();

    public void addFragmentToStack(Fragment fragment) {
        BaseFragment base = getBaseStackFragment();
        if (base != null)
            base.stackNavigationController.addFragment(fragment);
    }

    public void addFragmentToStack(Fragment fragment, @AnimatorRes @AnimRes int in,
                                   @AnimatorRes @AnimRes int out) {
        BaseFragment base = getBaseStackFragment();
        if (base != null)
            base.stackNavigationController.addFragment(fragment, in, out);
    }

    @Override
    public boolean isBaseStackFragment() {
        return true;
    }

    @Override
    public boolean allowBackPressed() {
        return stackNavigationController.allowBackPressed();
    }
}
