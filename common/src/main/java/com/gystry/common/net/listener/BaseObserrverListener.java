package com.gystry.common.net.listener;

import com.gystry.common.net.ErrorBean;

/**
 * 处理网络中的回调
 *
 * @param <T>
 */
public interface BaseObserrverListener<T> {
    void onSuccess(T result);

    void onComplete();

    void onError(Throwable throwable);

    void onBusinessError(ErrorBean errorBean);
}
