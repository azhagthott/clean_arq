package dev.zecovery.snaspe.scannqr.presentation.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.appy.android.appycore.presentation.activity.BaseActivity;
import com.appy.android.appycore.util.DateFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.location.domain.model.UserLocation;
import dev.zecovery.commons.park.domain.model.Park;
import dev.zecovery.commons.qrobject.QrObject;
import dev.zecovery.commons.ticket.domain.model.ScannedTicket;
import dev.zecovery.commons.ticket.domain.model.Ticket;
import dev.zecovery.snaspe.R;
import dev.zecovery.snaspe.databinding.ActivityCameraBinding;
import dev.zecovery.snaspe.downloadticket.presentation.contract.GetAllTicketsContract;
import dev.zecovery.snaspe.scannqr.di.component.DaggerCameraComponent;
import dev.zecovery.snaspe.scannqr.presentation.contract.ValidateTicketContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetCurrentParkContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetParkByIdContract;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static dev.zecovery.commons.App.SCAN;
import static dev.zecovery.commons.App.SCAN_ENTRANCE;
import static dev.zecovery.commons.App.SCAN_EXIT;
import static dev.zecovery.commons.App.SCAN_RESERVATION;

public class CameraActivity extends BaseActivity<ActivityCameraBinding> implements
        ZXingScannerView.ResultHandler,
        GetCurrentParkContract.View,
        GetAllTicketsContract.View,
        GetParkByIdContract.View,
        ValidateTicketContract.View {

    private static final String TAG = CameraActivity.class.getCanonicalName();
    private static final int PERMISSIONS_REQUEST_CAMERA = 1111;

    @Inject
    ValidateTicketContract.Presenter validateTicketPresenter;
    @Inject
    GetCurrentParkContract.Presenter getCurrentLocationPresenter;
    @Inject
    GetParkByIdContract.Presenter getParkByIdPresenter;
    @Inject
    GetAllTicketsContract.Presenter getAllTicketsPresenter;

    private ZXingScannerView mScannerView;
    private String mScanAction;
    private int mParkId;
    private int mServiced;
    private int mTextColor;
    private List<Integer> mTicketIdList = new ArrayList<>();
    private String mRawResultString;
    private String[] mResultSplit;

    @Override
    protected void injectDependencies() {
        DaggerCameraComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void initView() {
        clearText();
        getCurrentLocationPresenter.initialize(this);
        validateTicketPresenter.initialize(this);
        getParkByIdPresenter.initialize(this);
        getAllTicketsPresenter.initialize(this);

        getCurrentLocationPresenter.getCurrentLocation();

        getAllTicketsPresenter.getAllTickets();

        mScannerView = new ZXingScannerView(this);
        binder.viewCamera.addView(mScannerView);

        if (getIntent().getExtras() != null) {
            mScanAction = getIntent().getStringExtra(SCAN);
        }
    }

    @Override
    public void onGetCurrentLocation(UserLocation currentUserLocation) {
        if (currentUserLocation != null) {
            this.mParkId = currentUserLocation.userLocationParkId;
            this.mServiced = currentUserLocation.userLocationServiceId;
        }
    }

    @Override
    public void onGetCurrentLocationFailure(String message) {
        showMessage(" -onGetCurrentLocationFailure: " + message);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermission();
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermission();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mScannerView.stopCamera();
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "we need permission to do that", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mScannerView.setResultHandler(this);
                    mScannerView.startCamera();
                } else {
                    Toast.makeText(this, "we need permission to do that", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    @Override
    public void handleResult(Result rawResult) {
        mRawResultString = rawResult.getText();
        mResultSplit = mRawResultString.split(";");
        for (String str : mResultSplit) {
            Log.d(TAG, "handleResult: " + str);
        }
        int ticketId = Integer.parseInt(mResultSplit[2]);
        validateTicketPresenter.validateAlReadyScannedTicket(ticketId);
        new Handler().postDelayed(() -> mScannerView.resumeCameraPreview(CameraActivity.this), 1000);
    }

    @Override
    public void onAlreadyScannedTicket(ScannedTicket ticket) {

        QrObject qrObject = new QrObject();

        getParkName(String.valueOf(ticket.scannedTicketParkId));
        qrObject.qrTicketName = ticket.scannedTicketTicketName;
        qrObject.qrTicketEntryDate = ticket.scannedTicketTicketEntryDate;
        qrObject.qrTicketExitDate = ticket.scannedTicketTicketExitDate;
        qrObject.qrVisitorName = ticket.scannedTicketVisitorName;
        qrObject.qrVisitorPassport = ticket.scannedTicketVisitorId;

        fillForm(qrObject, Color.YELLOW, Color.BLACK);
    }

    @Override
    public void onAlReadyScannedTicketFailure(String message) {
        switch (mScanAction) {
            case SCAN_ENTRANCE:
                validateTicketPresenter.validateEntranceTicket(mParkId, mResultSplit, mRawResultString, mTicketIdList);
                break;
            case SCAN_RESERVATION:
                validateTicketPresenter.validateReservationTicket(mServiced, mResultSplit, mRawResultString, mTicketIdList);
                break;
            case SCAN_EXIT:
                validateTicketPresenter.validateExitTicket(mParkId, mResultSplit, mRawResultString, mTicketIdList);
                break;
        }
    }

    @Override
    public void onInvalidTicket() {
        binder.clTicketData.setBackgroundColor(Color.RED);
        binder.clTicketData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onValidTicket(QrObject qrObject) {
        getParkName(qrObject.qrParkId);
        fillForm(qrObject, Color.GREEN, Color.BLACK);
    }

    @Override
    public void onExistingValidTicket(QrObject qrObject) {
        getParkName(qrObject.qrParkId);
        fillForm(qrObject, Color.YELLOW, Color.BLACK);
    }

    @Override
    public void onNonExistingValidTicket(QrObject qrObject) {
        getParkName(qrObject.qrParkId);
        fillForm(qrObject, Color.BLUE, Color.BLACK);
    }

    @Override
    public void onExpiredValidTicket(QrObject qrObject) {
        getParkName(qrObject.qrParkId);
        fillForm(qrObject, Color.MAGENTA, Color.BLACK);
    }

    @Override
    public void onWrongParkValidTicket(QrObject qrObject) {
        getParkName(qrObject.qrParkId);
        fillForm(qrObject, Color.DKGRAY, Color.WHITE);
    }

    private void getParkName(String parkId) {
        if (parkId != null) {
            getParkByIdPresenter.getParkById(Integer.parseInt(parkId));
        }
    }

    @Override
    public void onReadingError(String message) {
        clearText();
        showMessage(" -onReadingError: " + message);
        Toast.makeText(this, "Asegurese de estar leyendo entreada/reserva", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTicketsDownloadedFailure(String message) {
        showMessage(" -onTicketsDownloadedFailure: " + message);
    }

    @Override
    public void onGetParkById(Park park) {
        if (park != null) {
            binder.tvParkName.setText(park.parkName);
            binder.tvParkName.setTextColor(mTextColor);
        }
    }

    @Override
    public void onGetParkByIdFailure(String message) {
        showMessage(" -onGetParkByIdFailure: " + message);
    }

    @Override
    public void onGetAllTickets(List<Ticket> tickets) {
        if (tickets != null) {
            for (Ticket ticket : tickets) {
                mTicketIdList.add(ticket.ticketId);
            }
        }
    }

    @Override
    public void onGetAllTicketsFailure(String message) {
        showMessage(" -onGetAllTicketsFailure: " + message);
    }

    private void fillForm(QrObject qrObject, int backgroundColor, int textColor) {

        this.mTextColor = textColor;

        binder.tvLblParkName.setTextColor(textColor);
        binder.tvLblTicketType.setTextColor(textColor);
        binder.tvLblEntryDate.setTextColor(textColor);
        binder.tvLblExitDate.setTextColor(textColor);
        binder.tvLblVisitorName.setTextColor(textColor);
        binder.tvLblVisitorPassport.setTextColor(textColor);

        if (qrObject.qrTicketName != null) {
            binder.tvTicketType.setText(qrObject.qrTicketName.replace("+", " "));
            binder.tvTicketType.setTextColor(textColor);
        }

        if (qrObject.qrTicketEntryDate != null) {
            binder.tvEntryDate.setText(DateFormat.timestampToDateString(Long.parseLong(qrObject.qrTicketEntryDate)));
            binder.tvEntryDate.setTextColor(textColor);
        }

        if (qrObject.qrTicketExitDate != null) {
            binder.tvExitDate.setText(DateFormat.timestampToDateString(Long.parseLong(qrObject.qrTicketExitDate)));
            binder.tvExitDate.setTextColor(textColor);
        }

        if (qrObject.qrVisitorName != null) {
            binder.tvVisitorName.setText(qrObject.qrVisitorName.replace("+", " "));
            binder.tvVisitorName.setTextColor(textColor);
        }

        if (qrObject.qrVisitorPassport != null) {
            binder.tvVisitorPassport.setText(qrObject.qrVisitorPassport);
            binder.tvVisitorPassport.setTextColor(textColor);
        }

        binder.clTicketData.setBackgroundColor(backgroundColor);
    }

    private void clearText() {
        binder.tvParkName.setText("");
        binder.tvTicketType.setText("");
        binder.tvEntryDate.setText("");
        binder.tvExitDate.setText("");
        binder.tvVisitorName.setText("");
        binder.tvVisitorPassport.setText("");
        binder.clTicketData.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void showProgress(boolean show) {
    }

    @Override
    public void showMessage(String message) {
        Log.e(TAG, "showMessage: " + message);
    }

}
