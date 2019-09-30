package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.TicketRepository;
import io.reactivex.Observable;

public class GetTicketListUseCase extends UseCase<JsonObject> {

    private TicketRepository repository;
    private String apiKey;
    private int parkId;

    @Inject
    public GetTicketListUseCase(TicketRepository repository) {
        this.repository = repository;
    }

    public GetTicketListUseCase setData(String apiKey, int parkId) {
        this.apiKey = apiKey;
        this.parkId = parkId;
        return this;
    }

    @Override
    protected Observable<JsonObject> createObservableUseCase() {
        return repository.getTicketList(apiKey, parkId).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
