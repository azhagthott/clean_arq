package dev.zecovery.snaspe.sendtickets.presentation.presenter;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.domain.model.ScannedTicket;
import dev.zecovery.commons.ticket.domain.usecase.SendEntryTicketListUseCase;
import dev.zecovery.commons.ticket.domain.usecase.SendExitTicketListUseCase;
import dev.zecovery.commons.ticket.domain.usecase.SendReservationsListUseCase;
import dev.zecovery.snaspe.sendtickets.presentation.contract.SendTicketsContract;

public class SendTicketsPresenter implements SendTicketsContract.Presenter {

    private SendTicketsContract.View view;
    private SendEntryTicketListUseCase sendEntryTicketListUseCase;
    private SendExitTicketListUseCase sendExitTicketListUseCase;
    private SendReservationsListUseCase sendReservationsListUseCase;

    @Inject
    public SendTicketsPresenter(SendEntryTicketListUseCase sendEntryTicketListUseCase,
                                SendExitTicketListUseCase sendExitTicketListUseCase,
                                SendReservationsListUseCase sendReservationsListUseCase) {
        this.sendEntryTicketListUseCase = sendEntryTicketListUseCase;
        this.sendExitTicketListUseCase = sendExitTicketListUseCase;
        this.sendReservationsListUseCase = sendReservationsListUseCase;
    }

    @Override
    public void initialize(SendTicketsContract.View view) {
        this.view = view;
    }

    @Override
    public void sendEntryTickets(List<ScannedTicket> scannedTickets) {

    }

    @Override
    public void sendExitTickets(List<ScannedTicket> scannedTickets) {

    }

    @Override
    public void sendReservationTickets(List<ScannedTicket> scannedTickets) {

    }
}
