package org.cloud.panzer.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;

import org.cloud.core.base.BaseMvpFragment;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.SearchContract;
import org.cloud.panzer.mvp.presenter.SearchPresenter;

import butterknife.BindView;
import io.github.xudaojie.qrcodelib.CaptureActivity;

public class SearchFragment extends BaseMvpFragment<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.scanImageView)
    AppCompatImageView scanImageView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_search;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
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

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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