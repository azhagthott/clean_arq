package dev.zecovery.snaspe.userlocation.di.component;

import com.appy.android.appycore.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;
import dev.zecovery.commons.park.di.ParkModule;
import dev.zecovery.snaspe.userlocation.di.module.ParkListFragmentModule;
import dev.zecovery.snaspe.userlocation.di.module.ServiceListFragmentModule;
import dev.zecovery.snaspe.userlocation.presentation.fragment.ServiceListFragment;

@Singleton
@Component(modules = {
        ParkListFragmentModule.class,
        ParkModule.class,
        ServiceListFragmentModule.class
})
public interface ServiceListFragmentComponent extends FragmentComponent<ServiceListFragment> {
}
