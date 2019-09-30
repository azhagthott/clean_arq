package dev.zecovery.snaspe.userlocation.di.module;

import dagger.Module;
import dagger.Provides;
import dev.zecovery.commons.location.domain.usecase.ClearLocationUseCase;
import dev.zecovery.commons.location.domain.usecase.GetCurrentParkUseCase;
import dev.zecovery.commons.location.domain.usecase.SaveControlLocationUseCase;
import dev.zecovery.commons.location.domain.usecase.SaveParkLocationUseCase;
import dev.zecovery.commons.location.domain.usecase.SaveServiceLocationUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearUserLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetCurrentParkContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveControlLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveParkLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveServiceLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.presenter.control.SaveControlLocationPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.park.ClearUserLocationPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.park.GetCurrentParkPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.park.SaveParkLocationPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.service.SaveServiceLocationPresenter;

@Module
public class UserLocationModule {

    @Provides
    SaveParkLocationContract.Presenter provideSaveParkLocationPresenter(SaveParkLocationUseCase saveParkLocationUseCase) {
        return new SaveParkLocationPresenter(saveParkLocationUseCase);
    }

    @Provides
    GetCurrentParkContract.Presenter provideGetCurrentParkPresenter(GetCurrentParkUseCase getCurrentParkUseCase) {
        return new GetCurrentParkPresenter(getCurrentParkUseCase);
    }

    @Provides
    SaveServiceLocationContract.Presenter provideSaveServiceLocationPresenter(SaveServiceLocationUseCase saveServiceLocationUseCase) {
        return new SaveServiceLocationPresenter(saveServiceLocationUseCase);
    }

    @Provides
    SaveControlLocationContract.Presenter provideSaveControlLocationPresenter(SaveControlLocationUseCase saveControlLocationUseCase) {
        return new SaveControlLocationPresenter(saveControlLocationUseCase);
    }

    @Provides
    ClearUserLocationContract.Presenter provideClearUserLocationPersenter(ClearLocationUseCase clearLocationUseCase) {
        return new ClearUserLocationPresenter(clearLocationUseCase);
    }
}
