package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.ScannedTicketLocalRepository;
import dev.zecovery.commons.ticket.data.repository.mapper.ScannedTicketLocalToDomainMapper;
import dev.zecovery.commons.ticket.domain.model.ScannedTicket;
import io.reactivex.Observable;

public class GetScannedTicketByIdUseCase extends UseCase<ScannedTicket> {

    private ScannedTicketLocalRepository repository;
    private ScannedTicketLocalToDomainMapper mapper;
    private int ticketId;

    @Inject
    public GetScannedTicketByIdUseCase(ScannedTicketLocalRepository repository, ScannedTicketLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public GetScannedTicketByIdUseCase setData(int ticketId) {
        this.ticketId = ticketId;
        return this;
    }

    @Override
    protected Observable<ScannedTicket> createObservableUseCase() {
        return repository.getScannedTicketById(ticketId).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return mapper.map(response);
        });
    }
}
