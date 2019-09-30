package dev.zecovery.snaspe.splashscreen.presentation.activity;

import com.appy.android.appycore.presentation.activity.BaseActivity;

import javax.inject.Inject;

import dev.zecovery.commons.location.domain.model.UserLocation;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.ActivitySplashScreenBinding;
import dev.zecovery.snaspe.main.presentation.activity.MainActivity;
import dev.zecovery.snaspe.splashscreen.di.component.DaggerSplashScreenComponent;
import dev.zecovery.snaspe.userlocation.presentation.activity.LocationSelectorActivity;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetCurrentParkContract;
import io.realm.Realm;
import io.realm.RealmConfiguration;

import static dev.zecovery.commons.App.DATA_BASE_SCHEMA_VERSION;

public class SplashScreenActivity extends BaseActivity<ActivitySplashScreenBinding> implements
        GetCurrentParkContract.View {

    @Inject
    GetCurrentParkContract.Presenter getCurrentLocationPresenter;

    @Override
    protected void injectDependencies() {
        DaggerSplashScreenComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void initView() {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("snaspe.realm")
                .schemaVersion(DATA_BASE_SCHEMA_VERSION)
                .build();
        Realm.setDefaultConfiguration(config);

        getCurrentLocationPresenter.initialize(this);
        getCurrentLocationPresenter.getCurrentLocation();
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void onGetCurrentLocation(UserLocation currentUserLocation) {
        if (currentUserLocation != null) {
            startActivity(MainActivity.class);
            finish();
        }
    }

    @Override
    public void onGetCurrentLocationFailure(String message) {
        startActivity(LocationSelectorActivity.class);
        finish();
    }
}
