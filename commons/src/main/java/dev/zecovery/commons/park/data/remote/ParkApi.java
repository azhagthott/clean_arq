package dev.zecovery.commons.park.data.remote;


import java.util.List;

import dev.zecovery.commons.park.data.entity.ParkEntityWrapper;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ParkApi {

    @GET("descarga_parque_app.php")
    Observable<List<ParkEntityWrapper>> getParkList(@Query("key") String key);
}
