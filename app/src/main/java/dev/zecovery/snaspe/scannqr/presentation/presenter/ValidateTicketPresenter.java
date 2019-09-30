package dev.zecovery.snaspe.scannqr.presentation.presenter;

import android.util.Log;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;
import com.appy.android.appycore.util.QrValidator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.qrobject.QrObject;
import dev.zecovery.commons.qrobject.QrObjectReservation;
import dev.zecovery.commons.ticket.domain.usecase.GetScannedTicketByIdUseCase;
import dev.zecovery.commons.ticket.domain.model.ScannedTicket;
import dev.zecovery.commons.ticket.domain.usecase.SaveScannedTicketUseCase;
import dev.zecovery.snaspe.BuildConfig;
import dev.zecovery.snaspe.scannqr.presentation.contract.ValidateTicketContract;

import static dev.zecovery.commons.App.INVALID_TICKET_TYPE;
import static dev.zecovery.commons.App.SCAN_ENTRANCE;
import static dev.zecovery.commons.App.SCAN_EXIT;
import static dev.zecovery.commons.App.TICKET_ENTRY_EXIT;
import static dev.zecovery.commons.App.TICKET_RESERVATION;

public class ValidateTicketPresenter implements ValidateTicketContract.Presenter {

    private static final String TAG = ValidateTicketPresenter.class.getCanonicalName();
    private ValidateTicketContract.View view;
    private SaveScannedTicketUseCase saveScannedTicketUseCase;
    private GetScannedTicketByIdUseCase getScannedTicketByIdUseCase;

    @Inject
    public ValidateTicketPresenter(SaveScannedTicketUseCase saveScannedTicketUseCase, GetScannedTicketByIdUseCase getScannedTicketByIdUseCase) {
        this.saveScannedTicketUseCase = saveScannedTicketUseCase;
        this.getScannedTicketByIdUseCase = getScannedTicketByIdUseCase;
    }

    @Override
    public void initialize(ValidateTicketContract.View view) {
        this.view = view;
    }

