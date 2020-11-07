package org.cloud.core.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.mvp.IView;

/**
 * @author ddw
 * @date 2020/11/1 17:12
 * @desc 基类 BaseMvpActivity
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IView {
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initPreparedData() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showError(String msg) {
        BaseToast.getInstance().show(msg);
    }

}
