package dev.zecovery.commons.control.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.control.data.repository.ControlRepository;
import dev.zecovery.commons.control.domain.model.Control;
import io.reactivex.Observable;

public class GetControlListUseCase extends UseCase<List<Control>> {

    private ControlRepository repository;
    private String apiKey;

    @Inject
    public GetControlListUseCase(ControlRepository repository) {
        this.repository = repository;
    }

    public GetControlListUseCase setData(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    @Override
    protected Observable<List<Control>> createObservableUseCase() {
        return repository.getControlList(apiKey).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
