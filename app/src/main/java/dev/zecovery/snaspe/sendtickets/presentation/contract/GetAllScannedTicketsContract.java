package dev.zecovery.snaspe.sendtickets.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.ticket.domain.model.ScannedTicket;

public interface GetAllScannedTicketsContract {

    interface View extends UIProgressView {

        void onGetAllEntryScannedTicket(List<ScannedTicket> tickets);

        void onGetAllExitScannedTicket(List<ScannedTicket> tickets);

        void onGetAllReservationScannedTicket(List<ScannedTicket> tickets);

        void onGetAllEntryScannedTicketFailure(String message);

        void onGetAllExitScannedTicketFailure(String message);

        void onGetAllReservationScannedTicketFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getAllScannedTicket();
    }
}
