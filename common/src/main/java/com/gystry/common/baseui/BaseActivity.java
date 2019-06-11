package com.gystry.common.baseui;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    private P mPresenter;

    abstract P setmPresenter();
}
