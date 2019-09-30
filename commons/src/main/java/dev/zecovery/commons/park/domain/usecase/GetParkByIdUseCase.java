package dev.zecovery.commons.park.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.park.data.repository.ParkLocalRepository;
import dev.zecovery.commons.park.data.repository.mapper.ParkLocalToDomainMapper;
import dev.zecovery.commons.park.domain.model.Park;
import io.reactivex.Observable;

public class GetParkByIdUseCase extends UseCase<Park> {

    private ParkLocalRepository repository;
    private ParkLocalToDomainMapper mapper;
    private int parkId;

    @Inject
    public GetParkByIdUseCase(ParkLocalRepository repository, ParkLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public GetParkByIdUseCase setData(int parkId) {
        this.parkId = parkId;
        return this;
    }

    @Override
    protected Observable<Park> createObservableUseCase() {
        return repository.getParkById(parkId).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return mapper.map(response);
        });
    }
}
