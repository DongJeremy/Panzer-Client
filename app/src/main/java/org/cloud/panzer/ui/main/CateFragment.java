package org.cloud.panzer.ui.main;

import org.cloud.core.base.BaseMvpFragment;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.CateContract;
import org.cloud.panzer.mvp.presenter.CatePresenter;

public class CateFragment extends BaseMvpFragment<CatePresenter> implements CateContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_cate;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useEventBus() {
        return false;
    }


    @Override
    protected CatePresenter createPresenter() {
        return new CatePresenter();
    }
}