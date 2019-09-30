package dev.zecovery.snaspe.scannqr.di.component;

import com.appy.android.appycore.di.component.ActivityComponent;

import dagger.Component;
import dev.zecovery.snaspe.downloadticket.di.module.DownloadTicketsModule;
import dev.zecovery.snaspe.scannqr.di.module.CameraModule;
import dev.zecovery.snaspe.scannqr.presentation.activity.CameraActivity;
import dev.zecovery.snaspe.userlocation.di.module.UserLocationModule;

@Component(modules = {
        CameraModule.class,
        UserLocationModule.class,
        DownloadTicketsModule.class
})
public interface CameraComponent extends ActivityComponent<CameraActivity> {
}
