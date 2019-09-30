package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import dev.zecovery.commons.park.domain.model.Park;

public interface SaveParkLocationContract {

    interface View extends UIProgressView {

        void onParkSaved(boolean saved);

        void onParkSavedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveParkLocation(Park park);
    }
}
