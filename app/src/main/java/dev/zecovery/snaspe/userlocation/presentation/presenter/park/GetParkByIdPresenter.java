package dev.zecovery.snaspe.userlocation.presentation.presenter.park;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

import dev.zecovery.commons.park.domain.model.Park;
import dev.zecovery.commons.park.domain.usecase.GetParkByIdUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetParkByIdContract;

public class GetParkByIdPresenter implements GetParkByIdContract.Presenter {

    private GetParkByIdContract.View view;
    private GetParkByIdUseCase getParkByIdUseCase;

    @Inject
    public GetParkByIdPresenter(GetParkByIdUseCase getParkByIdUseCase) {
        this.getParkByIdUseCase = getParkByIdUseCase;
    }

    @Override
    public void initialize(GetParkByIdContract.View view) {
        this.view = view;
    }

    @Override
    public void getParkById(int parkId) {
        try {
            getParkByIdUseCase.setData(parkId).execute(new UseCaseObserver<Park>() {
                @Override
                public void onNext(Park value) {
                    super.onNext(value);
                    view.onGetParkById(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onGetParkByIdFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onGetParkByIdFailure(e.getMessage());
        }
    }
}
