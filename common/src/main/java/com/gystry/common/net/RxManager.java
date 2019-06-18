package com.gystry.common.net;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Observable 和Subscribers 管理
 */
public class RxManager {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * 添加observer
     *
     * @param observer
     */
    public void addObserver(DisposableObserver observer) {
        if (observer != null) {
            compositeDisposable.add(observer);
        }
    }

    public void clear() {
        compositeDisposable.dispose();
    }
}
