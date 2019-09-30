package dev.zecovery.commons.service.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.service.data.repository.ServiceRepository;
import dev.zecovery.commons.service.domain.model.Service;
import io.reactivex.Observable;

public class GetServicesListUsaCase extends UseCase<List<Service>> {

    private ServiceRepository repository;
    private String apiKey;

    @Inject
    public GetServicesListUsaCase(ServiceRepository repository) {
        this.repository = repository;
    }

    public GetServicesListUsaCase setData(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    @Override
    protected Observable<List<Service>> createObservableUseCase() {
        return repository.getServiceList(apiKey).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
