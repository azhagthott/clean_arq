package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.TicketLocalRepository;
import dev.zecovery.commons.ticket.data.repository.mapper.TicketLocalToDomainMapper;
import dev.zecovery.commons.ticket.domain.model.Ticket;
import io.reactivex.Observable;

public class SaveTicketListUseCase extends UseCase<Boolean> {

    private TicketLocalRepository repository;
    private TicketLocalToDomainMapper mapper;
    private List<Ticket> list;

    @Inject
    public SaveTicketListUseCase(TicketLocalRepository repository, TicketLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SaveTicketListUseCase setData(List<Ticket> list) {
        this.list = list;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.saveAll(mapper.reverseMap(list)).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
