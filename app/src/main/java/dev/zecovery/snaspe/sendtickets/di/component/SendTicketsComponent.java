package dev.zecovery.snaspe.sendtickets.di.component;

import com.appy.android.appycore.di.component.FragmentComponent;

import dagger.Component;
import dev.zecovery.commons.ticket.di.TicketModule;
import dev.zecovery.snaspe.sendtickets.di.module.SendTicketFragmentModule;
import dev.zecovery.snaspe.sendtickets.presentation.fragment.SendTicketsFragment;
import dev.zecovery.snaspe.userlocation.di.module.UserLocationModule;

@Component(modules = {
        TicketModule.class,
        SendTicketFragmentModule.class,
        UserLocationModule.class
})
public interface SendTicketsComponent extends FragmentComponent<SendTicketsFragment> {
}
