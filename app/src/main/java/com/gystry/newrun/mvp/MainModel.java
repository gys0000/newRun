package com.gystry.newrun.mvp;

import com.gystry.common.net.BaseCommonBean;
import com.gystry.common.net.RetrofitDeploy;
import com.gystry.newrun.api.Api;
import com.gystry.newrun.bean.LiveListBean;
import com.gystry.newrun.mvp.main.MainContract;
import io.reactivex.Observable;

import java.util.List;

public class MainModel implements MainContract.Model {
    @Override
    public Observable<BaseCommonBean<List<LiveListBean>>> getLiveList() {
        return RetrofitDeploy.getRetrofitManager().getRequestService(Api.BASE_URL).create(Api.class).getLiveList();
    }
}
