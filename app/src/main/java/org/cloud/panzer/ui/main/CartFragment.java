package org.cloud.panzer.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;

import org.cloud.core.base.BaseMVPFragment;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.CartContract;
import org.cloud.panzer.mvp.presenter.CartPresenter;

import butterknife.BindView;
import io.github.xudaojie.qrcodelib.CaptureActivity;

public class CartFragment extends BaseMVPFragment<CartPresenter> implements CartContract.View {

    @BindView(R.id.scanImageView)
    AppCompatImageView scanImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_cart;
    }

    @Override
    protected CartPresenter createPresenter() {
        return new CartPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {
        scanImageView.setOnClickListener(v -> {
            startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 1003);
        });
    }

    @Override
    protected void initData() {

    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
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