package com.gystry.common.baseui;

import com.gystry.common.net.RxManager;

public abstract class BasePresenter<V, M> {
    public M mModel;
    public V mView;
    public RxManager rxManager = new RxManager();

    public void setVM(V v, M m) {
        this.mModel = m;
        this.mView = v;
    }

    public void onDestory() {
        rxManager.clear();
        rxManager = null;
        mView = null;
    }
}
