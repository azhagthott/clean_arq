package dev.zecovery.commons.service.data.repository;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.service.data.local.ServiceLocal;
import dev.zecovery.commons.utils.DBRepository;
import io.reactivex.Observable;
import io.realm.Realm;

public class ServiceLocalRepository extends DBRepository<ServiceLocal> {

    @Inject
    public ServiceLocalRepository() {
    }

    @Override
    protected Class<ServiceLocal> getItemClass() {
        return ServiceLocal.class;
    }


    public Observable<Boolean> saveServiceList(final List<ServiceLocal> serviceLocalList) {
        return Observable.create(e -> {
            for (ServiceLocal serviceLocal : serviceLocalList) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(bRealm -> bRealm.copyToRealmOrUpdate(serviceLocal));
                e.onNext(true);
                e.onComplete();
                realm.close();
            }
        });
    }

    public Observable<List<ServiceLocal>> getServiceByParkId(int parkId) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.sharedRealm.refresh();
            List<ServiceLocal> list = realm
                    .where(getItemClass())
                    .equalTo("serviceParkId", parkId)
                    .findAll();

            if (list != null) {
                e.onNext(list);
            } else {
                e.onError(new Throwable("No service"));
            }
            e.onComplete();
            realm.close();
        });
    }

    public Observable<Boolean> removeAllServices() {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            boolean result = realm.where(ServiceLocal.class)
                    .findAll()
                    .deleteAllFromRealm();
            realm.commitTransaction();
            e.onNext(result);
            e.onComplete();
            realm.close();
        });
    }
}
