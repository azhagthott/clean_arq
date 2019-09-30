package dev.zecovery.snaspe.info.presentation.activity;

import android.util.Log;
import android.view.View;

import com.appy.android.appycore.presentation.activity.BaseActivity;

import javax.inject.Inject;

import dev.zecovery.commons.location.domain.model.UserLocation;
import dev.zecovery.snaspe.BuildConfig;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.ActivityInfoBinding;
import dev.zecovery.snaspe.help.presentation.activity.HelpActivity;
import dev.zecovery.snaspe.info.di.component.DaggerInfoActivityComponent;
import dev.zecovery.snaspe.userlocation.presentation.activity.LocationSelectorActivity;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetCurrentParkContract;

public class InfoActivity extends BaseActivity<ActivityInfoBinding> implements
        View.OnClickListener,
        GetCurrentParkContract.View {

    private static final String TAG = InfoActivity.class.getCanonicalName();

    @Inject
    GetCurrentParkContract.Presenter getCurrentLocationPresenter;

    @Override
    protected void injectDependencies() {
        DaggerInfoActivityComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_info;
    }

    @Override
    protected void initView() {
        binder.tvVersion.setText(setVersion());
        binder.btnChangeLocation.setOnClickListener(this);

        binder.ibBack.setOnClickListener(this);
        binder.fabHelp.setOnClickListener(this);

        getCurrentLocationPresenter.initialize(this);
        getCurrentLocationPresenter.getCurrentLocation();
    }

    private String setVersion() {
        return BuildConfig.VERSION_NAME + ".Build-" + BuildConfig.VERSION_CODE;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                onBackPressed();
                finish();
                break;
            case R.id.btn_change_location:
                startActivity(LocationSelectorActivity.class);
                finish();
                break;
            case R.id.fab_help:
                startActivity(HelpActivity.class);
                break;
        }
    }

    @Override
    public void onGetCurrentLocation(UserLocation currentUserLocation) {
        if (currentUserLocation != null) {
            binder.tvInfoPark.setText(currentUserLocation.userLocationParkName);
            binder.tvInfoService.setText(currentUserLocation.userLocationServiceName);
            binder.tvInfoControl.setText(currentUserLocation.userLocationControlName);
        }
    }

    @Override
    public void onGetCurrentLocationFailure(String message) {

    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {
        Log.e(TAG, "ERROR: " + message);
    }
}
