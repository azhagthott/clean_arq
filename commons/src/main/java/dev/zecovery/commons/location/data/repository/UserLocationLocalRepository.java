package dev.zecovery.commons.location.data.repository;

import javax.inject.Inject;

import dev.zecovery.commons.location.data.local.UserLocationLocal;
import dev.zecovery.commons.park.data.local.ParkLocal;
import dev.zecovery.commons.utils.DBRepository;
import io.reactivex.Observable;
import io.realm.Realm;

public class UserLocationLocalRepository extends DBRepository<UserLocationLocal> {

    @Inject
    public UserLocationLocalRepository() {
    }

    @Override
    protected Class<UserLocationLocal> getItemClass() {
        return UserLocationLocal.class;
    }

    public Observable<Boolean> saveUserLocation(UserLocationLocal item) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.sharedRealm.refresh();
            realm.beginTransaction();
            realm.insertOrUpdate(item);
            realm.commitTransaction();
            e.onNext(true);
            e.onComplete();
            realm.close();
        });
    }

    public Observable<Boolean> clearAll() {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            boolean result = realm.where(UserLocationLocal.class)
                    .findAll()
                    .deleteAllFromRealm();
            realm.commitTransaction();
            e.onNext(result);
            e.onComplete();
            realm.close();

        });
    }

    public Observable<UserLocationLocal> getCurrentLocation() {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            UserLocationLocal currentLocation = realm
                    .where(UserLocationLocal.class)
                    .findFirst();

            if (currentLocation != null) {
                e.onNext(currentLocation);
            } else {
                e.onError(new Throwable("NULL park"));
            }
            realm.close();
            e.onComplete();
        });
    }

}
