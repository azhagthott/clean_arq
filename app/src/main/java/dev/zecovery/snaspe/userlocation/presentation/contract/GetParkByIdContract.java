package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import dev.zecovery.commons.park.domain.model.Park;

public interface GetParkByIdContract {

    interface View extends UIProgressView {

        void onGetParkById(Park park);

        void onGetParkByIdFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getParkById(int parkId);
    }
}