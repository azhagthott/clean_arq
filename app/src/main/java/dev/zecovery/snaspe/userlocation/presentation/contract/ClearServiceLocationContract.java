package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

public interface ClearServiceLocationContract {

    interface View extends UIProgressView {

        void onServiceLocationCleared(boolean cleared);

        void onServiceLocationClearedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void clearServicesLocation();
    }
}
