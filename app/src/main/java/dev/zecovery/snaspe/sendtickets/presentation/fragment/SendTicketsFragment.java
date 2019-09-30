package dev.zecovery.snaspe.sendtickets.presentation.fragment;

import android.util.Log;
import android.view.View;

import com.appy.android.appycore.presentation.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.location.domain.model.UserLocation;
import dev.zecovery.commons.ticket.domain.model.ScannedTicket;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.FragmentSendTicketsBinding;
import dev.zecovery.snaspe.sendtickets.di.component.DaggerSendTicketsComponent;
import dev.zecovery.snaspe.sendtickets.presentation.contract.GetAllScannedTicketsContract;
import dev.zecovery.snaspe.sendtickets.presentation.contract.SendTicketsContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetCurrentParkContract;

public class SendTicketsFragment extends BaseFragment<FragmentSendTicketsBinding> implements
        GetAllScannedTicketsContract.View,
        GetCurrentParkContract.View,
        SendTicketsContract.View,
        View.OnClickListener {

    private static final String TAG = SendTicketsFragment.class.getCanonicalName();

    @Inject
    GetCurrentParkContract.Presenter getUserLocationPresenter;
    @Inject
    GetAllScannedTicketsContract.Presenter getAllScannedTicketsPresenter;
    /*@Inject
    SendTicketsContract.Presenter sendTicketsPresenter;*/

    private List<ScannedTicket> mEntryTickets = new ArrayList<>();
    private List<ScannedTicket> mExitTickets = new ArrayList<>();
    private List<ScannedTicket> mReservationTickets = new ArrayList<>();

    private int mServiceId;
    private int mControlId;

    @Override
    protected void injectDependencies() {
        DaggerSendTicketsComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_send_tickets;
    }

    @Override
    protected void initView() {
        clearTexts();
        getAllScannedTicketsPresenter.initialize(this);
        getAllScannedTicketsPresenter.getAllScannedTicket();

        getUserLocationPresenter.initialize(this);
        getUserLocationPresenter.getCurrentLocation();

        //sendTicketsPresenter.initialize(this);

        binder.btnSendTicket.setOnClickListener(this);
    }

    private void clearTexts() {
        binder.tvSendEntryTickets.setText("");
        binder.tvSendTicketsReservations.setText("");
        binder.tvSendTicketsExit.setText("");
    }

    @Override
    public void onClick(View view) {
        /*sendTicketsPresenter.sendEntryTickets();
        sendTicketsPresenter.sendExitTickets();
        sendTicketsPresenter.sendReservationTickets();*/
    }

    @Override
    public void onGetCurrentLocation(UserLocation currentUserLocation) {
        if (currentUserLocation != null) {
            mServiceId = currentUserLocation.userLocationServiceId;
            mControlId = currentUserLocation.userLocationControlId;

            Log.d(TAG, "mServiceId: " + mServiceId);
            Log.d(TAG, "mControlId: " + mControlId);
        }
    }

    @Override
    public void onGetCurrentLocationFailure(String message) {
        showMessage(" -onGetCurrentLocationFailure: " + message);
    }

    @Override
    public void onGetAllEntryScannedTicket(List<ScannedTicket> tickets) {
        if (tickets != null) {
            mEntryTickets.addAll(tickets);
            binder.tvSendEntryTickets.setText(String.valueOf(tickets.size()));
        }
    }

    @Override
    public void onGetAllExitScannedTicket(List<ScannedTicket> tickets) {
        if (tickets != null) {
            mExitTickets.addAll(tickets);
            binder.tvSendTicketsExit.setText(String.valueOf(tickets.size()));

        }
    }

    @Override
    public void onGetAllReservationScannedTicket(List<ScannedTicket> tickets) {
        if (tickets != null) {
            mReservationTickets.addAll(tickets);
            binder.tvSendTicketsReservations.setText(String.valueOf(tickets.size()));
        }
    }

    @Override
    public void onGetAllEntryScannedTicketFailure(String message) {
        showMessage(" -onGetAllEntryScannedTicketFailure: " + message);
    }

    @Override
    public void onGetAllExitScannedTicketFailure(String message) {
        showMessage(" -onGetAllExitScannedTicketFailure: " + message);
    }

    @Override
    public void onGetAllReservationScannedTicketFailure(String message) {
        showMessage(" -onGetAllReservationScannedTicketFailure: " + message);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {
        Log.e(TAG, "ERROR: " + message);
    }

    @Override
    public void onEntryTicketsSent(boolean sent) {

    }

    @Override
    public void onExitTicketsSent(boolean sent) {

    }

    @Override
    public void onReservationTicketsSent(boolean sent) {

    }

    @Override
    public void onEntryTicketsSentFailure(String message) {
        showMessage(" -onEntryTicketsSentFailure: " + message);
    }

    @Override
    public void onExitTicketsSentFailure(String message) {
        showMessage(" -onExitTicketsSentFailure: " + message);
    }

    @Override
    public void onReservationTicketsSentFailure(String message) {
        showMessage(" -onReservationTicketsSentFailure: " + message);
    }

    @Override
    public void onSendFailure(String message) {
        showMessage(" -onSendFailure: " + message);
    }
}

