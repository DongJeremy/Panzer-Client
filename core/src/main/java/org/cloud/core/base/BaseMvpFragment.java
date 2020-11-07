package org.cloud.core.base;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

/**
 * @author xuhao
 * @date 2018/6/9 17:12
 * @desc fragment 基类
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements IView {

    /**
     * 将代理类通用行为抽出来
     */
    protected T mPresenter;

    @Override
    protected void initPreparedData() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {
        getBaseActivity().showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        getBaseActivity().hideLoadingDialog();
    }

    protected abstract T createPresenter();

    @Override
    public void showError(String msg) {
        BaseToast.getInstance().show(msg);
    }
}
