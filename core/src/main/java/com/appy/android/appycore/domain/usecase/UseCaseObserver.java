package com.appy.android.appycore.domain.usecase;

import io.reactivex.observers.DisposableObserver;

public class UseCaseObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T value) {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onComplete() {
    }
}
