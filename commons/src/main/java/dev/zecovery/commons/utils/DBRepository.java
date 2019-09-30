package dev.zecovery.commons.utils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public abstract class DBRepository<T extends RealmObject> {

    private Function<RealmObject, T> realmObjectToTMapper = ts -> (T) ts;

    private Function<RealmResults<T>, List<T>> realmResultsToListMapper = ts -> Realm.getDefaultInstance().copyFromRealm(ts);

    protected abstract Class<T> getItemClass();

    protected String getIdPropertyName() {
        return "id";
    }

    public Observable<Boolean> save(T item) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.insertOrUpdate(item);
            realm.commitTransaction();
            e.onNext(true);
            e.onComplete();
            realm.close();
        });
    }


    public Observable<Boolean> saveAll(final List<T> items) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.insertOrUpdate(items);
            realm.commitTransaction();
            e.onNext(true);
            e.onComplete();
            realm.close();
        });
    }

    public Observable<List<T>> getAll() {
        return Observable.create((ObservableOnSubscribe<RealmResults<T>>) e -> {
            Realm realm = Realm.getDefaultInstance();
            e.onNext(realm.where(getItemClass())
                    .findAll());
            e.onComplete();
            realm.close();
        }).map(realmResultsToListMapper);
    }

    public Observable<T> get(final long id) {
        return Observable.create((ObservableOnSubscribe<RealmObject>) e -> {
            Realm realm = Realm.getDefaultInstance();
            e.onNext(realm.where(getItemClass())
                    .equalTo(getIdPropertyName(), id)
                    .findFirst());

            e.onComplete();
            realm.close();
        }).map(realmObjectToTMapper);
    }

    public Observable<Boolean> delete(final String propertyName, final long id) {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            realm.where(getItemClass()).equalTo(propertyName, id).findFirst().deleteFromRealm();
            realm.commitTransaction();
            e.onNext(true);
            e.onComplete();
            realm.close();
        });
    }

    public Observable<Boolean> clear() {
        return Observable.create(e -> {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            boolean result = realm.where(getItemClass())
                    .findAll()
                    .deleteAllFromRealm();
            realm.commitTransaction();
            e.onNext(result);
            e.onComplete();
            realm.close();
        });
    }
}
