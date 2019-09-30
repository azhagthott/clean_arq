package dev.zecovery.commons.service.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.service.data.repository.ServiceLocalRepository;
import dev.zecovery.commons.service.data.repository.mapper.ServiceLocalToDomainMapper;
import dev.zecovery.commons.service.domain.model.Service;
import io.reactivex.Observable;

public class SaveServiceListUseCase extends UseCase<Boolean> {

    private ServiceLocalRepository repository;
    private ServiceLocalToDomainMapper mapper;
    private List<Service> serviceList;

    @Inject
    public SaveServiceListUseCase(ServiceLocalRepository repository, ServiceLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SaveServiceListUseCase setData(List<Service> serviceList) {
        this.serviceList = serviceList;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.saveServiceList(mapper.reverseMap(serviceList)).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
