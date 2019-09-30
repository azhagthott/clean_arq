package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import dev.zecovery.commons.service.domain.model.Service;

public interface SaveServiceLocationContract {

    interface View extends UIProgressView {

        void onServiceSaved(boolean saved);

        void onServiceSavedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveServiceLocation(int parkId, Service service);
    }
}
