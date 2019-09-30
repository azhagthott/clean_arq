package dev.zecovery.snaspe.userlocation.presentation.presenter.park;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

import dev.zecovery.commons.location.domain.usecase.ClearLocationUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearUserLocationContract;

public class ClearUserLocationPresenter implements ClearUserLocationContract.Presenter {

    private ClearUserLocationContract.View view;
    private ClearLocationUseCase clearLocationUseCase;

    @Inject
    public ClearUserLocationPresenter(ClearLocationUseCase clearLocationUseCase) {
        this.clearLocationUseCase = clearLocationUseCase;
    }

    @Override
    public void initialize(ClearUserLocationContract.View view) {
        this.view = view;
    }

    @Override
    public void clearUserLocation() {
        try {
            clearLocationUseCase.execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onUserLocationCleared(true);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onUserLocationClearedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onUserLocationClearedFailure(e.getMessage());
        }
    }
}
