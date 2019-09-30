package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.park.domain.model.Park;

public interface SaveParkListContract {

    interface View extends UIProgressView {

        void onParkListSaved(boolean saved);

        void onParkListSavedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveParkList(List<Park> parkList);
    }
}
