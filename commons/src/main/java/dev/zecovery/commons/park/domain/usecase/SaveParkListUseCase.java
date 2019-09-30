package dev.zecovery.commons.park.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.park.data.repository.ParkLocalRepository;
import dev.zecovery.commons.park.data.repository.mapper.ParkLocalToDomainMapper;
import dev.zecovery.commons.park.domain.model.Park;
import io.reactivex.Observable;

public class SaveParkListUseCase extends UseCase<Boolean> {

    private ParkLocalRepository repository;
    private ParkLocalToDomainMapper mapper;
    private List<Park> parkList;

    @Inject
    public SaveParkListUseCase(ParkLocalRepository repository, ParkLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SaveParkListUseCase setData(List<Park> parkList) {
        this.parkList = parkList;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.saveParkList(mapper.reverseMap(parkList)).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
