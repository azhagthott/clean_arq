package dev.zecovery.snaspe.userlocation.presentation.presenter.control;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

import dev.zecovery.commons.control.domain.usecase.ClearControlListUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearControlListContract;

public class ClearControlListPresenter implements ClearControlListContract.Presenter {

    private ClearControlListContract.View view;
    private ClearControlListUseCase clearControlListUseCase;

    @Inject
    public ClearControlListPresenter(ClearControlListUseCase clearControlListUseCase) {
        this.clearControlListUseCase = clearControlListUseCase;
    }

    @Override
    public void initialize(ClearControlListContract.View view) {
        this.view = view;
    }

    @Override
    public void clearControlList() {
        try {
            clearControlListUseCase.execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onControlListCleared(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onControlListClearedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onControlListClearedFailure(e.getMessage());
        }
    }
}
