package dev.zecovery.snaspe.sendtickets.di.module;

import dagger.Module;
import dagger.Provides;
import dev.zecovery.commons.ticket.domain.usecase.GetAllScannedTicketUseCase;
import dev.zecovery.commons.ticket.domain.usecase.SendEntryTicketListUseCase;
import dev.zecovery.commons.ticket.domain.usecase.SendExitTicketListUseCase;
import dev.zecovery.commons.ticket.domain.usecase.SendReservationsListUseCase;
import dev.zecovery.snaspe.sendtickets.presentation.contract.GetAllScannedTicketsContract;
import dev.zecovery.snaspe.sendtickets.presentation.contract.SendTicketsContract;
import dev.zecovery.snaspe.sendtickets.presentation.presenter.GetAllScannedTicketsPresenter;
import dev.zecovery.snaspe.sendtickets.presentation.presenter.SendTicketsPresenter;

@Module
public class SendTicketFragmentModule {

    @Provides
    GetAllScannedTicketsContract.Presenter provideGetAllScannedTicketsPresenter(GetAllScannedTicketUseCase getAllScannedTicketUseCase) {
        return new GetAllScannedTicketsPresenter(getAllScannedTicketUseCase);
    }

    @Provides
    SendTicketsContract.Presenter provideSendTicketsPresenter(SendEntryTicketListUseCase sendEntryTicketListUseCase, SendExitTicketListUseCase sendExitTicketListUseCase, SendReservationsListUseCase sendReservationsListUseCase) {
        return new SendTicketsPresenter(sendEntryTicketListUseCase, sendExitTicketListUseCase, sendReservationsListUseCase);
    }
}

