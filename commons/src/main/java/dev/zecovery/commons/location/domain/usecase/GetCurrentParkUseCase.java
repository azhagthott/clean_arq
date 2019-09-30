package dev.zecovery.commons.location.domain.usecase;

import com.appy.android.appycore.domain.usecase.UseCase;

import javax.inject.Inject;

import dev.zecovery.commons.location.data.repository.UserLocationLocalRepository;
import dev.zecovery.commons.location.data.repository.mapper.UserLocationLocalToDomainMapper;
import dev.zecovery.commons.location.domain.model.UserLocation;
import io.reactivex.Observable;

public class GetCurrentParkUseCase extends UseCase<UserLocation> {

    private UserLocationLocalRepository repository;
    private UserLocationLocalToDomainMapper mapper;

    @Inject
    public GetCurrentParkUseCase(UserLocationLocalRepository repository, UserLocationLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    protected Observable<UserLocation> createObservableUseCase() {
        return repository.getCurrentLocation().map(response -> {
            if (response == null) {
                throw new Exception("ERROR");
            }
            return mapper.map(response);
        });
    }
}
