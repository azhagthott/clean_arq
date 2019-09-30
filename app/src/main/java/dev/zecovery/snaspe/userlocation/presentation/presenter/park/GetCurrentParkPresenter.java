package dev.zecovery.snaspe.userlocation.presentation.presenter.park;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

import dev.zecovery.commons.location.domain.model.UserLocation;
import dev.zecovery.commons.location.domain.usecase.GetCurrentParkUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetCurrentParkContract;

public class GetCurrentParkPresenter implements GetCurrentParkContract.Presenter {

    private GetCurrentParkContract.View view;
    private GetCurrentParkUseCase getCurrentParkUseCase;

    @Inject
    public GetCurrentParkPresenter(GetCurrentParkUseCase getCurrentParkUseCase) {
        this.getCurrentParkUseCase = getCurrentParkUseCase;
    }

    @Override
    public void initialize(GetCurrentParkContract.View view) {
        this.view = view;
    }

    @Override
    public void getCurrentLocation() {
        try {
            getCurrentParkUseCase.execute(new UseCaseObserver<UserLocation>() {
                @Override
                public void onNext(UserLocation value) {
                    super.onNext(value);
                    view.onGetCurrentLocation(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onGetCurrentLocationFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onGetCurrentLocationFailure(e.getMessage());
        }
    }
}
