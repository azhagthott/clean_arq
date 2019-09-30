package dev.zecovery.commons.park.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.park.data.local.ParkLocal;
import dev.zecovery.commons.park.domain.model.Park;

public class ParkLocalToDomainMapper extends Mapper<ParkLocal, Park> {

    @Inject
    public ParkLocalToDomainMapper() {
    }

    @Override
    public Park map(ParkLocal value) {
        Park model = new Park();
        model.parkId = value.parkId;
        model.parkName = value.parkName;
        return model;
    }

    @Override
    public ParkLocal reverseMap(Park value) {
        ParkLocal local = new ParkLocal();
        local.parkId = value.parkId;
        local.parkName = value.parkName;
        return local;
    }
}
