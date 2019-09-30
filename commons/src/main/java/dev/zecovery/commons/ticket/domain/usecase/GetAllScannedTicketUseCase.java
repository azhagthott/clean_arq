package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.ScannedTicketLocalRepository;
import dev.zecovery.commons.ticket.data.repository.mapper.ScannedTicketLocalToDomainMapper;
import dev.zecovery.commons.ticket.domain.model.ScannedTicket;
import io.reactivex.Observable;

public class GetAllScannedTicketUseCase extends UseCase<List<ScannedTicket>> {

    private ScannedTicketLocalRepository repository;
    private ScannedTicketLocalToDomainMapper mapper;
    private String type;

    @Inject
    public GetAllScannedTicketUseCase(ScannedTicketLocalRepository repository, ScannedTicketLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public GetAllScannedTicketUseCase setData(String type) {
        this.type = type;
        return this;
    }

    @Override
    protected Observable<List<ScannedTicket>> createObservableUseCase() {
        return repository.getAllScannedTicketByType(type).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return mapper.map(response);
        });
    }
}
