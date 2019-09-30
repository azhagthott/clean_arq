package dev.zecovery.snaspe.splashscreen.di.component;

import com.appy.android.appycore.di.component.ActivityComponent;

import dagger.Component;
import dev.zecovery.snaspe.splashscreen.presentation.activity.SplashScreenActivity;
import dev.zecovery.snaspe.userlocation.di.module.UserLocationModule;

@Component(modules = {
        UserLocationModule.class
})
public interface SplashScreenComponent extends ActivityComponent<SplashScreenActivity> {
}
