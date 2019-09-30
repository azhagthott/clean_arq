package dev.zecovery.snaspe.userlocation.presentation.presenter.service;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.service.domain.model.Service;
import dev.zecovery.commons.service.domain.usecase.SaveServiceListUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveServiceListContract;

public class SaveServiceListPresenter implements SaveServiceListContract.Presenter {

    private SaveServiceListContract.View view;
    private SaveServiceListUseCase saveServiceListUseCase;

    @Inject
    public SaveServiceListPresenter(SaveServiceListUseCase saveServiceListUseCase) {
        this.saveServiceListUseCase = saveServiceListUseCase;
    }

    @Override
    public void initialize(SaveServiceListContract.View view) {
        this.view = view;
    }

    @Override
    public void saveServiceList(List<Service> serviceList) {
        try {
            saveServiceListUseCase.setData(serviceList).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    super.onNext(value);
                    view.onServiceListSaved(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onServiceListSavedFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onServiceListSavedFailure(e.getMessage());
        }
    }
}
