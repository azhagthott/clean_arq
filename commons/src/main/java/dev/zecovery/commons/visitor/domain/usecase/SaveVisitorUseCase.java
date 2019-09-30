package dev.zecovery.commons.visitor.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.visitor.data.repository.VisitorLocalRepository;
import dev.zecovery.commons.visitor.data.repository.mapper.VisitorLocalToDoaminMapper;
import dev.zecovery.commons.visitor.domain.model.Visitor;
import io.reactivex.Observable;

public class SaveVisitorUseCase extends UseCase<Boolean> {

    private VisitorLocalRepository repository;
    private VisitorLocalToDoaminMapper mapper;
    private List<Visitor> visitorList;

    @Inject
    public SaveVisitorUseCase(VisitorLocalRepository repository, VisitorLocalToDoaminMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SaveVisitorUseCase setData(List<Visitor> visitorList) {
        this.visitorList = visitorList;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.saveVisitorkList(mapper.reverseMap(visitorList)).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
