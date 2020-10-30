package org.cloud.panzer.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.util.BannerUtils;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseFragment;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.HomeBannerAdapter;
import org.cloud.panzer.adapter.HomeGoodsListAdapter;
import org.cloud.panzer.adapter.HomeShortcutAdapter;
import org.cloud.panzer.mvp.contract.HomeContract;
import org.cloud.panzer.mvp.model.HomeInfoModel;
import org.cloud.panzer.mvp.presenter.HomePresenter;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressWarnings("rawtypes")
public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    private HomeShortcutAdapter mHomeShortcutAdapter;
    private HomeGoodsListAdapter mHomeGoodsListAdapter;
    private List<HomeInfoModel.HomeInfo.HomeShortcut> mShortcutList = new ArrayList<>();
    private List<HomeInfoModel.HomeInfo.HomeBanner> mBannerList = new ArrayList<>();
    private ArrayList<HomeInfoModel.HomeInfo.Goods> mGoodsList = new ArrayList<>();

    @BindView(R.id.mainBanner)
    @SuppressWarnings("rawtypes")
    Banner mainBanner;

    @BindView(R.id.noticeMarqueeView)
    MarqueeView noticeMarqueeView;

    @BindView(R.id.mainCategoryRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.mainRecyclerView)
    RecyclerView mainRecyclerView;

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
        mHomeShortcutAdapter = new HomeShortcutAdapter(mContext);
        mainBanner.setAdapter(new HomeBannerAdapter(mBannerList));
        mainBanner.setIndicator(new RectangleIndicator(getActivity()));
        mainBanner.setIndicatorSpace((int) BannerUtils.dp2px(4));
        mainBanner.setIndicatorRadius(0);
        mHomeGoodsListAdapter = new HomeGoodsListAdapter(getActivity(), mGoodsList);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter.requestGridData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 5));
        mRecyclerView.setAdapter(mHomeShortcutAdapter);
        BaseApplication.getInstance().setRecyclerView(getActivity(), mainRecyclerView, mHomeGoodsListAdapter);
        mainRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        mainRecyclerView.setPadding(BaseApplication.getInstance().dipToPx(2), 0, BaseApplication.getInstance().dipToPx(2), 0);
    }

    @Override
    protected boolean useEventBus() {
        return false;
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

    @Override
    public void showHomeInfoData(HomeInfoModel.HomeInfo.Data homeInfoData) {
        mShortcutList.clear();
        mShortcutList.addAll(homeInfoData.getHomeShortcutList());
        mHomeShortcutAdapter.setList(mShortcutList);
        mHomeShortcutAdapter.notifyDataSetChanged();
        mBannerList.clear();
        mBannerList.addAll(homeInfoData.getHomeBannerList());
        mainBanner.setDatas(mBannerList);
        mGoodsList.clear();
        mGoodsList.addAll(homeInfoData.getGoodsList());
        mHomeGoodsListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showHomeInfoData(String homeInfoData) {
        try {
            JSONObject jsonObject;
            JSONObject mainJSONObject = new JSONObject(homeInfoData);
            JSONObject goodsInfoJSONObject = new JSONObject(mainJSONObject.getString("index"));
            String[] goodsImages = mainJSONObject.getString("goods_image").split(",");


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Gson gson=new Gson();
        gson.fromJson(homeInfoData);
        JSONParser jp=new JSONParser();
        goodses= (ArrayList<Goods>) jp.getJSONParserResult(json,"list");
    }
}