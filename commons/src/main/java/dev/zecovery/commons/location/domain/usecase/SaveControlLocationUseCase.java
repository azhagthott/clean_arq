package dev.zecovery.commons.location.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.control.domain.model.Control;
import dev.zecovery.commons.location.data.repository.UserLocationLocalRepository;
import dev.zecovery.commons.location.data.repository.mapper.UserLocationLocalToDomainMapper;
import dev.zecovery.commons.location.domain.model.UserLocation;
import io.reactivex.Observable;

public class SaveControlLocationUseCase extends UseCase<Boolean> {

    private UserLocationLocalRepository repository;
    private UserLocationLocalToDomainMapper mapper;
    private int parkId;
    private String parkName;
    private int serviceId;
    private String serviceName;
    private Control control;

    @Inject
    public SaveControlLocationUseCase(UserLocationLocalRepository repository, UserLocationLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SaveControlLocationUseCase setData(int parkId, String parkName, int serviceId, String serviceName, Control control) {
        this.parkId = parkId;
        this.parkName = parkName;
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.control = control;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {

        UserLocation location = new UserLocation();
        location.userLocationParkId = parkId;
        location.userLocationParkName = parkName;
        location.userLocationServiceId = serviceId;
        location.userLocationServiceName = serviceName;
        location.userLocationControlId = control.controlId;
        location.userLocationControlName = control.controlName;

        return repository.saveUserLocation(mapper.reverseMap(location)).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
