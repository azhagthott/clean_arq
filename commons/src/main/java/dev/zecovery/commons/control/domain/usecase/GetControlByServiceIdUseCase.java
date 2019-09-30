package dev.zecovery.commons.control.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.control.data.repository.ControlLocalRepository;
import dev.zecovery.commons.control.data.repository.mapper.ControlLocalToDomainMapper;
import dev.zecovery.commons.control.domain.model.Control;
import io.reactivex.Observable;

public class GetControlByServiceIdUseCase extends UseCase<List<Control>> {

    private ControlLocalRepository repository;
    private ControlLocalToDomainMapper mapper;
    private int serviceId;

    @Inject
    public GetControlByServiceIdUseCase(ControlLocalRepository repository, ControlLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public GetControlByServiceIdUseCase setData(int serviceId) {
        this.serviceId = serviceId;
        return this;
    }

    @Override
    protected Observable<List<Control>> createObservableUseCase() {
        return repository.getControlByServiceId(serviceId).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return mapper.map(response);
        });
    }
}
