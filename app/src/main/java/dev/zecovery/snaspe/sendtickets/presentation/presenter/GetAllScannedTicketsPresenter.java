package dev.zecovery.snaspe.sendtickets.presentation.presenter;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.domain.model.ScannedTicket;
import dev.zecovery.commons.ticket.domain.usecase.GetAllScannedTicketUseCase;
import dev.zecovery.snaspe.sendtickets.presentation.contract.GetAllScannedTicketsContract;

import static dev.zecovery.commons.App.SCAN_ENTRANCE;
import static dev.zecovery.commons.App.SCAN_EXIT;
import static dev.zecovery.commons.App.SCAN_RESERVATION;

public class GetAllScannedTicketsPresenter implements GetAllScannedTicketsContract.Presenter {

    private GetAllScannedTicketsContract.View view;
    private GetAllScannedTicketUseCase getAllScannedTicketUseCase;

    @Inject
    public GetAllScannedTicketsPresenter(GetAllScannedTicketUseCase getAllScannedTicketUseCase) {
        this.getAllScannedTicketUseCase = getAllScannedTicketUseCase;
    }

    @Override
    public void initialize(GetAllScannedTicketsContract.View view) {
        this.view = view;
    }

    @Override
    public void getAllScannedTicket() {
        try {
            getEntryTickets();
            getExitTickets();
            getReservationTickets();
        } catch (Exception e) {
            view.onGetAllEntryScannedTicketFailure(e.getMessage());
        }
    }

    private void getEntryTickets() {
        getAllScannedTicketUseCase.setData(SCAN_ENTRANCE).execute(new UseCaseObserver<List<ScannedTicket>>() {
            @Override
            public void onNext(List<ScannedTicket> value) {
                super.onNext(value);
                view.onGetAllEntryScannedTicket(value);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.onGetAllEntryScannedTicketFailure(e.getMessage());
            }
        });
    }

    private void getExitTickets() {
        getAllScannedTicketUseCase.setData(SCAN_EXIT).execute(new UseCaseObserver<List<ScannedTicket>>() {
            @Override
            public void onNext(List<ScannedTicket> value) {
                super.onNext(value);
                view.onGetAllExitScannedTicket(value);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.onGetAllExitScannedTicketFailure(e.getMessage());
            }
        });
    }

    private void getReservationTickets() {
        getAllScannedTicketUseCase.setData(SCAN_RESERVATION).execute(new UseCaseObserver<List<ScannedTicket>>() {
            @Override
            public void onNext(List<ScannedTicket> value) {
                super.onNext(value);
                view.onGetAllReservationScannedTicket(value);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.onGetAllReservationScannedTicketFailure(e.getMessage());
            }
        });
    }
}
