package dev.zecovery.commons.ticket.data.remote;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SendScanTicketsApi {

    @POST("carga_ticket.php")
    @FormUrlEncoded
    Observable<JsonObject> sendEntryTicket(@Field("e") String s, @Field("e") JsonArray a, @Field("key") String apiKey);

    @POST("carga_ticket.php")
    @FormUrlEncoded
    Observable<JsonObject> sendReservations(@Field("r") String s, @Field("key") String apiKey);

    @POST("carga_ticket.php")
    @FormUrlEncoded
    Observable<JsonObject> sendExitTicket(@Field("s") String s, @Field("key") String apiKey);
}
