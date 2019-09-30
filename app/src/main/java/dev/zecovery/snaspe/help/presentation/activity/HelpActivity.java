package dev.zecovery.snaspe.help.presentation.activity;

import android.view.View;

import com.appy.android.appycore.presentation.activity.BaseActivity;

import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.ActivityHelpBinding;

public class HelpActivity extends BaseActivity<ActivityHelpBinding> implements View.OnClickListener {
    @Override
    protected void injectDependencies() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help;
    }

    @Override
    protected void initView() {
        binder.ibBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
        finish();
    }
}
