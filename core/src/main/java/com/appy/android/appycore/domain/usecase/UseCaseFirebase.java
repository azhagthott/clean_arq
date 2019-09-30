package com.appy.android.appycore.domain.usecase;

import dagger.internal.Preconditions;
import io.reactivex.Maybe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCaseFirebase<T> {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void execute(UseCaseObserverFirebase<T> disposableObserver) {
        Preconditions.checkNotNull(disposableObserver);
        Maybe<T> maybe = this.createMaybeUseCase()
                .subscribeOn(getSubscribeOn())
                .observeOn(getObserveOn());
        Disposable observer = maybe.subscribe();
        compositeDisposable.add(observer);
    }

    public Maybe<T> getMaybe() {
        return createMaybeUseCase();
    }

    protected Scheduler getSubscribeOn() {
        return Schedulers.io();
    }

    protected Scheduler getObserveOn() {
        return AndroidSchedulers.mainThread();
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Maybe<T> createMaybeUseCase();

}
