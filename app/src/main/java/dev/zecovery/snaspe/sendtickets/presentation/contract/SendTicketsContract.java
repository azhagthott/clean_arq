package dev.zecovery.snaspe.sendtickets.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.ticket.domain.model.ScannedTicket;

public interface SendTicketsContract {

    interface View extends UIProgressView {

        void onEntryTicketsSent(boolean sent);

        void onExitTicketsSent(boolean sent);

        void onReservationTicketsSent(boolean sent);

        void onEntryTicketsSentFailure(String message);

        void onExitTicketsSentFailure(String message);

        void onReservationTicketsSentFailure(String message);

        void onSendFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void sendEntryTickets(List<ScannedTicket> scannedTickets);

        void sendExitTickets(List<ScannedTicket> scannedTickets);

        void sendReservationTickets(List<ScannedTicket> scannedTickets);
    }
}
