package dev.zecovery.snaspe.main.presentation.activity;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.appy.android.appycore.presentation.activity.BaseFragmentActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.ActivityMainBinding;
import dev.zecovery.snaspe.downloadticket.presentation.fragment.DownloadTicketsFragment;
import dev.zecovery.snaspe.info.presentation.activity.InfoActivity;
import dev.zecovery.snaspe.scannqr.presentation.fragment.ScanQrFragment;
import dev.zecovery.snaspe.sendtickets.presentation.fragment.SendTicketsFragment;

public class MainActivity extends BaseFragmentActivity<ActivityMainBinding> implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    @Override
    protected void injectDependencies() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fl_main_content;
    }

    @Override
    protected void initView() {
        setFragment(new DownloadTicketsFragment());
        binder.ibChangeLocation.setOnClickListener(this);
        binder.navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_download:
                setFragment(new DownloadTicketsFragment());
                break;
            case R.id.navigation_scann:
                setFragment(new ScanQrFragment());
                break;
            case R.id.navigation_send_info:
                setFragment(new SendTicketsFragment());
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_change_location:
                startActivity(InfoActivity.class);
                break;
        }
    }
}
