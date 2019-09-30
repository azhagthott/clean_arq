package dev.zecovery.snaspe.userlocation.presentation.presenter.park;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

import dev.zecovery.commons.park.domain.usecase.ClearParkListUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearParkListContract;

public class ClearParkListPresenter implements ClearParkListContract.Presenter {

    private ClearParkListContract.View view;
    private ClearParkListUseCase clearParkListUseCase;

    @Inject
    public ClearParkListPresenter(ClearParkListUseCase clearParkListUseCase) {
        this.clearParkListUseCase = clearParkListUseCase;
    }

    @Override
    public void initialize(ClearParkListContract.View view) {
        this.view = view;
    }

    @Override
    public void clearParkList() {
        try {
            clearParkListUseCase.execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onParkListCleared(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onParkListClearedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onParkListClearedFailure(e.getMessage());
        }
    }
}
