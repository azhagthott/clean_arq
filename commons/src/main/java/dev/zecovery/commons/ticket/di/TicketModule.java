package dev.zecovery.commons.ticket.di;

import com.appy.android.appycore.data.remote.ApiServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dev.zecovery.commons.BuildConfig;
import dev.zecovery.commons.ticket.data.remote.EntranceTicketsApi;
import dev.zecovery.commons.ticket.data.repository.TicketRepository;
import dev.zecovery.commons.ticket.data.repository.TicketRepositoryImp;
import dev.zecovery.commons.ticket.data.repository.mapper.TicketEntityToDomainMapper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
public class TicketModule {

    @Provides
    TicketRepository provideTicketRepository(EntranceTicketsApi api, TicketEntityToDomainMapper mapper) {
        return new TicketRepositoryImp(api, mapper);
    }

    @Provides
    @Singleton
    EntranceTicketsApi provideApiTicketService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.level(HttpLoggingInterceptor.Level.BASIC);
        logging.level(HttpLoggingInterceptor.Level.HEADERS);
        logging.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        return ApiServiceFactory.build(okHttpClient, EntranceTicketsApi.class, BuildConfig.URL_BASE);
    }
}
