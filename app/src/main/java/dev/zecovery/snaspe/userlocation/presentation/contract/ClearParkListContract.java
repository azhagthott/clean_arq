package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

public interface ClearParkListContract {

    interface View extends UIProgressView {

        void onParkListCleared(boolean cleared);

        void onParkListClearedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void clearParkList();
    }
}
