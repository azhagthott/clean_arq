package dev.zecovery.snaspe.userlocation.presentation.presenter.service;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

import dev.zecovery.commons.service.domain.usecase.ClearServiceListUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearServiceListContract;

public class ClearServiceListPresenter implements ClearServiceListContract.Presenter {

    private ClearServiceListContract.View view;
    private ClearServiceListUseCase clearServiceListUseCase;

    @Inject
    public ClearServiceListPresenter(ClearServiceListUseCase clearServiceListUseCase) {
        this.clearServiceListUseCase = clearServiceListUseCase;
    }

    @Override
    public void initialize(ClearServiceListContract.View view) {
        this.view = view;
    }

    @Override
    public void clearServiceList() {
        try {
            clearServiceListUseCase.execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onServiceListCleared(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onServiceListClearedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onServiceListClearedFailure(e.getMessage());
        }
    }
}
