package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.TicketSenderRepository;
import io.reactivex.Observable;

public class SendExitTicketListUseCase extends UseCase<JsonObject> {

    private TicketSenderRepository repository;
    private String s;
    private String apiKey;

    @Inject
    public SendExitTicketListUseCase(TicketSenderRepository repository) {
        this.repository = repository;
    }

    public SendExitTicketListUseCase setData(String s, String apiKey) {
        this.s = s;
        this.apiKey = apiKey;
        return this;
    }

    @Override
    protected Observable<JsonObject> createObservableUseCase() {
        return repository.sendExitTicket(s, apiKey).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
