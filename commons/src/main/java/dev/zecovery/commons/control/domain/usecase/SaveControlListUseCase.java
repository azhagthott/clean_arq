package dev.zecovery.commons.control.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.control.data.repository.ControlLocalRepository;
import dev.zecovery.commons.control.data.repository.mapper.ControlLocalToDomainMapper;
import dev.zecovery.commons.control.domain.model.Control;
import io.reactivex.Observable;

public class SaveControlListUseCase extends UseCase<Boolean> {

    private ControlLocalRepository repository;
    private ControlLocalToDomainMapper mapper;
    private List<Control> controlList;

    @Inject
    public SaveControlListUseCase(ControlLocalRepository repository, ControlLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SaveControlListUseCase setData(List<Control> controlList) {
        this.controlList = controlList;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.saveControlList(mapper.reverseMap(controlList)).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
