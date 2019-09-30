package dev.zecovery.commons.location.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.location.data.repository.UserLocationLocalRepository;
import dev.zecovery.commons.location.data.repository.mapper.UserLocationLocalToDomainMapper;
import dev.zecovery.commons.location.domain.model.UserLocation;
import dev.zecovery.commons.service.domain.model.Service;
import io.reactivex.Observable;

public class SaveServiceLocationUseCase extends UseCase<Boolean> {

    private UserLocationLocalRepository repository;
    private UserLocationLocalToDomainMapper mapper;
    private int parkId;
    private Service service;

    @Inject
    public SaveServiceLocationUseCase(UserLocationLocalRepository repository, UserLocationLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SaveServiceLocationUseCase setData(int parkId, Service service) {
        this.parkId = parkId;
        this.service = service;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        UserLocation location = new UserLocation();
        location.userLocationParkId = parkId;
        location.userLocationServiceId = service.serviceId;
        location.userLocationServiceName = service.serviceName;

        return repository.saveUserLocation(mapper.reverseMap(location)).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
