package dev.zecovery.snaspe.downloadticket.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.ticket.domain.model.Ticket;

public interface SaveTicketListContract {

    interface View extends UIProgressView {

        void onTicketsSaved(boolean saved);

        void onTicketsSavedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveTickets(List<Ticket> tickets);
    }
}
