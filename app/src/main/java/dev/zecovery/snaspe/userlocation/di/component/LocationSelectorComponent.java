package dev.zecovery.snaspe.userlocation.di.component;

import com.appy.android.appycore.di.component.ActivityComponent;

import dagger.Component;
import dev.zecovery.snaspe.userlocation.di.module.UserLocationModule;
import dev.zecovery.snaspe.userlocation.presentation.activity.LocationSelectorActivity;

@Component(modules = {
        UserLocationModule.class
})
public interface LocationSelectorComponent extends ActivityComponent<LocationSelectorActivity> {
}
