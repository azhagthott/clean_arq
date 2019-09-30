package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.TicketLocalRepository;
import dev.zecovery.commons.ticket.data.repository.mapper.TicketLocalToDomainMapper;
import dev.zecovery.commons.ticket.domain.model.Ticket;
import io.reactivex.Observable;

public class GetAllLocalTicketsUseCase extends UseCase<List<Ticket>> {

    private TicketLocalRepository repository;
    private TicketLocalToDomainMapper mapper;

    @Inject
    public GetAllLocalTicketsUseCase(TicketLocalRepository repository, TicketLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    protected Observable<List<Ticket>> createObservableUseCase() {
        return repository.getAll().map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return mapper.map(response);
        });
    }
}
