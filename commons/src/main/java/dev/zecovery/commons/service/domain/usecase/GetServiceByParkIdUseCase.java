package dev.zecovery.commons.service.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.service.data.local.ServiceLocal;
import dev.zecovery.commons.service.data.repository.ServiceLocalRepository;
import dev.zecovery.commons.service.data.repository.mapper.ServiceLocalToDomainMapper;
import dev.zecovery.commons.service.domain.model.Service;
import io.reactivex.Observable;

public class GetServiceByParkIdUseCase extends UseCase<List<Service>> {

    private ServiceLocalRepository repository;
    private ServiceLocalToDomainMapper mapper;
    private int parkId;

    @Inject
    public GetServiceByParkIdUseCase(ServiceLocalRepository repository, ServiceLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public GetServiceByParkIdUseCase setData(int parkId) {
        this.parkId = parkId;
        return this;
    }

    @Override
    protected Observable<List<Service>> createObservableUseCase() {
        return repository.getServiceByParkId(parkId).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            List<Service> list = new ArrayList<>();
            for (ServiceLocal serviceLocal : response) {
                list.add(mapper.map(serviceLocal));
            }
            return list;
        });
    }
}
