package dev.zecovery.snaspe.userlocation.di.component;

import com.appy.android.appycore.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;
import dev.zecovery.commons.park.di.ParkModule;
import dev.zecovery.snaspe.userlocation.di.module.ParkListFragmentModule;
import dev.zecovery.snaspe.userlocation.di.module.UserLocationModule;
import dev.zecovery.snaspe.userlocation.presentation.fragment.ParkListFragment;

@Singleton
@Component(modules = {
        ParkModule.class,
        ParkListFragmentModule.class,
        UserLocationModule.class
})
public interface ParkListFragmentComponent extends FragmentComponent<ParkListFragment> {
}
