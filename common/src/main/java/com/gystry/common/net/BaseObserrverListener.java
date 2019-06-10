package com.gystry.common.net;

/**
 * 处理网络中的回调
 * @param <T>
 */
public interface BaseObserrverListener<T> {
    void onSuccess(T result);
    void onComplete();
    void onError();
    void onBusinessError(ErrorBean errorBean);
}
