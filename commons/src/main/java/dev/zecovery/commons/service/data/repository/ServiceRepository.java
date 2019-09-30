package dev.zecovery.commons.service.data.repository;

import java.util.List;

import dev.zecovery.commons.service.domain.model.Service;
import io.reactivex.Observable;

public interface ServiceRepository {

    Observable<List<Service>> getServiceList(String apiKey);

}
