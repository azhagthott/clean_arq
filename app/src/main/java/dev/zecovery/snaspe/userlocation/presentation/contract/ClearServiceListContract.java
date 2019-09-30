package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

public interface ClearServiceListContract {

    interface View extends UIProgressView {

        void onServiceListCleared(boolean cleared);

        void onServiceListClearedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void clearServiceList();
    }
}
