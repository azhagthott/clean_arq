package dev.zecovery.commons.visitor.data.repository;

import java.util.List;

import javax.inject.Inject;

import dev.zecovery.commons.utils.DBRepository;
import dev.zecovery.commons.visitor.data.local.VisitorLocal;
import io.reactivex.Observable;
import io.realm.Realm;

public class VisitorLocalRepository extends DBRepository<VisitorLocal> {

    @Inject
    public VisitorLocalRepository() {
    }

    @Override
    protected Class<VisitorLocal> getItemClass() {
        return VisitorLocal.class;
    }

    public Observable<Boolean> saveVisitorkList(final List<VisitorLocal> visitorLocalList) {
        return Observable.create(e -> {
            for (VisitorLocal visitor : visitorLocalList) {
                Realm realm = Realm.getDefaultInstance();
                realm.executeTransaction(bRealm -> bRealm.copyToRealmOrUpdate(visitor));
                e.onNext(true);
                e.onComplete();
                realm.close();
            }
        });
    }
}
