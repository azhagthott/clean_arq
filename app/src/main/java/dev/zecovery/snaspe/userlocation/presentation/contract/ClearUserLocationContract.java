package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

public interface ClearUserLocationContract {

    interface View extends UIProgressView {

        void onUserLocationCleared(boolean cleared);

        void onUserLocationClearedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void clearUserLocation();
    }
}
