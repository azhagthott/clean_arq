package dev.zecovery.snaspe.userlocation.presentation.activity;

import com.appy.android.appycore.presentation.activity.BaseFragmentActivity;

import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.ActivityParkSelectorBinding;
import dev.zecovery.snaspe.userlocation.presentation.fragment.ControlListFragment;
import dev.zecovery.snaspe.userlocation.presentation.fragment.ParkListFragment;
import dev.zecovery.snaspe.userlocation.presentation.fragment.ServiceListFragment;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnCallFragmentControlListener;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnCallFragmentParkListener;
import dev.zecovery.snaspe.userlocation.presentation.listener.OnCallFragmentServiceListener;

public class LocationSelectorActivity extends BaseFragmentActivity<ActivityParkSelectorBinding> implements
        OnCallFragmentServiceListener,
        OnCallFragmentControlListener,
        OnCallFragmentParkListener {

    private static final String TAG = LocationSelectorActivity.class.getCanonicalName();

    @Override
    protected void injectDependencies() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_park_selector;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.content;
    }

    @Override
    protected void initView() {
        setFragment(new ParkListFragment());
    }

    @Override
    public void callFragmentParkListener() {
        setFragment(new ParkListFragment(), R.id.content);
    }

    @Override
    public void callFragmentServiceListener(int parkId, String parkName) {
        setFragment(new ServiceListFragment().newInstance(parkId, parkName), R.id.content);
    }

    @Override
    public void callFragmentControlListener(int parkId, String parkName, int serviceId, String serviceName) {
        setFragment(new ControlListFragment().newInstance(parkId, parkName, serviceId, serviceName), R.id.content);
    }
}
