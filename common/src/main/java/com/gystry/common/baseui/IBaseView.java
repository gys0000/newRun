package com.gystry.common.baseui;

import com.gystry.common.net.ErrorBean;
import com.gystry.common.widgets.MultipleStatusView;

public interface IBaseView {

    void showLoading(MultipleStatusView multipleStatusView,String msg);

    void hideLoading();

    void showDialogLoading(String msg);

    void dismissDialogLoading();

    /**
     * 网络异常及数据错误等异常
     * @param errorBean
     */
    void showBusinessError(ErrorBean errorBean);

    void showException(ErrorBean errorBean);
}
