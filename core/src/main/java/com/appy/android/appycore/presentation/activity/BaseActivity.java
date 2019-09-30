package com.appy.android.appycore.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.appy.android.appycore.R;

import java.util.List;

/**
 * Created by Francisco Barrios 19/10/2018
 */
public abstract class BaseActivity<BINDER extends ViewDataBinding> extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected BINDER binder;

    protected abstract void injectDependencies();

    protected abstract @LayoutRes
    int getLayoutId();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(getMenuId(), menu);
        return true;
    }

    protected int getMenuId() {
        return R.menu.empty_menu;
    }

    /**
     * The onCreate base will init this view
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTransitionAnimations();
        super.onCreate(savedInstanceState);
        binder = DataBindingUtil.setContentView(this, getLayoutId());
        injectDependencies();
        setupToolbar();
        initView();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    protected abstract void initView();

    /**
     * Setup the object graph and inject the dependencies needed on this activity.
     */

    @Override
    protected void onDestroy() {
        unbindViews();
        super.onDestroy();
    }

    protected void setupToolbar() {
        setupToolbar(R.id.toolbar);
    }

    protected void setupToolbar(int toolbarId) {
        mToolbar = findViewById(toolbarId);
        try {
            setSupportActionBar(mToolbar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startActivity(Class activityClass) {
        Intent i = new Intent(this, activityClass);
        startActivity(i);
    }

    private void unbindViews() {
        if (binder != null)
            binder.unbind();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    protected void setTransitionAnimations() {
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    protected List<Fragment> getFragmentStack() {
        return getSupportFragmentManager().getFragments();
    }

    public void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    protected void showUpButton(boolean show) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(show);
            getSupportActionBar().setDisplayHomeAsUpEnabled(show);
        }
    }

    protected void hideActionBar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }
}