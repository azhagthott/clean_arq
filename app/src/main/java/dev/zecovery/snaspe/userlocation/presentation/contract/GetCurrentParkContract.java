package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import dev.zecovery.commons.location.domain.model.UserLocation;

public interface GetCurrentParkContract {

    interface View extends UIProgressView {

        void onGetCurrentLocation(UserLocation currentUserLocation);

        void onGetCurrentLocationFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getCurrentLocation();
    }
}
