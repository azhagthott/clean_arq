package dev.zecovery.snaspe.userlocation.presentation.presenter.control;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

import dev.zecovery.commons.control.domain.model.Control;
import dev.zecovery.commons.location.domain.usecase.SaveControlLocationUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveControlLocationContract;

public class SaveControlLocationPresenter implements SaveControlLocationContract.Presenter {

    private SaveControlLocationUseCase saveControlLocationUseCase;
    private SaveControlLocationContract.View view;

    @Inject
    public SaveControlLocationPresenter(SaveControlLocationUseCase saveControlLocationUseCase) {
        this.saveControlLocationUseCase = saveControlLocationUseCase;
    }

    @Override
    public void initialize(SaveControlLocationContract.View view) {
        this.view = view;
    }

    @Override
    public void saveControlLocation(int parkId, String parkName, int serviceId, String serviceName, Control control) {
        try {
            saveControlLocationUseCase.setData(parkId, parkName, serviceId, serviceName, control).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onControlSaved(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onControlSavedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onControlSavedFailure(e.getMessage());
        }
    }
}
