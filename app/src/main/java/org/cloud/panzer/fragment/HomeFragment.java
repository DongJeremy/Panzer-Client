package org.cloud.panzer.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.util.BannerUtils;

import org.cloud.core.base.BaseFragment;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.HomeBannerAdapter;
import org.cloud.panzer.adapter.HomePageGridAdapter;
import org.cloud.panzer.mvp.contract.HomeContract;
import org.cloud.panzer.mvp.model.HomeInfoModel;
import org.cloud.panzer.mvp.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressWarnings("rawtypes")
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    private HomePageGridAdapter mAdapter;
    private List<HomeInfoModel.HomeGrid.Item> mList;
    private List<HomeInfoModel.HomeBanner.Item> mBannerList;

    @BindView(R.id.mainBanner)
    @SuppressWarnings("rawtypes")
    Banner mainBanner;

    @BindView(R.id.noticeMarqueeView)
    MarqueeView noticeMarqueeView;

    @BindView(R.id.mainCategoryRecyclerView)
    RecyclerView mRecyclerView;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_home;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mAdapter = new HomePageGridAdapter(mContext);
        mBannerList = new ArrayList<>();
        mainBanner.setAdapter(new HomeBannerAdapter(mBannerList));
        mainBanner.setIndicator(new RectangleIndicator(getActivity()));
        mainBanner.setIndicatorSpace((int) BannerUtils.dp2px(4));
        mainBanner.setIndicatorRadius(0);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.requestGridData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 5));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.requestBannerData();
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void showGridData(List<HomeInfoModel.HomeGrid.Item> homeGrids) {
        mList.clear();
        mList.addAll(homeGrids);
        mAdapter.setList(mList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBannerData(List<HomeInfoModel.HomeBanner.Item> homeBanner) {
        mBannerList.clear();
        mBannerList.addAll(homeBanner);
        mainBanner.setDatas(mBannerList);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onStart() {
        super.onStart();
        mainBanner.start();
        noticeMarqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainBanner.stop();
        noticeMarqueeView.stopFlipping();
    }
}