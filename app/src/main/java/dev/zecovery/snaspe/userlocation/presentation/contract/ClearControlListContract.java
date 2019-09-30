package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

public interface ClearControlListContract {

    interface View extends UIProgressView {

        void onControlListCleared(boolean cleared);

        void onControlListClearedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void clearControlList();
    }
}
