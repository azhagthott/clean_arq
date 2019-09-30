package dev.zecovery.snaspe.downloadticket.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.ticket.domain.model.Ticket;

public interface GetAllTicketsContract {

    interface View extends UIProgressView {

        void onGetAllTickets(List<Ticket> tickets);

        void onGetAllTicketsFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getAllTickets();
    }
}
