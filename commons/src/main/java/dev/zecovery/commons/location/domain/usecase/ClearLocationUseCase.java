package dev.zecovery.commons.location.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.location.data.repository.UserLocationLocalRepository;
import io.reactivex.Observable;

public class ClearLocationUseCase extends UseCase<Boolean> {

    private UserLocationLocalRepository repository;

    @Inject
    public ClearLocationUseCase(UserLocationLocalRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.clearAll().map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
