package dev.zecovery.commons.service.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.service.data.entity.ServiceEntity;
import dev.zecovery.commons.service.domain.model.Service;

public class ServiceEntityToDomainMapper extends Mapper<ServiceEntity, Service> {

    @Inject
    public ServiceEntityToDomainMapper() {
    }

    @Override
    public Service map(ServiceEntity value) {
        Service model = new Service();
        model.serviceId = value.serv_id;
        model.serviceName = value.serv_nombre;
        return model;
    }

    @Override
    public ServiceEntity reverseMap(Service value) {
        return null;
    }
}
