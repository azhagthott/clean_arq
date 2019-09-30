package dev.zecovery.commons.park.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.park.data.repository.ParkRepository;
import dev.zecovery.commons.park.domain.model.Park;
import io.reactivex.Observable;

public class GetParkListUseCase extends UseCase<List<Park>> {

    private ParkRepository repository;
    private String apiKey;

    @Inject
    public GetParkListUseCase(ParkRepository repository) {
        this.repository = repository;
    }

    public GetParkListUseCase setData(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    @Override
    protected Observable<List<Park>> createObservableUseCase() {
        return repository.getParkList(apiKey).map(response -> {
            if ((response == null)) {
                throw new Exception("ERROR");
            }
            return response;
        });
    }
}
