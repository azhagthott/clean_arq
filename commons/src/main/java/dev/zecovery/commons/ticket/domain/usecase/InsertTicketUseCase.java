package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.TicketLocalRepository;
import dev.zecovery.commons.ticket.data.repository.mapper.TicketLocalToDomainMapper;
import dev.zecovery.commons.ticket.domain.model.Ticket;
import io.reactivex.Observable;

public class InsertTicketUseCase extends UseCase<Boolean> {

    private TicketLocalRepository repository;
    private TicketLocalToDomainMapper mapper;
    private Ticket ticket;

    @Inject
    public InsertTicketUseCase(TicketLocalRepository repository, TicketLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public InsertTicketUseCase setData(Ticket ticket) {
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
