package dev.zecovery.commons.ticket.data.repository;

import com.google.gson.JsonObject;

import io.reactivex.Observable;

public interface TicketRepository {

    Observable<JsonObject> getTicketList(String apiKey, int parkId);
}
