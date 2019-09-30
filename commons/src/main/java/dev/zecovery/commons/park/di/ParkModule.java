package dev.zecovery.commons.park.di;

import com.appy.android.appycore.data.remote.ApiServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.zecovery.commons.BuildConfig;
import dev.zecovery.commons.park.data.remote.ParkApi;
import dev.zecovery.commons.park.data.repository.ParkRepository;
import dev.zecovery.commons.park.data.repository.ParkRepositoryImp;
import dev.zecovery.commons.park.data.repository.mapper.ParkEntityToDomainMapper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class ParkModule {

    @Provides
    ParkRepository provideParkRepository(ParkApi api, ParkEntityToDomainMapper mapper) {
        return new ParkRepositoryImp(api, mapper);
    }

    @Provides
    @Singleton
    ParkApi provideApiParkService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BASIC);
        logging.level(HttpLoggingInterceptor.Level.HEADERS);
        logging.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        return ApiServiceFactory.build(okHttpClient, ParkApi.class, BuildConfig.URL_BASE);
    }
}
