package dev.zecovery.snaspe.userlocation.presentation.fragment;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appy.android.appycore.presentation.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.control.domain.model.Control;
import dev.zecovery.commons.park.domain.model.Park;
import dev.zecovery.commons.service.domain.model.Service;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.FragmentParkListBinding;
import dev.zecovery.snaspe.userlocation.di.component.DaggerParkListFragmentComponent;
import dev.zecovery.snaspe.userlocation.presentation.adapter.ParkListAdapter;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearControlListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearParkListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearServiceListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearUserLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveControlListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveParkListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveParkLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveServiceListContract;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnCallFragmentServiceListener;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnSelectParkListener;

public class ParkListFragment extends BaseFragment<FragmentParkListBinding> implements
        GetListContract.View,
        SaveParkListContract.View,
        SaveServiceListContract.View,
        SaveControlListContract.View,
        ClearParkListContract.View,
        ClearServiceListContract.View,
        ClearControlListContract.View,
        SaveParkLocationContract.View,
        ClearUserLocationContract.View,
        OnSelectParkListener {

    private static final String TAG = ParkListFragment.class.getCanonicalName();

    @Inject
    GetListContract.Presenter getListPresenter;
    @Inject
    SaveParkListContract.Presenter saveParkListPresenter;
    @Inject
    SaveServiceListContract.Presenter saveServiceListPresenter;
    @Inject
    SaveControlListContract.Presenter saveControlListPresenter;
    @Inject
    ClearParkListContract.Presenter clearParkListPresenter;
    @Inject
    ClearServiceListContract.Presenter clearServicesListPresenter;
    @Inject
    ClearControlListContract.Presenter clearControlListPresenter;
    @Inject
    SaveParkLocationContract.Presenter saveParkLocationPresenter;
    @Inject
    ClearUserLocationContract.Presenter clearUserLocationPresenter;

    private OnCallFragmentServiceListener listener;
    private Park mPark;

    public ParkListFragment newInstance() {
        return new ParkListFragment();
    }

    @Override
    protected void injectDependencies() {
        DaggerParkListFragmentComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_park_list;
    }

    @Override
    protected void initView() {
        showProgress(true);

        // Get parks list
        getListPresenter.initialize(this);
        getListPresenter.getLocationList();

        // Save list of parks
        saveParkListPresenter.initialize(this);
        // Save list of services
        saveServiceListPresenter.initialize(this);
        // Save list of controls
        saveControlListPresenter.initialize(this);

        // Remove list of parks from local db
        clearParkListPresenter.initialize(this);
        // Remove list of services from local db
        clearServicesListPresenter.initialize(this);
        // Remove list of controls from local db
        clearControlListPresenter.initialize(this);

        // Clear previous lists
        clearParkListPresenter.clearParkList();
        clearServicesListPresenter.clearServiceList();
        clearControlListPresenter.clearControlList();

        // Clear user location
        clearUserLocationPresenter.initialize(this);
        // Save park location
        saveParkLocationPresenter.initialize(this);

        binder.srRefresh.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        binder.srRefresh.setOnRefreshListener(() -> {
            getListPresenter.getLocationList();
            showProgress(true);
        });
    }

    @Override
    public void onGetParkList(List<Park> parkList) {
        //save list of parks in local db
        if (parkList != null) {
            for (Park park : parkList) {
                Log.i(TAG, "List of PARKS == park_name: " + park.parkName + " - park_id: " + park.parkId);
            }
            clearUserLocationPresenter.clearUserLocation();
            saveParkListPresenter.saveParkList(parkList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CONTEXT);
            ParkListAdapter adapter = new ParkListAdapter(parkList, this);
            binder.rvParkList.setLayoutManager(layoutManager);
            binder.rvParkList.setHasFixedSize(false);
            binder.rvParkList.setAdapter(adapter);
            showProgress(false);
            if (binder.srRefresh.isRefreshing()) {
                binder.srRefresh.setRefreshing(false);
            }
        }
    }

    @Override
    public void onGetListFailure(String message) {
        showProgress(false);
        showMessage(" -onGetListFailure: " + message);
    }

    @Override
    public void onParkListCleared(boolean cleared) {
        Log.i(TAG, "park list cleared: " + cleared);
    }

    @Override
    public void onParkListClearedFailure(String message) {
        showMessage(" -onParkListClearedFailure: " + message);
    }

    @Override
    public void onGetServiceList(List<Service> serviceList) {
        //save list of services in local db
        if (serviceList != null) {
            for (Service service : serviceList) {
                Log.i(TAG, "List of SERVICES == service_name: " + service.serviceName +
                        " - service_id: " + service.serviceId +
                        " - park_id: " + service.serviceParkId);
            }
            saveServiceListPresenter.saveServiceList(serviceList);
        }
    }

    @Override
    public void onServiceListCleared(boolean cleared) {
        Log.i(TAG, "onServiceListCleared: " + cleared);
    }

    @Override
    public void onServiceListClearedFailure(String message) {
        showMessage(" -onServiceListClearedFailure: " + message);
    }

    @Override
    public void onGetControlList(List<Control> controlList) {
        //save list of controls in local db
        if (controlList != null) {
            for (Control control : controlList) {
                Log.i(TAG, "List of CONTROLS == control_name: " + control.controlName +
                        " - control_id: " + control.controlId +
                        " - service_id: " + control.controlServiceId +
                        " - park_id:" + control.controlParkId);
            }
        }
        saveControlListPresenter.saveControlList(controlList);
    }

    @Override
    public void onControlListCleared(boolean cleared) {
        Log.i(TAG, "List of control cleared: " + cleared);
    }

    @Override
    public void onControlListClearedFailure(String message) {
        showMessage(" -onControlListClearedFailure: " + message);
    }

    @Override
    public void onUserLocationCleared(boolean cleared) {
        Log.i(TAG, "user location cleared: " + cleared);
    }

    @Override
    public void onUserLocationClearedFailure(String message) {
        showMessage(" -onUserLocationClearedFailure: " + message);
    }

    @Override
    public void onParkSelected(Park park) {
        if (park != null) {
            this.mPark = park;
            Log.i(TAG, "Park selected: " + park.parkName + " - " + park.parkId);
            saveParkLocationPresenter.saveParkLocation(park);
        }
    }

    @Override
    public void onParkListSaved(boolean saved) {
        Log.i(TAG, "park list saved: " + saved);
    }

    @Override
    public void onParkListSavedFailure(String message) {
        showMessage(" -onParkListSavedFailure: " + message);
    }

    @Override
    public void onServiceListSaved(boolean saved) {
        Log.i(TAG, "service list saved: " + saved);
    }

    public void onServiceListSavedFailure(String message) {
        showMessage(" -onServiceListSavedFailure: " + message);
    }

    @Override
    public void onControlListSaved(boolean saved) {
        Log.i(TAG, "control list saved: " + saved);
    }

    @Override
    public void onControlListSavedFailure(String message) {
        showMessage(" -onControlListSavedFailure: " + message);
    }

    @Override
    public void onParkSaved(boolean saved) {
        Log.i(TAG, "park location saved: " + saved);
        if (mPark != null) {
            listener.callFragmentServiceListener(mPark.parkId, mPark.parkName);
        }
    }

    @Override
    public void onParkSavedFailure(String message) {
        showMessage(" -onParkSavedFailure: " + message);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnCallFragmentServiceListener) {
            listener = (OnCallFragmentServiceListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnCallFragmentServiceListener");
        }
    }
}
