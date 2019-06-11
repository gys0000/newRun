package com.gystry.common.baseui;

import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {
    private P mPresenter;

    abstract P getmPresenter();
}
