package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.TicketLocalRepository;
import dev.zecovery.commons.ticket.data.repository.mapper.TicketLocalToDomainMapper;
import dev.zecovery.commons.ticket.domain.model.Ticket;
import io.reactivex.Observable;

public class GetTicketByIdUseCase extends UseCase<Ticket> {

    private TicketLocalRepository repository;
    private TicketLocalToDomainMapper mapper;
    private int ticketId;

    @Inject
    public GetTicketByIdUseCase(TicketLocalRepository repository, TicketLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public GetTicketByIdUseCase setData(int ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    @Override
    protected Observable<Ticket> createObservableUseCase() {
        return repository.getTicketById(ticketId).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return mapper.map(response);
        });
    }
}
