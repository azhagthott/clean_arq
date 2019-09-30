package dev.zecovery.snaspe.scannqr.di.module;

import dagger.Module;
import dagger.Provides;
import dev.zecovery.commons.park.domain.usecase.GetParkByIdUseCase;
import dev.zecovery.commons.ticket.domain.usecase.GetScannedTicketByIdUseCase;
import dev.zecovery.commons.ticket.domain.usecase.SaveScannedTicketUseCase;
import dev.zecovery.snaspe.scannqr.presentation.contract.ValidateTicketContract;
import dev.zecovery.snaspe.scannqr.presentation.presenter.ValidateTicketPresenter;
import dev.zecovery.snaspe.userlocation.presentation.contract.GetParkByIdContract;
import dev.zecovery.snaspe.userlocation.presentation.presenter.park.GetParkByIdPresenter;

@Module
public class CameraModule {

    @Provides
    ValidateTicketContract.Presenter provideValidateTicketPresenter(SaveScannedTicketUseCase saveScannedTicketUseCase,
                                                                    GetScannedTicketByIdUseCase getScannedTicketByIdUseCase) {
        return new ValidateTicketPresenter(saveScannedTicketUseCase, getScannedTicketByIdUseCase);
    }

    @Provides
    GetParkByIdContract.Presenter provideGetParkByIdPresenter(GetParkByIdUseCase getParkByIdUseCase) {
        return new GetParkByIdPresenter(getParkByIdUseCase);
    }
}
