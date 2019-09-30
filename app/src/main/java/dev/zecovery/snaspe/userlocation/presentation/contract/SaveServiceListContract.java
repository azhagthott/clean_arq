package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.service.domain.model.Service;

public interface SaveServiceListContract {

    interface View extends UIProgressView {

        void onServiceListSaved(boolean saved);

        void onServiceListSavedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveServiceList(List<Service> serviceList);
    }
}
