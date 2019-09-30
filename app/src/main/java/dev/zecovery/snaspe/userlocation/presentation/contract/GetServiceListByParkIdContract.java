package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.service.domain.model.Service;

public interface GetServiceListByParkIdContract {
    interface View extends UIProgressView {
        void onGetServiceList(List<Service> serviceList);

        void onGetListFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getServiceListByParkId(int parkId);
    }
}
