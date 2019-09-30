package dev.zecovery.snaspe.downloadticket.di.module;

import dagger.Module;
import dagger.Provides;
import dev.zecovery.commons.ticket.domain.usecase.GetAllLocalTicketsUseCase;
import dev.zecovery.commons.ticket.domain.usecase.GetTicketListUseCase;
import dev.zecovery.commons.ticket.domain.usecase.SaveTicketListUseCase;
import dev.zecovery.commons.visitor.domain.usecase.SaveVisitorUseCase;
import dev.zecovery.snaspe.downloadticket.presentation.contract.DownloadTicketContract;
import dev.zecovery.snaspe.downloadticket.presentation.contract.GetAllTicketsContract;
import dev.zecovery.snaspe.downloadticket.presentation.contract.SaveTicketListContract;
import dev.zecovery.snaspe.downloadticket.presentation.contract.SaveVisitorsContract;
import dev.zecovery.snaspe.downloadticket.presentation.presenter.DownloadTicketPresenter;
import dev.zecovery.snaspe.downloadticket.presentation.presenter.GetAllTicketsPresenter;
import dev.zecovery.snaspe.downloadticket.presentation.presenter.SaveTicketListPresenter;
import dev.zecovery.snaspe.downloadticket.presentation.presenter.SaveVisitorsPresenter;

@Module
public class DownloadTicketsModule {

    @Provides
    DownloadTicketContract.Presenter provideDownloadTicketPresenter(GetTicketListUseCase getTicketListUseCase) {
        return new DownloadTicketPresenter(getTicketListUseCase);
    }

    @Provides
    SaveVisitorsContract.Presenter provideSaveVisitorsPresenter(SaveVisitorUseCase saveVisitorUseCase) {
        return new SaveVisitorsPresenter(saveVisitorUseCase);
    }

    @Provides
    SaveTicketListContract.Presenter provideSaveTicketListPresenter(SaveTicketListUseCase saveTicketListUseCase) {
        return new SaveTicketListPresenter(saveTicketListUseCase);
    }

    @Provides
    GetAllTicketsContract.Presenter provideGetAllTicketsPresenter(GetAllLocalTicketsUseCase getAllLocalTicketsUseCase) {
        return new GetAllTicketsPresenter(getAllLocalTicketsUseCase);
    }
}

