package dev.zecovery.commons.ticket.data.repository;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.ticket.data.local.ScannedTicketLocal;
import dev.zecovery.commons.utils.DBRepository;
import io.reactivex.Observable;
import io.realm.Realm;

public class ScannedTicketLocalRepository extends DBRepository<ScannedTicketLocal> {

    @Inject
    public ScannedTicketLocalRepository() {
    }

    @Override
    protected Class<ScannedTicketLocal> getItemClass() {
        return ScannedTicketLocal.class;
    }

    public Observable<List<ScannedTicketLocal>> getAllScannedTicketByType(String type) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.sharedRealm.refresh();
            List<ScannedTicketLocal> tickets = realm
                    .where(getItemClass())
                    .like("scannedTicketType", type)
                    .findAll();
            if (tickets != null) {
                e.onNext(tickets);
            } else {
                e.onError(new Throwable("No tickets"));
            }
            e.onComplete();
            realm.close();
        });
    }

    public Observable<ScannedTicketLocal> getScannedTicketById(int scannedTicketId) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.sharedRealm.refresh();
            ScannedTicketLocal ticket = realm
                    .where(getItemClass())
                    .equalTo("scannedTicketId", scannedTicketId)
                    .findFirst();
            if (ticket != null) {
                e.onNext(ticket);
            } else {
                e.onError(new Throwable("No tickets"));
            }
            e.onComplete();
            realm.close();
        });
    }

}
