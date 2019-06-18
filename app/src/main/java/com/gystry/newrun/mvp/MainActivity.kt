package com.gystry.newrun.mvp

import android.annotation.SuppressLint
import com.blankj.utilcode.util.LogUtils
import com.gystry.common.baseui.BaseActivity
import com.gystry.common.net.TokenManager
import com.gystry.newrun.R
import com.gystry.newrun.bean.LiveListBean
import com.gystry.newrun.mvp.main.MainContract

@SuppressLint("Registered")
class MainActivity : BaseActivity<MainPresenter, MainModel>(), MainContract.View {

    /**
     * 当函数主体只有一个返回值的时候
     */
    override fun getContentViewLayoutID() = R.layout.activity_main

    override fun initViewAndEvents() {
        TokenManager.getInstance().token = "Bearer 20b4f5b55c3fc30f98120b6bdbda546d"
        mPresenter.getLiveList(mMultipleStatusView)
    }

    override fun getLiveList(bean: MutableList<LiveListBean>?) {
        LogUtils.e(bean?.size)
        if (bean?.size!! > 0) {
            LogUtils.e(bean.size)
        }
    }
}
