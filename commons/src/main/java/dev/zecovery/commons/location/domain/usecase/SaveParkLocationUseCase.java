package dev.zecovery.commons.location.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.location.data.repository.UserLocationLocalRepository;
import dev.zecovery.commons.location.data.repository.mapper.UserLocationLocalToDomainMapper;
import dev.zecovery.commons.location.domain.model.UserLocation;
import dev.zecovery.commons.park.domain.model.Park;
import io.reactivex.Observable;

public class SaveParkLocationUseCase extends UseCase<Boolean> {

    private UserLocationLocalRepository repository;
    private UserLocationLocalToDomainMapper mapper;
    private int parkId;
    private String parkName;

    @Inject
    public SaveParkLocationUseCase(UserLocationLocalRepository repository, UserLocationLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SaveParkLocationUseCase setData(Park park) {
        this.parkId = park.parkId;
        this.parkName = park.parkName;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {

        UserLocation location = new UserLocation();
        location.userLocationParkId = parkId;
        location.userLocationParkName = parkName;

        return repository.saveUserLocation(mapper.reverseMap(location)).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
