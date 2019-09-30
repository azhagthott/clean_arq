package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.control.domain.model.Control;

public interface GetControlListByServiceIdContract {
    interface View extends UIProgressView {
        void onGetControlListByServiceIdList(List<Control> controlList);

        void onGetControlListByServiceIdListFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getControlListByServiceId(int serviceId);
    }
}
