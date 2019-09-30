package dev.zecovery.commons.ticket.data.remote;

import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EntranceTicketsApi {

    @GET("descarga_ticket_app.php")
    Observable<JsonObject> downloadTickets(@Query("parq_id") int parkId, @Query("key") String apiKey);
}