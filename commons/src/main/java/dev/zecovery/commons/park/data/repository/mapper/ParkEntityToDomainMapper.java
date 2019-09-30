package dev.zecovery.commons.park.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import javax.inject.Inject;

import dev.zecovery.commons.park.data.entity.ParkEntity;
import dev.zecovery.commons.park.domain.model.Park;

public class ParkEntityToDomainMapper extends Mapper<ParkEntity, Park> {

    @Inject
    public ParkEntityToDomainMapper() {
    }

    @Override
    public Park map(ParkEntity value) {
        Park model = new Park();
        model.parkId = value.parq_id;
        model.parkName = value.parq_nombre;
        model.parkServiceList = new JsonArray();
        for (JsonElement element : value.servicio) {
            model.parkServiceList.add(element);
        }
        return model;
    }

    @Override
    public ParkEntity reverseMap(Park value) {
        return null;
    }
}
