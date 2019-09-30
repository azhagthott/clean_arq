package dev.zecovery.snaspe.userlocation.di.module;

import dagger.Module;
import dagger.Provides;
import dev.zecovery.commons.control.domain.usecase.ClearControlListUseCase;
import dev.zecovery.commons.control.domain.usecase.SaveControlListUseCase;
import dev.zecovery.commons.park.domain.usecase.ClearParkListUseCase;
import dev.zecovery.commons.park.domain.usecase.GetParkListUseCase;
import dev.zecovery.commons.park.domain.usecase.SaveParkListUseCase;
import dev.zecovery.commons.service.domain.usecase.ClearServiceListUseCase;
import dev.zecovery.commons.service.domain.usecase.SaveServiceListUseCase;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearControlListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearParkListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.ClearServiceListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveControlListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveParkListContract;
import dev.zecovery.snaspe.userlocation.presentation.contract.SaveServiceListContract;
import dev.zecovery.snaspe.userlocation.presentation.presenter.GetListPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.control.ClearControlListPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.control.SaveControlListPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.park.ClearParkListPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.park.SaveParkListPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.service.ClearServiceListPresenter;
import dev.zecovery.snaspe.userlocation.presentation.presenter.service.SaveServiceListPresenter;

@Module
public class ParkListFragmentModule {

    @Provides
    GetListContract.Presenter provideGetLisPresenter(GetParkListUseCase getParkListUseCase) {
        return new GetListPresenter(getParkListUseCase);
    }

    @Provides
    SaveParkListContract.Presenter provideSaveParkListPresenter(SaveParkListUseCase saveParkListUseCase) {
        return new SaveParkListPresenter(saveParkListUseCase);
    }

    @Provides
    SaveServiceListContract.Presenter provideSaveServiceListPresenter(SaveServiceListUseCase saveServiceListUseCase) {
        return new SaveServiceListPresenter(saveServiceListUseCase);
    }

    @Provides
    SaveControlListContract.Presenter provideSaveControlListPresenter(SaveControlListUseCase saveControlListUseCase) {
        return new SaveControlListPresenter(saveControlListUseCase);
    }

    @Provides
    ClearParkListContract.Presenter provideClearParkListPresenter(ClearParkListUseCase clearParkListUseCase) {
        return new ClearParkListPresenter(clearParkListUseCase);
    }

    @Provides
    ClearServiceListContract.Presenter provideClearServiceListPresenter(ClearServiceListUseCase clearServiceListUseCase) {
        return new ClearServiceListPresenter(clearServiceListUseCase);
    }

    @Provides
    ClearControlListContract.Presenter provideClearControlListPresenter(ClearControlListUseCase clearControlListUseCase) {
        return new ClearControlListPresenter(clearControlListUseCase);
    }
}
