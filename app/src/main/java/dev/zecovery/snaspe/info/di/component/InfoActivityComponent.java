package dev.zecovery.snaspe.info.di.component;

import com.appy.android.appycore.di.component.ActivityComponent;

import dagger.Component;
import dev.zecovery.snaspe.info.presentation.activity.InfoActivity;
import dev.zecovery.snaspe.userlocation.di.module.UserLocationModule;

@Component(modules = {
        UserLocationModule.class
})
public interface InfoActivityComponent extends ActivityComponent<InfoActivity> {
}
