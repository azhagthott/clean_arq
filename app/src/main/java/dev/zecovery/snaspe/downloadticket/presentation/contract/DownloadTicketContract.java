package dev.zecovery.snaspe.downloadticket.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.ticket.domain.model.Ticket;
import dev.zecovery.commons.visitor.domain.model.Visitor;

public interface DownloadTicketContract {

    interface View extends UIProgressView {

        void onTicketsDownloaded(List<Ticket> ticketList);

        void ticketListToDelete(List<String> ticketList);

        void listOfVisitors(List<Visitor> visitorList);

        void onTicketsDownloadedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void downloadTickets(String apiKey, int parkId);
    }
}
