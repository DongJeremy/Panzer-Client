package org.cloud.panzer.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.util.BannerUtils;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseMVPFragment;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.PanzerApplication;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.HomeBannerAdapter;
import org.cloud.panzer.adapter.HomeGoodsListAdapter;
import org.cloud.panzer.adapter.HomeNavListAdapter;
import org.cloud.panzer.bean.HomeBean;
import org.cloud.panzer.mvp.contract.HomeContract;
import org.cloud.panzer.mvp.presenter.HomePresenter;
import org.cloud.panzer.ui.home.ChatListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.github.xudaojie.qrcodelib.CaptureActivity;

@SuppressWarnings("rawtypes")
public class HomeFragment extends BaseMVPFragment<HomePresenter> implements HomeContract.View {

    private HomeNavListAdapter mHomeNavListAdapter;
    private HomeGoodsListAdapter mHomeGoodsListAdapter;
    private List<HomeBean.ShortcutBean> mShortcutList = new ArrayList<>();
    private List<String> mBannerList = new ArrayList<>();
    private ArrayList<HomeBean.GoodsBean.ItemBean> mGoodsList = new ArrayList<>();

    @BindView(R.id.mainBanner)
    @SuppressWarnings("rawtypes")
    Banner mainBanner;

    @BindView(R.id.messageImageView)
    AppCompatImageView messageImageView;

    @BindView(R.id.noticeMarqueeView)
    MarqueeView noticeMarqueeView;

    @BindView(R.id.mainCategoryRecyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.mainRecyclerView)
    RecyclerView mainRecyclerView;

    @BindView(R.id.scanImageView)
    AppCompatImageView scanImageView;

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
        mHomeNavListAdapter = new HomeNavListAdapter(getActivity(), mShortcutList);
        mainBanner.setAdapter(new HomeBannerAdapter(mBannerList));
        mainBanner.setIndicator(new RectangleIndicator(getActivity()));
        mainBanner.setIndicatorSpace((int) BannerUtils.dp2px(4));
        mainBanner.setIndicatorRadius(0);
        mHomeGoodsListAdapter = new HomeGoodsListAdapter(getActivity(), mGoodsList);
    }

    @Override
    protected void initListener() {
        scanImageView.setOnClickListener(v ->
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 1003));
        messageImageView.setOnClickListener(view ->
                ((PanzerApplication) BaseApplication.getInstance()).startCheckLogin(getActivity(), ChatListActivity.class));
    }

    @Override
    protected void initData() {
        mPresenter.requestGridData();

        BaseApplication.getInstance().setRecyclerView(getActivity(), mRecyclerView, mHomeNavListAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 5));
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
    public void showHomeInfoData(String homeInfoData) {
        JsonObject rootJsonObject = new JsonParser().parse(homeInfoData).getAsJsonObject();
        int code = rootJsonObject.get("code").getAsInt();
        if (code != 200) {
            return;
        }
        JsonArray index = rootJsonObject.getAsJsonArray("datas");
        JsonObject jsonObject;
        for (int i = 0; i < index.size(); i++) {
            jsonObject = index.get(i).getAsJsonObject();
            //广告图
            if (jsonObject.has("adv_list")) {
                handlerAdvList(jsonObject.get("adv_list").getAsJsonObject());
            }
            //Home7
            if (jsonObject.has("home_nav")) {
                handlerHome7(jsonObject.get("home_nav").getAsJsonObject());
            }
            //Goods
            if (jsonObject.has("goods")) {
                handlerGoods(jsonObject.get("goods").getAsJsonObject());
            }
        }
    }

    private void handlerGoods(JsonObject jsonObject) {
        ArrayList<HomeBean.GoodsBean.ItemBean> arrayList = new ArrayList<>();
        JsonArray jsonArray = jsonObject.get("item").getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            arrayList.add(JsonUtils.jsonToBean(jsonArray.get(i), HomeBean.GoodsBean.ItemBean.class));
        }
        mGoodsList.clear();
        mGoodsList.addAll(arrayList);
        mHomeGoodsListAdapter.notifyDataSetChanged();
    }

    private void handlerAdvList(JsonObject jsonObject) {
        JsonArray jsonArray = jsonObject.get("item").getAsJsonArray();
        ArrayList<HomeBean.AdvListBean> arrayList = new ArrayList<>(JsonUtils.jsonToList(jsonArray, HomeBean.AdvListBean.class));
        if (arrayList.size() == 0) {
            mainBanner.setVisibility(View.GONE);
        } else {
            mainBanner.setVisibility(View.VISIBLE);
            List<String> image = new ArrayList<>();
            final List<String> type = new ArrayList<>();
            final List<String> data = new ArrayList<>();
            for (int i = 0; i < arrayList.size(); i++) {
                image.add(arrayList.get(i).image);
                type.add(arrayList.get(i).type);
                data.add(arrayList.get(i).data);
            }
            mainBanner.setOnBannerListener((o, position) -> BaseApplication.getInstance().startTypeValue(getActivity(), type.get(position), data.get(position)));
            mainBanner.setDatas(image);
            mainBanner.start();
        }
    }

    private void handlerHome7(JsonObject jsonObject) {
        mShortcutList.clear();
        //第一个
        JsonArray jsonArray = jsonObject.get("item").getAsJsonArray();
        ArrayList<HomeBean.ShortcutBean> arrayList = new ArrayList<>(JsonUtils.jsonToList(jsonArray, HomeBean.ShortcutBean.class));
        mShortcutList.addAll(arrayList);
        mHomeNavListAdapter.notifyDataSetChanged();
    }
}