package dev.zecovery.commons.ticket.data.repository;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.local.TicketLocal;
import dev.zecovery.commons.utils.DBRepository;
import io.reactivex.Observable;
import io.realm.Realm;

public class TicketLocalRepository extends DBRepository<TicketLocal> {

    @Inject
    public TicketLocalRepository() {
    }

    @Override
    protected Class<TicketLocal> getItemClass() {
        return TicketLocal.class;
    }

    public Observable<TicketLocal> getTicketById(int ticketId) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            TicketLocal parkLocal = realm
                    .where(TicketLocal.class)
                    .equalTo("ticketId", ticketId)
                    .findFirst();

            if (parkLocal != null) {
                e.onNext(parkLocal);
            } else {
                e.onError(new Throwable("NULL ticket"));
            }
            realm.close();
            e.onComplete();
        });
    }
}
