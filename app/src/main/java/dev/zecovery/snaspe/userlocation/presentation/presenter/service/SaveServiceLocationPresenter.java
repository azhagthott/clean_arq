package dev.zecovery.snaspe.userlocation.presentation.presenter.service;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

import dev.zecovery.commons.location.domain.usecase.SaveServiceLocationUseCase;
import dev.zecovery.commons.service.domain.model.Service;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveServiceLocationContract;

public class SaveServiceLocationPresenter implements SaveServiceLocationContract.Presenter {

    private SaveServiceLocationContract.View view;
    private SaveServiceLocationUseCase saveServiceLocationUseCase;

    @Inject
    public SaveServiceLocationPresenter(SaveServiceLocationUseCase saveServiceLocationUseCase) {
        this.saveServiceLocationUseCase = saveServiceLocationUseCase;
    }

    @Override
    public void initialize(SaveServiceLocationContract.View view) {
        this.view = view;
    }

    @Override
    public void saveServiceLocation(int parkId, Service service) {
        try {
            saveServiceLocationUseCase.setData(parkId, service).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onServiceSaved(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onServiceSavedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onServiceSavedFailure(e.getMessage());
        }
    }
}