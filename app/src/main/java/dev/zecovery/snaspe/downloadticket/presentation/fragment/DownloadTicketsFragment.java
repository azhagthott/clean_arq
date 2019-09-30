package dev.zecovery.snaspe.downloadticket.presentation.fragment;

import android.util.Log;
import android.view.View;

import com.appy.android.appycore.presentation.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.location.domain.model.UserLocation;
import dev.zecovery.commons.ticket.domain.model.Ticket;
import dev.zecovery.commons.visitor.domain.model.Visitor;
import dev.zecovery.snaspe.BuildConfig;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.FragmentDownloadTicketsBinding;
import dev.zecovery.snaspe.downloadticket.di.component.DaggerDownloadTicketsComponent;
import dev.zecovery.snaspe.downloadticket.presentation.contract.DownloadTicketContract;
import dev.zecovery.snaspe.downloadticket.presentation.contract.SaveTicketListContract;
import dev.zecovery.snaspe.downloadticket.presentation.contract.SaveVisitorsContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetCurrentParkContract;

public class DownloadTicketsFragment extends BaseFragment<FragmentDownloadTicketsBinding> implements
        DownloadTicketContract.View,
        SaveVisitorsContract.View,
        GetCurrentParkContract.View,
        SaveTicketListContract.View,
        View.OnClickListener {

    private static final String TAG = DownloadTicketsFragment.class.getCanonicalName();

    @Inject
    DownloadTicketContract.Presenter downloadTicketsPresenter;
    @Inject
    SaveVisitorsContract.Presenter saveVisitorsPresenter;
    @Inject
    GetCurrentParkContract.Presenter getCurrentLocationPresenter;
    @Inject
    SaveTicketListContract.Presenter saveTicketListPresenter;

    private int mParkId;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_download_tickets;
    }

    @Override
    protected void injectDependencies() {
        DaggerDownloadTicketsComponent.builder().build().inject(this);
    }

    @Override
    protected void initView() {
        downloadTicketsPresenter.initialize(this);
        saveVisitorsPresenter.initialize(this);

        getCurrentLocationPresenter.initialize(this);
        getCurrentLocationPresenter.getCurrentLocation();

        saveTicketListPresenter.initialize(this);

        binder.btnDownloadTicket.setVisibility(View.GONE);

        binder.btnDownloadTicket.setOnClickListener(this);
        showTotal(false);
    }

    @Override
    public void onGetCurrentLocation(UserLocation currentUserLocation) {
        if (currentUserLocation != null) {
            this.mParkId = currentUserLocation.userLocationParkId;
            binder.btnDownloadTicket.setVisibility(View.VISIBLE);
            Log.i(TAG, "parkId: " + mParkId);
        }
    }

    @Override
    public void onGetCurrentLocationFailure(String message) {
        showMessage(" -onGetCurrentLocationFailure: " + message);
    }

    @Override
    public void onTicketsDownloaded(List<Ticket> ticketList) {
        showProgress(false);
        if (ticketList != null) {
            showTotal(true);
            saveTicketListPresenter.saveTickets(ticketList);
            binder.tvTotalTicketsDownloaded.setText(String.valueOf(ticketList.size()));
            for (Ticket ticket : ticketList) {
                Log.d(TAG, "onTicketsDownloaded: " + ticket.ticketId);
            }
        }
    }

    @Override
    public void onTicketsSaved(boolean saved) {
        Log.i(TAG, "list of tickets saved: " + saved);
    }

    @Override
    public void onTicketsSavedFailure(String message) {
        showMessage(" -onTicketsSavedFailure: " + message);
    }

    @Override
    public void ticketListToDelete(List<String> ticketList) {
        if (ticketList != null) {
            if (ticketList.get(0).equals("")) {
                binder.tvTotalTicketsDeleted.setText("0");
            } else {
                binder.tvTotalTicketsDeleted.setText(String.valueOf(ticketList.size()));
                for (String ticketId : ticketList) {
                    Log.d(TAG, "ticketListToDelete: " + ticketId);
                }
            }
        }
    }

    @Override
    public void listOfVisitors(List<Visitor> visitorList) {
        saveVisitorsPresenter.saveVisitors(visitorList);
    }

    @Override
    public void onTicketsDownloadedFailure(String message) {
        showMessage(" -onTicketsDownloadedFailure: " + message);
        showProgress(false);
    }

    @Override
    public void onVisitorsSaved(boolean saved) {
        Log.i(TAG, "onVisitorsSaved: " + saved);
    }

    @Override
    public void onVisitorsSavedFailure(String message) {
        showMessage(" -onVisitorsSavedFailure: " + message);
    }

    @Override
    public void showProgress(boolean show) {
        binder.pbMainProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Log.e(TAG, "ERROR: " + message);
    }

    @Override
    public void onClick(View view) {
        showTotal(false);
        showProgress(true);
        downloadTicketsPresenter.downloadTickets(BuildConfig.URL_KEY, mParkId);
    }

    private void showTotal(boolean show) {
        binder.clTotalTicketsDownloaded.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