    @Override
    public void validateAlReadyScannedTicket(int scannedTicketId) {
        try {
            getScannedTicketByIdUseCase.setData(scannedTicketId).execute(new UseCaseObserver<ScannedTicket>() {
                @Override
                public void onNext(ScannedTicket value) {
                    super.onNext(value);
                    view.onAlreadyScannedTicket(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onAlReadyScannedTicketFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            e.getMessage();
            view.onAlReadyScannedTicketFailure(e.getMessage());
        }
    }

    private void saveTicket(QrObject qrObject, String type) {
        try {

            long tsLong = System.currentTimeMillis() / 1000;
            String timestamp = Long.toString(tsLong);

            ScannedTicket ticket = new ScannedTicket();
            ticket.scannedTicketParkId = Integer.parseInt(qrObject.qrParkId);
            ticket.scannedTicketTicketName = qrObject.qrTicketName;
            ticket.scannedTicketTicketEntryDate = qrObject.qrTicketEntryDate;
            ticket.scannedTicketTicketExitDate = qrObject.qrTicketExitDate;
            ticket.scannedTicketId = Integer.parseInt(qrObject.qrIdTicket);
            ticket.scannedTicketVisitorId = qrObject.qrVisitorId;
            ticket.scannedTicketVisitorName = qrObject.qrVisitorName;
            ticket.scannedTicketPaymentTimestamp = timestamp;
            ticket.scannedTicketType = type;

            saveScannedTicketUseCase.setData(ticket).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    Log.d(TAG, "onNext: " + value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    Log.e(TAG, "saveTicket: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "saveTicket: " + e.getMessage());
        }
    }

    private boolean compareTickets(int qrTicket, List<Integer> localTicket) {
        List<Integer> aux = new ArrayList<>();
        for (int ticket : localTicket) {
            if (ticket == qrTicket) {
                aux.add(ticket);
            }
        }
        return aux.size() > 0;
    }

    @Override
    public void validateEntranceTicket(int parkId, String[] strings, String rawResultString, List<Integer> ticketIdList) {
        try {
            QrObject qrObject = new QrObject();
            qrObject.qrUrl = strings[0];
            qrObject.qrType = strings[1];
            qrObject.qrIdTicket = strings[2];
            qrObject.qrTicketName = strings[3];
            qrObject.qrVisitorId = strings[4];
            qrObject.qrTicketEntryDate = strings[5];
            qrObject.qrTicketExitDate = strings[6];
            qrObject.qrParkId = strings[7];
            qrObject.qrVisitorPassport = strings[8];
            qrObject.qrVisitorName = strings[9];
            qrObject.qrVisitorNationality = strings[10];
            qrObject.qrVisitorAge = strings[11];
            qrObject.qrTicketPrice = strings[12];

            if (!QrValidator.validate(BuildConfig.QR_KEY, rawResultString)) {
                view.onInvalidTicket();
            } else {
                if (qrObject.qrType.equals(TICKET_ENTRY_EXIT)) {
                    // Valid but wrong park
                    if (!qrObject.qrParkId.equals(String.valueOf(parkId))) {
                        view.onWrongParkValidTicket(qrObject);
                        // Valid but date
                    } else if (!checkDate(qrObject.qrTicketEntryDate)) {
                        view.onExpiredValidTicket(qrObject);
                        saveTicket(qrObject, SCAN_ENTRANCE);
                        // Validate ticket Id in local db
                    } else if (!compareTickets(Integer.parseInt(qrObject.qrIdTicket), ticketIdList)) {
                        view.onNonExistingValidTicket(qrObject);
                        saveTicket(qrObject, SCAN_ENTRANCE);
                        // Validate ticket Id in local db
                    } else if (compareTickets(Integer.parseInt(qrObject.qrIdTicket), ticketIdList)) {
                        view.onValidTicket(qrObject);
                        saveTicket(qrObject, SCAN_ENTRANCE);
                    } else {
                        view.onExistingValidTicket(qrObject);
                    }
                } else {
                    view.onReadingError(INVALID_TICKET_TYPE);
                }
            }
        } catch (Exception e) {
            view.onReadingError(e.getMessage());
        }
    }

    @Override
    public void validateExitTicket(int parkId, String[] strings, String rawResultString, List<Integer> ticketIdList) {
        try {
            QrObject qrObject = new QrObject();
            qrObject.qrUrl = strings[0];
            qrObject.qrType = strings[1];
            qrObject.qrIdTicket = strings[2];
            qrObject.qrTicketName = strings[3];
            qrObject.qrVisitorId = strings[4];
            qrObject.qrTicketEntryDate = strings[5];
            qrObject.qrTicketExitDate = strings[6];
            qrObject.qrParkId = strings[7];
            qrObject.qrVisitorPassport = strings[8];
            qrObject.qrVisitorName = strings[9];
            qrObject.qrVisitorNationality = strings[10];
            qrObject.qrVisitorAge = strings[11];
            qrObject.qrTicketPrice = strings[12];

            if (!QrValidator.validate(BuildConfig.QR_KEY, rawResultString)) {
                view.onInvalidTicket();
            } else {
                if (qrObject.qrType.equals(TICKET_ENTRY_EXIT)) {
                    // Valid but wrong park
                    if (!qrObject.qrParkId.equals(String.valueOf(parkId))) {
                        view.onWrongParkValidTicket(qrObject);
                        // Valid but date
                    } else if (!checkDate(qrObject.qrTicketExitDate)) {
                        view.onExpiredValidTicket(qrObject);
                        saveTicket(qrObject, SCAN_EXIT);
                        // Validate ticket Id in local db
                    } else if (!compareTickets(Integer.parseInt(qrObject.qrIdTicket), ticketIdList)) {
                        view.onNonExistingValidTicket(qrObject);
                        saveTicket(qrObject, SCAN_EXIT);
                        // Validate ticket Id in local db
                    } else if (compareTickets(Integer.parseInt(qrObject.qrIdTicket), ticketIdList)) {
                        view.onValidTicket(qrObject);
                        saveTicket(qrObject, SCAN_EXIT);
                    } else {
                        view.onExistingValidTicket(qrObject);
                    }
                } else {
                    view.onReadingError(INVALID_TICKET_TYPE);
                }
            }
        } catch (Exception e) {
            view.onReadingError(e.getMessage());
        }

    }

    @Override
    public void validateReservationTicket(int servicesId, String[] strings, String rawResultString, List<Integer> ticketIdList) {

        try {

            List<QrObjectReservation> reservationList = new ArrayList<>();

            QrObject qrObject = new QrObject();
            qrObject.qrUrl = strings[0];
            qrObject.qrType = strings[1];
            qrObject.qrIdTicket = strings[2];
            qrObject.qrTicketEntryDate = strings[3];
            qrObject.qrTicketExitDate = strings[4];
            qrObject.qrVisitorPassport = strings[5];

            for (int i = 6; i < strings.length; i = i + 3) {
                Log.d(TAG, "validateReservationTicket: " + i);
                QrObjectReservation reservation = new QrObjectReservation();
                reservation.qrServiceId = String.valueOf(servicesId);
                for (int j = i + 1; j < i + 3; j++) {
                    int k = j + 1;
                    reservation.qrReservationId = strings[j];
                    reservation.qrReservationDate = strings[k];
                    j = k + 1;
                }
                reservationList.add(reservation);
            }

            if (QrValidator.validate(BuildConfig.QR_KEY, rawResultString)) {
                if (qrObject.qrType.equals(TICKET_RESERVATION)) {
                    for (QrObjectReservation reservation : reservationList) {
                        if (!reservation.qrServiceId.equals(String.valueOf(servicesId))) {
                            view.onWrongParkValidTicket(qrObject);
                        } else {
                            view.onValidTicket(qrObject);
                        }
                    }
                } else {
                    view.onReadingError(INVALID_TICKET_TYPE);
                }
            } else {
                view.onInvalidTicket();
            }
        } catch (Exception e) {
            view.onReadingError(e.getMessage());
        }
    }

    private boolean checkDate(String timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        long dateTicket = Long.parseLong(timestamp) * 1000L;
        String date = dateFormat.format(new Date(dateTicket));
        String currentDate = dateFormat.format(new Date());
        return currentDate.equals(date);
    }
}
