package dev.zecovery.snaspe.userlocation.presentation.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appy.android.appycore.presentation.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.service.domain.model.Service;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.FragmentServiceListBinding;
import dev.zecovery.snaspe.userlocation.di.component.DaggerServiceListFragmentComponent;
import dev.zecovery.snaspe.userlocation.presentation.adapter.ServiceListAdapter;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetServiceListByParkIdContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveServiceLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnCallFragmentControlListener;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnSelectServiceListener;

import static dev.zecovery.commons.App.PARK_ID;
import static dev.zecovery.commons.App.PARK_NAME;

public class ServiceListFragment extends BaseFragment<FragmentServiceListBinding> implements
        GetServiceListByParkIdContract.View,
        SaveServiceLocationContract.View,
        OnSelectServiceListener {

    private static final String TAG = ServiceListFragment.class.getCanonicalName();

    @Inject
    GetServiceListByParkIdContract.Presenter getServiceListPresenter;
    @Inject
    SaveServiceLocationContract.Presenter saveServiceLocationPresenter;

    private OnCallFragmentControlListener listener;
    private Service mService;
    private int mParkId;
    private String mParkName;

    public ServiceListFragment newInstance(int parkId, String parkName) {
        ServiceListFragment fragment = new ServiceListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARK_ID, parkId);
        bundle.putString(PARK_NAME, parkName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void injectDependencies() {
        DaggerServiceListFragmentComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_service_list;
    }

    @Override
    protected void initView() {
        showProgress(true);

        // Get list of services
        getServiceListPresenter.initialize(this);
        // Save selected service
        saveServiceLocationPresenter.initialize(this);

        if (getArguments() != null) {
            mParkId = getArguments().getInt(PARK_ID);
            mParkName = getArguments().getString(PARK_NAME);
            getServiceListPresenter.getServiceListByParkId(mParkId);
            Log.i(TAG, "mParkId: " + mParkId);
        }
    }

    @Override
    public void onGetServiceList(List<Service> serviceList) {
        if (serviceList != null) {
            for (Service service : serviceList) {
                Log.i(TAG, "List of SERVICES == service_name: " + service.serviceName +
                        " - service_id: " + service.serviceId +
                        " - park_id: " + service.serviceParkId);
            }
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CONTEXT);
            ServiceListAdapter adapter = new ServiceListAdapter(serviceList, this);
            binder.rvServiceList.setLayoutManager(layoutManager);
            binder.rvServiceList.setHasFixedSize(false);
            binder.rvServiceList.setAdapter(adapter);
            showProgress(false);
            if (binder.srRefresh.isRefreshing()) {
                binder.srRefresh.setRefreshing(false);
            }
        }
    }

    @Override
    public void onGetListFailure(String message) {
        showMessage(" -onGetListFailure: " + message);
        showProgress(false);
    }

    @Override
    public void onServiceSelected(Service service) {
        if (service != null) {
            this.mService = service;
            saveServiceLocationPresenter.saveServiceLocation(mParkId, service);
            Log.i(TAG, "service selected: " + service.serviceName);
        }
    }

    @Override
    public void showProgress(boolean show) {
        binder.pbMainProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onServiceSaved(boolean saved) {
        listener.callFragmentControlListener(mParkId, mParkName, mService.serviceId, mService.serviceName);
        Log.i(TAG, "onServiceSaved: " + saved);
    }

    @Override
    public void onServiceSavedFailure(String message) {
        showMessage(" -onServiceSavedFailure: " + message);
    }

    @Override
    public void showMessage(String message) {
        Log.e(TAG, "showMessage: " + message);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCallFragmentControlListener) {
            listener = (OnCallFragmentControlListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnCallFragmentControlListener");
        }
    }
}
