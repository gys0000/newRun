package com.gystry.common.net.listener;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.gystry.common.baseui.IBaseView;
import com.gystry.common.baseui.RetrofitException;
import com.gystry.common.net.ErrorBean;

public abstract class RxObserverListener<T> implements BaseObserrverListener<T> {
    private IBaseView mView;

    private RxObserverListener(IBaseView view) {
        this.mView = view;
    }

    @Override
    public void onError(Throwable throwable) {
        RetrofitException.ResponseThrowable responseThrowable = RetrofitException.getResponseThrowable(throwable);
        LogUtils.e(responseThrowable.code + ":" + responseThrowable.message);
        ErrorBean errorBean = new ErrorBean();
        errorBean.setMsg(responseThrowable.message);
        errorBean.setCode(String.valueOf(responseThrowable.code));
        if (mView != null) {
            mView.showException(errorBean);
            mView.dismissDialogLoading();
            ToastUtils.showShort(responseThrowable.message);
        }
    }

    /**
     * 业务错误
     * 接口http结果返回200，但是后台数据返回错误
     *
     * @param errorBean
     */
    @Override
    public void onBusinessError(ErrorBean errorBean) {
        if (mView != null) {
            mView.showBusinessError(errorBean);
            mView.dismissDialogLoading();
            LogUtils.e("onBusinessError msg=" + errorBean.getMsg());
        }
    }

    @Override
    public void onComplete() {

    }
}
