package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.ScannedTicketLocalRepository;
import dev.zecovery.commons.ticket.data.repository.mapper.ScannedTicketLocalToDomainMapper;
import dev.zecovery.commons.ticket.domain.model.ScannedTicket;
import io.reactivex.Observable;

public class SaveScannedTicketUseCase extends UseCase<Boolean> {

    private ScannedTicketLocalRepository repository;
    private ScannedTicketLocalToDomainMapper mapper;
    private ScannedTicket ticket;

    @Inject
    public SaveScannedTicketUseCase(ScannedTicketLocalRepository repository, ScannedTicketLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SaveScannedTicketUseCase setData(ScannedTicket ticket) {
        this.ticket = ticket;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.save(mapper.reverseMap(ticket)).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
