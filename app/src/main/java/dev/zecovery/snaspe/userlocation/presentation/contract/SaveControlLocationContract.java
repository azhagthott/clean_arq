package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import dev.zecovery.commons.control.domain.model.Control;

public interface SaveControlLocationContract {

    interface View extends UIProgressView {

        void onControlSaved(boolean saved);

        void onControlSavedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveControlLocation(int parkId, String parkName, int serviceId, String serviceName, Control control);
    }
}
