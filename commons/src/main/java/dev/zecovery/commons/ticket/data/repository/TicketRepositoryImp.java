package dev.zecovery.commons.ticket.data.repository;

import com.google.gson.JsonObject;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.remote.EntranceTicketsApi;
import dev.zecovery.commons.ticket.data.repository.mapper.TicketEntityToDomainMapper;
import io.reactivex.Observable;

public class TicketRepositoryImp implements TicketRepository {

    private static final String TAG = TicketRepositoryImp.class.getCanonicalName();
    private EntranceTicketsApi api;
    private TicketEntityToDomainMapper mapper;

    @Inject
    public TicketRepositoryImp(EntranceTicketsApi api, TicketEntityToDomainMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    @Override
    public Observable<JsonObject> getTicketList(String apiKey, int parkId) {
        return api.downloadTickets(parkId, apiKey).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
