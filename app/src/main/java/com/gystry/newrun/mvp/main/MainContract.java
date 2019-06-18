package com.gystry.newrun.mvp.main;

import com.gystry.common.baseui.BaseModel;
import com.gystry.common.baseui.BasePresenter;
import com.gystry.common.baseui.IBaseView;
import com.gystry.common.net.BaseCommonBean;
import com.gystry.common.widgets.MultipleStatusView;
import com.gystry.newrun.bean.LiveListBean;
import io.reactivex.Observable;

import java.util.List;

public interface MainContract {
    interface View extends IBaseView {
        void getLiveList(List<LiveListBean> bean);
    }

    interface Model extends BaseModel {
        Observable<BaseCommonBean<List<LiveListBean>>> getLiveList();
    }

    abstract class Presenter extends BasePresenter<MainContract.View, MainContract.Model> {
        public abstract void getLiveList(MultipleStatusView multipleStatusView);
    }
}
