package dev.zecovery.commons.ticket.data.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.remote.SendScanTicketsApi;
import io.reactivex.Observable;

public class TicketSenderRepositoryImp implements TicketSenderRepository {

    private SendScanTicketsApi api;

    @Inject
    public TicketSenderRepositoryImp(SendScanTicketsApi api) {
        this.api = api;
    }

    @Override
    public Observable<JsonObject> sendEntryTicket(String s, JsonArray a, String apiKey) {
        return api.sendEntryTicket(s, a, apiKey).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }

    @Override
    public Observable<JsonObject> sendReservations(String s, String apiKey) {
        return api.sendReservations(s, apiKey).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }

    @Override
    public Observable<JsonObject> sendExitTicket(String s, String apiKey) {
        return api.sendExitTicket(s, apiKey).map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
