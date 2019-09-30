package dev.zecovery.snaspe.userlocation.presentation.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appy.android.appycore.presentation.fragment.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.control.domain.model.Control;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.FragmentControlListBinding;
import dev.zecovery.snaspe.main.presentation.activity.MainActivity;
import dev.zecovery.snaspe.userlocation.di.component.DaggerControlListFragmentComponent;
import dev.zecovery.snaspe.userlocation.presentation.adapter.ControlListAdapter;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetControlListByServiceIdContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveControlLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnSelectControlListener;

import static dev.zecovery.commons.App.PARK_ID;
import static dev.zecovery.commons.App.PARK_NAME;
import static dev.zecovery.commons.App.SERVICE_ID;
import static dev.zecovery.commons.App.SERVICE_NAME;

public class ControlListFragment extends BaseFragment<FragmentControlListBinding> implements
        GetControlListByServiceIdContract.View,
        SaveControlLocationContract.View,
        OnSelectControlListener {

    private static final String TAG = ControlListFragment.class.getCanonicalName();

    @Inject
    GetControlListByServiceIdContract.Presenter getControlListPresenter;
    @Inject
    SaveControlLocationContract.Presenter saveControlLocationPresenter;

    private int mParkId;
    private String mParkName;
    private int mServiceId;
    private String mServiceName;

    public ControlListFragment newInstance(int parkId, String parkName, int serviceId, String serviceName) {
        ControlListFragment fragment = new ControlListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PARK_ID, parkId);
        bundle.putString(PARK_NAME, parkName);
        bundle.putInt(SERVICE_ID, serviceId);
        bundle.putString(SERVICE_NAME, serviceName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void injectDependencies() {
        DaggerControlListFragmentComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_control_list;
    }

    @Override
    protected void initView() {
        showProgress(true);

        // Get list of controls
        getControlListPresenter.initialize(this);

        // Save control selected
        saveControlLocationPresenter.initialize(this);

        if (getArguments() != null) {
            mParkId = getArguments().getInt(PARK_ID);
            mParkName = getArguments().getString(PARK_NAME);
            mServiceId = getArguments().getInt(SERVICE_ID);
            mServiceName = getArguments().getString(SERVICE_NAME);
            getControlListPresenter.getControlListByServiceId(mServiceId);

            Log.i(TAG, "mParkId: " + mParkId);
            Log.i(TAG, "mServiceId: " + mServiceId);
        }
    }

    @Override
    public void onGetControlListByServiceIdList(List<Control> controlList) {
        if (controlList != null) {
            for (Control control : controlList) {
                Log.i(TAG, "List of CONTROLS: " +
                        " - control_name: " + control.controlName +
                        " - control_id: " + control.controlId +
                        " - service_id: " + control.controlServiceId +
                        " - park_id:" + control.controlParkId);
            }
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CONTEXT);
            ControlListAdapter adapter = new ControlListAdapter(controlList, this);
            binder.rvControlList.setLayoutManager(layoutManager);
            binder.rvControlList.setHasFixedSize(false);
            binder.rvControlList.setAdapter(adapter);
            showProgress(false);
        }
    }

    @Override
    public void onGetControlListByServiceIdListFailure(String message) {
        showMessage(" -onGetControlListByServiceIdListFailure: " + message);
        showProgress(false);
    }

    @Override
    public void onControlSelected(Control control) {
        if (control != null) {
            Log.i(TAG, "control selected: " + control.controlName);
            saveControlLocationPresenter.saveControlLocation(mParkId, mParkName, mServiceId, mServiceName, control);
            finishActivity();
        }
    }

    @Override
    public void onControlSaved(boolean saved) {
        Log.i(TAG, "control saved: " + saved);
        startActivity(MainActivity.class);
    }

    @Override
    public void onControlSavedFailure(String message) {
        showMessage(" -onControlSavedFailure: " + message);
    }

    @Override
    public void showProgress(boolean show) {
        binder.pbMainProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Log.e(TAG, "showMessage: " + message);
    }

}
