package dev.zecovery.commons.ticket.data.repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.reactivex.Observable;

public interface TicketSenderRepository {

    Observable<JsonObject> sendEntryTicket(String s, JsonArray a, String apiKey);

    Observable<JsonObject> sendReservations(String s, String apiKey);

    Observable<JsonObject> sendExitTicket(String s, String apiKey);
}
