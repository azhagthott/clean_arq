package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.control.domain.model.Control;
import dev.zecovery.commons.park.domain.model.Park;
import dev.zecovery.commons.service.domain.model.Service;

public interface GetListContract {

    interface View extends UIProgressView {

        void onGetParkList(List<Park> parkList);

        void onGetServiceList(List<Service> serviceList);

        void onGetControlList(List<Control> controlList);

        void onGetListFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getLocationList();
    }
}
