package dev.zecovery.commons.control.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.control.data.repository.ControlLocalRepository;
import io.reactivex.Observable;

public class ClearControlListUseCase extends UseCase<Boolean> {

    private ControlLocalRepository repository;

    @Inject
    public ClearControlListUseCase(ControlLocalRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.removeAllControls().map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
