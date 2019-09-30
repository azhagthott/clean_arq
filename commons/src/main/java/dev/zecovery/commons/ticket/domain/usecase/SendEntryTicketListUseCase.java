package dev.zecovery.commons.ticket.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.repository.TicketSenderRepository;
import io.reactivex.Observable;

public class SendEntryTicketListUseCase extends UseCase<JsonObject> {

    private TicketSenderRepository repository;
    private String s;
    private String apiKey;
    private JsonArray array;

    @Inject
    public SendEntryTicketListUseCase(TicketSenderRepository repository) {
        this.repository = repository;
    }

    public SendEntryTicketListUseCase setData(String s, JsonArray array, String apiKey) {
        this.s = s;
        this.apiKey = apiKey;
        this.array = array;
        return this;
    }

    @Override
    protected Observable<JsonObject> createObservableUseCase() {
        return repository.sendEntryTicket(s, array, apiKey).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
