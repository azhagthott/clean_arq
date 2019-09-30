package dev.zecovery.snaspe.userlocation.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.control.domain.model.Control;

public interface SaveControlListContract {

    interface View extends UIProgressView {

        void onControlListSaved(boolean saved);

        void onControlListSavedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveControlList(List<Control> controlList);
    }
}
