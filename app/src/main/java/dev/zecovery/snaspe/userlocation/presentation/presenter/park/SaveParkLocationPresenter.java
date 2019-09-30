package dev.zecovery.snaspe.userlocation.presentation.presenter.park;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

import dev.zecovery.commons.location.domain.usecase.SaveParkLocationUseCase;
import dev.zecovery.commons.park.domain.model.Park;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveParkLocationContract;

public class SaveParkLocationPresenter implements SaveParkLocationContract.Presenter {

    private SaveParkLocationUseCase saveParkLocationUseCase;
    private SaveParkLocationContract.View view;

    @Inject
    public SaveParkLocationPresenter(SaveParkLocationUseCase saveParkLocationUseCase) {
        this.saveParkLocationUseCase = saveParkLocationUseCase;
    }

    @Override
    public void initialize(SaveParkLocationContract.View view) {
        this.view = view;
    }

    @Override
    public void saveParkLocation(Park park) {
        try {
            saveParkLocationUseCase.setData(park).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onParkSaved(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onParkSavedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onParkSavedFailure(e.getMessage());
        }
    }
}
