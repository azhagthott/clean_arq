package dev.zecovery.commons.control.data.repository;

import java.util.List;

import dev.zecovery.commons.control.domain.model.Control;
import io.reactivex.Observable;

public interface ControlRepository {

    Observable<List<Control>> getControlList(String apiKey);
}

