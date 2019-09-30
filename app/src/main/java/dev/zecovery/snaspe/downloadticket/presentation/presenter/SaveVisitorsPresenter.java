package dev.zecovery.snaspe.downloadticket.presentation.presenter;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.visitor.domain.model.Visitor;
import dev.zecovery.commons.visitor.domain.usecase.SaveVisitorUseCase;
import dev.zecovery.snaspe.downloadticket.presentation.contract.SaveVisitorsContract;

public class SaveVisitorsPresenter implements SaveVisitorsContract.Presenter {

    private SaveVisitorsContract.View view;
    private SaveVisitorUseCase saveVisitorUseCase;

    @Inject
    public SaveVisitorsPresenter(SaveVisitorUseCase saveVisitorUseCase) {
        this.saveVisitorUseCase = saveVisitorUseCase;
    }

    @Override
    public void initialize(SaveVisitorsContract.View view) {
        this.view = view;
    }

    @Override
    public void saveVisitors(List<Visitor> visitorList) {
        try {
            saveVisitorUseCase.setData(visitorList).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onVisitorsSaved(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onVisitorsSavedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onVisitorsSavedFailure(e.getMessage());
        }
    }
}
