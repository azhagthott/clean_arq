package dev.zecovery.snaspe.userlocation.di.module;

import dagger.Module;
import dagger.Provides;
import dev.zecovery.commons.location.domain.usecase.SaveServiceLocationUseCase;
import dev.zecovery.commons.service.domain.usecase.GetServiceByParkIdUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetServiceListByParkIdContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveServiceLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.presenter.service.GetServiceListByParkIdPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.service.SaveServiceLocationPresenter;

@Module
public class ServiceListFragmentModule {

    @Provides
    GetServiceListByParkIdContract.Presenter provideGetServiceListByParkIdPresenter(GetServiceByParkIdUseCase getServiceByParkIdUseCase) {
        return new GetServiceListByParkIdPresenter(getServiceByParkIdUseCase);
    }

    @Provides
    SaveServiceLocationContract.Presenter provideSaveServiceLocationPresenter(SaveServiceLocationUseCase saveServiceLocationUseCase) {
        return new SaveServiceLocationPresenter(saveServiceLocationUseCase);
    }
}
