package dev.zecovery.commons.location.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.location.data.local.UserLocationLocal;
import dev.zecovery.commons.location.domain.model.UserLocation;

public class UserLocationLocalToDomainMapper extends Mapper<UserLocationLocal, UserLocation> {

    @Inject
    public UserLocationLocalToDomainMapper() {
    }

    @Override
    public UserLocation map(UserLocationLocal value) {
        UserLocation model = new UserLocation();
        model.userLocationId = value.userLocationId;
        model.userLocationParkId = value.userLocationParkId;
        model.userLocationParkName = value.userLocationParkName;
        model.userLocationServiceId = value.userLocationServiceId;
        model.userLocationServiceName = value.userLocationServiceName;
        model.userLocationControlId = value.userLocationControlId;
        model.userLocationControlName = value.userLocationControlName;
        return model;
    }

    @Override
    public UserLocationLocal reverseMap(UserLocation value) {
        UserLocationLocal local = new UserLocationLocal();
        local.userLocationId = value.userLocationId;
        local.userLocationParkId = value.userLocationParkId;
        local.userLocationParkName = value.userLocationParkName;
        local.userLocationServiceId = value.userLocationServiceId;
        local.userLocationServiceName = value.userLocationServiceName;
        local.userLocationControlId = value.userLocationControlId;
        local.userLocationControlName = value.userLocationControlName;
        return local;
    }
}
