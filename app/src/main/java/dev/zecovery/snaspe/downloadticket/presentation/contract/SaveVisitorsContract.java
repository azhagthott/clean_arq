package dev.zecovery.snaspe.downloadticket.presentation.contract;

import com.appy.android.appycore.presentation.contract.BaseViewPresenter;
import com.appy.android.appycore.presentation.contract.UIProgressView;

import java.util.List;

import dev.zecovery.commons.visitor.domain.model.Visitor;

public interface SaveVisitorsContract {

    interface View extends UIProgressView {

        void onVisitorsSaved(boolean saved);

        void onVisitorsSavedFailure(String message);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveVisitors(List<Visitor> visitorList);
    }
}
