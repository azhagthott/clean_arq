package dev.zecovery.commons.service.data.repository.mapper;

import com.appy.android.appycore.data.repository.Mapper;

import javax.inject.Inject;

import dev.zecovery.commons.service.data.local.ServiceLocal;
import dev.zecovery.commons.service.domain.model.Service;

public class ServiceLocalToDomainMapper extends Mapper<ServiceLocal, Service> {

    @Inject
    public ServiceLocalToDomainMapper() {
    }

    @Override
    public Service map(ServiceLocal value) {
        Service model = new Service();
        model.serviceId = value.serviceId;
        model.serviceName = value.serviceName;
        model.serviceParkId = value.serviceParkId;
        return model;
    }

    @Override
    public ServiceLocal reverseMap(Service value) {
        ServiceLocal local = new ServiceLocal();
        local.serviceId = value.serviceId;
        local.serviceName = value.serviceName;
        local.serviceParkId = value.serviceParkId;
        return local;
    }
}
