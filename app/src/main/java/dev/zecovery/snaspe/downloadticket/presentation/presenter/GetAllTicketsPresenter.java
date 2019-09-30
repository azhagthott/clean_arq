package dev.zecovery.snaspe.downloadticket.presentation.presenter;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.domain.model.Ticket;
import dev.zecovery.commons.ticket.domain.usecase.GetAllLocalTicketsUseCase;
import dev.zecovery.snaspe.downloadticket.presentation.contract.GetAllTicketsContract;

public class GetAllTicketsPresenter implements GetAllTicketsContract.Presenter {

    private GetAllTicketsContract.View view;
    private GetAllLocalTicketsUseCase getAllLocalTicketsUseCase;

    @Inject
    public GetAllTicketsPresenter(GetAllLocalTicketsUseCase getAllLocalTicketsUseCase) {
        this.getAllLocalTicketsUseCase = getAllLocalTicketsUseCase;
    }

    @Override
    public void initialize(GetAllTicketsContract.View view) {
        this.view = view;
    }

    @Override
    public void getAllTickets() {
        try {
            getAllLocalTicketsUseCase.execute(new UseCaseObserver<List<Ticket>>() {
                @Override
                public void onNext(List<Ticket> value) {
                    super.onNext(value);
                    view.onGetAllTickets(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onGetAllTicketsFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onGetAllTicketsFailure(e.getMessage());
        }
    }
}
