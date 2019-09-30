package dev.zecovery.snaspe.userlocation.di.module;

import dagger.Module;
import dagger.Provides;
import dev.zecovery.commons.control.domain.usecase.GetControlByServiceIdUseCase;
import dev.zecovery.commons.location.domain.usecase.SaveControlLocationUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetControlListByServiceIdContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveControlLocationContract;
import dev.zecovery.snaspe.userlocation.presentation.presenter.control.GetControlListByServiceIdPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.control.SaveControlLocationPresenter;

@Module
public class ControlListFragmentModule {

    @Provides
    GetControlListByServiceIdContract.Presenter provideGetControlListByServiceIdPresenter(GetControlByServiceIdUseCase getControlByServiceIdUseCase) {
        return new GetControlListByServiceIdPresenter(getControlByServiceIdUseCase);
    }

    @Provides
    SaveControlLocationContract.Presenter provideSaveControlLocationPresenter(SaveControlLocationUseCase saveControlLocationUseCase) {
        return new SaveControlLocationPresenter(saveControlLocationUseCase);
    }
}
