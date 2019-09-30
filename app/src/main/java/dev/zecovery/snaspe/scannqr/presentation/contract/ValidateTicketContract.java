package dev.zecovery.snaspe.scannqr.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.qrobject.QrObject;
import dev.zecovery.commons.ticket.domain.model.ScannedTicket;

public interface ValidateTicketContract {

    interface View extends UIProgressView {

        void onTicketsDownloadedFailure(String message);

        void onInvalidTicket();

        void onValidTicket(QrObject qrObject);

        void onExistingValidTicket(QrObject qrObject);

        void onNonExistingValidTicket(QrObject qrObject);

        void onExpiredValidTicket(QrObject qrObject);

        void onWrongParkValidTicket(QrObject qrObject);

        void onReadingError(String message);

        void onAlreadyScannedTicket(ScannedTicket ticket);

        void onAlReadyScannedTicketFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {

        void validateAlReadyScannedTicket(int scannedTicketId);

        void validateEntranceTicket(int parkId, String[] strings, String rawResultString, List<Integer> ticketIds);

        void validateExitTicket(int parkId, String[] strings, String rawResultString, List<Integer> ticketIds);

        void validateReservationTicket(int servicesId, String[] strings, String rawResultString, List<Integer> ticketIds);
    }
}
