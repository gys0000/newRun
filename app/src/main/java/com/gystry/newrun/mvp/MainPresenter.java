package com.gystry.newrun.mvp;

import android.os.Handler;
import com.gystry.common.net.RetrofitDeploy;
import com.gystry.common.net.listener.RxObserverListener;
import com.gystry.common.widgets.MultipleStatusView;
import com.gystry.newrun.bean.LiveListBean;
import com.gystry.newrun.mvp.main.MainContract;

import java.util.List;

public class MainPresenter extends MainContract.Presenter {
    @Override
    public void getLiveList(final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();

            rxManager.addObserver(RetrofitDeploy.getRetrofitManager().doRequest(mModel.getLiveList(), new RxObserverListener<List<LiveListBean>>(mView) {
                @Override
                public void onSuccess(List<LiveListBean> result) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                    mView.getLiveList(result);
                }
            }));
        }
    }
}
