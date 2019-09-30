package dev.zecovery.commons.park.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.park.data.repository.ParkLocalRepository;
import io.reactivex.Observable;

public class ClearParkListUseCase extends UseCase<Boolean> {

    private ParkLocalRepository repository;

    @Inject
    public ClearParkListUseCase(ParkLocalRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.removeAllParks().map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
