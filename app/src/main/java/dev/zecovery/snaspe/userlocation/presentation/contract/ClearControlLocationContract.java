package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

public interface ClearControlLocationContract {

    interface View extends UIProgressView {

        void onControlLocationCleared(boolean cleared);

        void onControlLocationClearedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void clearControlLocation();
    }
}
