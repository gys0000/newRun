package com.gystry.common.baseui;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.gystry.common.net.ErrorBean;
import com.gystry.common.utils.ClassRefectHelper;
import com.gystry.common.widgets.MultipleStatusView;

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity implements IBaseView {
    protected P mPresenter;
    protected M mModel;
    protected MultipleStatusView mMultipleStatusView;
    private Unbinder unbinder;

    protected abstract int getContentViewLayoutID();

    protected abstract void initViewAndEvents();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContentViewLayoutID() != 0) {
            mMultipleStatusView = new MultipleStatusView(this);
            setContentView(View.inflate(this, getContentViewLayoutID(), mMultipleStatusView));
            unbinder = ButterKnife.bind(this);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

        mPresenter = ClassRefectHelper.getT(this, 0);
        mModel = ClassRefectHelper.getT(this, 1);
        if (this instanceof IBaseView) {
            if (mPresenter != null && mModel != null) {
                mPresenter.setVM(this, mModel);
            }
        }
        initViewAndEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.onDestory();
        }
    }

    @Override
    public void showLoading(MultipleStatusView multipleStatusView, String msg) {
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showBusinessError(ErrorBean errorBean) {
        mMultipleStatusView.showError();
    }

    @Override
    public void showException(ErrorBean errorBean) {
        mMultipleStatusView.showNoNetwork();
    }
}
