package dev.zecovery.snaspe.downloadticket.presentation.presenter;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.domain.model.Ticket;
import dev.zecovery.commons.ticket.domain.usecase.SaveTicketListUseCase;
import dev.zecovery.snaspe.downloadticket.presentation.contract.SaveTicketListContract;

public class SaveTicketListPresenter implements SaveTicketListContract.Presenter {

    private SaveTicketListContract.View view;
    private SaveTicketListUseCase saveTicketListUseCase;

    @Inject
    public SaveTicketListPresenter(SaveTicketListUseCase saveTicketListUseCase) {
        this.saveTicketListUseCase = saveTicketListUseCase;
    }

    @Override
    public void initialize(SaveTicketListContract.View view) {
        this.view = view;
    }

    @Override
    public void saveTickets(List<Ticket> tickets) {
        try {
            saveTicketListUseCase.setData(tickets).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onTicketsSaved(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onTicketsSavedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onTicketsSavedFailure(e.getMessage());
        }
    }
}
