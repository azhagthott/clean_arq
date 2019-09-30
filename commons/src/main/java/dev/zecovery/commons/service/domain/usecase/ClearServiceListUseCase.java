package dev.zecovery.commons.service.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.service.data.repository.ServiceLocalRepository;
import io.reactivex.Observable;

public class ClearServiceListUseCase extends UseCase<Boolean> {

    private ServiceLocalRepository repository;

    @Inject
    public ClearServiceListUseCase(ServiceLocalRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.removeAllServices().map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
