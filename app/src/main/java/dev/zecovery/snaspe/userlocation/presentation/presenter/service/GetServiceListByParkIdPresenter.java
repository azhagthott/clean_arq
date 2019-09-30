package dev.zecovery.snaspe.userlocation.presentation.presenter.service;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.service.domain.model.Service;
import dev.zecovery.commons.service.domain.usecase.GetServiceByParkIdUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetServiceListByParkIdContract;

public class GetServiceListByParkIdPresenter implements GetServiceListByParkIdContract.Presenter {

    private GetServiceListByParkIdContract.View view;
    private GetServiceByParkIdUseCase getServiceByParkIdUseCase;

    @Inject
    public GetServiceListByParkIdPresenter(GetServiceByParkIdUseCase getServiceByParkIdUseCase) {
        this.getServiceByParkIdUseCase = getServiceByParkIdUseCase;
    }

    @Override
    public void initialize(GetServiceListByParkIdContract.View view) {
        this.view = view;
    }

    @Override
    public void getServiceListByParkId(int parkId) {
        try {
            getServiceByParkIdUseCase.setData(parkId).execute(new UseCaseObserver<List<Service>>() {
                @Override
                public void onNext(List<Service> value) {
                    super.onNext(value);
                    view.onGetServiceList(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onGetListFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onGetListFailure(e.getMessage());
        }
    }
}
