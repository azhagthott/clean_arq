package dev.zecovery.snaspe.userlocation.presentation.presenter.control;

import com.appy.android.appycore.domain.usecase.UseCaseObserver;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.control.domain.model.Control;
import dev.zecovery.commons.control.domain.usecase.GetControlByServiceIdUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetControlListByServiceIdContract;

public class GetControlListByServiceIdPresenter implements GetControlListByServiceIdContract.Presenter {

    private GetControlListByServiceIdContract.View view;
    private GetControlByServiceIdUseCase getControlByServiceIdUseCase;

    @Inject
    public GetControlListByServiceIdPresenter(GetControlByServiceIdUseCase getControlByServiceIdUseCase) {
        this.getControlByServiceIdUseCase = getControlByServiceIdUseCase;
    }

    @Override
    public void initialize(GetControlListByServiceIdContract.View view) {
        this.view = view;
    }

    @Override
    public void getControlListByServiceId(int serviceId) {
        try {
            getControlByServiceIdUseCase.setData(serviceId).execute(new UseCaseObserver<List<Control>>() {
                @Override
                public void onNext(List<Control> value) {
                    super.onNext(value);
                    view.onGetControlListByServiceIdList(value);
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    view.onGetControlListByServiceIdListFailure(e.getMessage());
                }
            });
        } catch (Exception e) {
            view.onGetControlListByServiceIdListFailure(e.getMessage());
        }
    }
}
