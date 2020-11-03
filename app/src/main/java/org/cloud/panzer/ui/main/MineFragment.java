package org.cloud.panzer.ui.main;

import android.os.Bundle;

import org.cloud.core.base.BaseMVPFragment;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.MineContract;
import org.cloud.panzer.mvp.presenter.MinePresenter;

public class MineFragment extends BaseMVPFragment<MinePresenter> implements MineContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_mine;
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
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

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void showHomeInfoData(String homeInfoData) {

    }

    @Override
    public void showError(String msg) {

    }
}