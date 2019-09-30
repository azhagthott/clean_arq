package dev.zecovery.snaspe.scannqr.presentation.fragment;

import android.content.Intent;
import android.view.View;

import com.appy.android.appycore.presentation.fragment.BaseFragment;

import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.FragmentScanQrBinding;
import dev.zecovery.snaspe.scannqr.presentation.activity.CameraActivity;

import static dev.zecovery.commons.App.SCAN;
import static dev.zecovery.commons.App.SCAN_ENTRANCE;
import static dev.zecovery.commons.App.SCAN_EXIT;
import static dev.zecovery.commons.App.SCAN_RESERVATION;

public class ScanQrFragment extends BaseFragment<FragmentScanQrBinding> implements View.OnClickListener {

    @Override
    protected void injectDependencies() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_scan_qr;
    }

    @Override
    protected void initView() {
        binder.btnScanEntrances.setOnClickListener(this);
        binder.btnScanReservation.setOnClickListener(this);
        binder.btnScanExits.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getContext(), CameraActivity.class);

        switch (view.getId()) {
            case R.id.btn_scan_entrances:
                intent.putExtra(SCAN, SCAN_ENTRANCE);
                startActivity(intent);
                break;
            case R.id.btn_scan_reservation:
                intent.putExtra(SCAN, SCAN_RESERVATION);
                startActivity(intent);
                break;
            case R.id.btn_scan_exits:
                intent.putExtra(SCAN, SCAN_EXIT);
                startActivity(intent);
                break;
        }
    }
}
