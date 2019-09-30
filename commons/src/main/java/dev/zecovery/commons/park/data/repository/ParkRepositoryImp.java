package dev.zecovery.commons.park.data.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.park.data.entity.ParkEntity;
import dev.zecovery.commons.park.data.entity.ParkEntityWrapper;
import dev.zecovery.commons.park.data.remote.ParkApi;
import dev.zecovery.commons.park.data.repository.mapper.ParkEntityToDomainMapper;
import dev.zecovery.commons.park.domain.model.Park;
import io.reactivex.Observable;

public class ParkRepositoryImp implements ParkRepository {

    private ParkApi api;
    private ParkEntityToDomainMapper mapper;

    @Inject
    public ParkRepositoryImp(ParkApi api, ParkEntityToDomainMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    @Override
    public Observable<List<Park>> getParkList(String key) {
        return api.getParkList(key).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }

            List<ParkEntity> parks = new ArrayList<>();

            for (ParkEntityWrapper wrapper : response) {
                parks.add(wrapper.parque);
            }
            return mapper.map(parks);
        });
    }
}
