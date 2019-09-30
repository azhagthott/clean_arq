package dev.zecovery.commons.park.data.repository;

import java.util.List;

import dev.zecovery.commons.park.domain.model.Park;
import io.reactivex.Observable;

public interface ParkRepository {

    Observable<List<Park>> getParkList(String key);
}
