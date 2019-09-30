package dev.zecovery.commons.park.data.repository;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.park.data.local.ParkLocal;
import dev.zecovery.commons.utils.DBRepository;
import io.reactivex.Observable;
import io.realm.Realm;

public class ParkLocalRepository extends DBRepository<ParkLocal> {

    @Inject
    public ParkLocalRepository() {
    }

    @Override
    protected Class<ParkLocal> getItemClass() {
        return ParkLocal.class;
    }

    public Observable<Boolean> saveParkList(final List<ParkLocal> parkLocalList) {
        return Observable.create(e -> {
            for (ParkLocal parkLocal : parkLocalList) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(bRealm -> bRealm.copyToRealmOrUpdate(parkLocal));
                e.onNext(true);
                e.onComplete();
                realm.close();
            }
        });
    }

    public Observable<Boolean> removeAllParks() {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            boolean result = realm.where(ParkLocal.class)
                    .findAll()
                    .deleteAllFromRealm();
            realm.commitTransaction();
            e.onNext(result);
            e.onComplete();
            realm.close();
        });
    }

    public Observable<ParkLocal> getParkById(int parkId) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            ParkLocal parkLocal = realm
                    .where(ParkLocal.class)
                    .equalTo("parkId", parkId)
                    .findFirst();

            if (parkLocal != null) {
                e.onNext(parkLocal);
            } else {
                e.onError(new Throwable("NULL park"));
            }
            realm.close();
            e.onComplete();
        });
    }
}
