package dev.zecovery.commons.control.data.repository;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.control.data.local.ControlLocal;
import dev.zecovery.commons.utils.DBRepository;
import io.reactivex.Observable;
import io.realm.Realm;

public class ControlLocalRepository extends DBRepository<ControlLocal> {

    @Inject
    public ControlLocalRepository() {
    }

    @Override
    protected Class<ControlLocal> getItemClass() {
        return ControlLocal.class;
    }

    public Observable<Boolean> saveControlList(final List<ControlLocal> controlLocalList) {
        return Observable.create(e -> {
            for (ControlLocal controlLocal : controlLocalList) {
                Realm realm = Realm.getDefaultInstance();
                Number currentIdNum = realm.where(ControlLocal.class).max("controlLocalId");

                int nextId;
                if (currentIdNum == null) {
                    nextId = 1;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }
                controlLocal.controlLocalId = nextId;
                realm.executeTransaction(bRealm -> bRealm.copyToRealmOrUpdate(controlLocal));
                e.onNext(true);
                e.onComplete();
                realm.close();
            }
        });
    }


    public Observable<List<ControlLocal>> getControlByServiceId(int serviceId) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.sharedRealm.refresh();
            List<ControlLocal> list = realm
                    .where(getItemClass())
                    .equalTo("controlServiceId", serviceId)
                    .findAll();

            if (list != null) {
                e.onNext(list);
            } else {
                e.onError(new Throwable("No control"));
            }
            e.onComplete();
            realm.close();
        });
    }

    public Observable<Boolean> removeAllControls() {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            boolean result = realm.where(ControlLocal.class)
                    .findAll()
                    .deleteAllFromRealm();
            realm.commitTransaction();
            e.onNext(result);
            e.onComplete();
            realm.close();
        });
    }
}
