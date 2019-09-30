package dev.zecovery.snaspe.downloadticket.di.component;

import com.appy.android.appycore.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;
import dev.zecovery.commons.ticket.di.TicketModule;
import dev.zecovery.snaspe.downloadticket.di.module.DownloadTicketsModule;
import dev.zecovery.snaspe.downloadticket.presentation.fragment.DownloadTicketsFragment;
import dev.zecovery.snaspe.userlocation.di.module.UserLocationModule;

@Singleton
@Component(modules = {
        TicketModule.class,
        UserLocationModule.class,
        DownloadTicketsModule.class
})
public interface DownloadTicketsComponent extends FragmentComponent<DownloadTicketsFragment> {
}
