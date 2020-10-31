package org.cloud.panzer.fragment;

import android.os.Bundle;
import android.view.View;

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
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.HomeBannerAdapter;
import org.cloud.panzer.adapter.HomeGoodsListAdapter;
import org.cloud.panzer.adapter.HomeShortcutAdapter;
import org.cloud.panzer.bean.HomeBean;
import org.cloud.panzer.mvp.contract.HomeContract;
import org.cloud.panzer.mvp.presenter.HomePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

@SuppressWarnings("rawtypes")
public class HomeFragment extends BaseMVPFragment<HomePresenter> implements HomeContract.View {

    private HomeShortcutAdapter mHomeShortcutAdapter;
    private HomeGoodsListAdapter mHomeGoodsListAdapter;
    private List<HomeBean.ShortcutBean> mShortcutList = new ArrayList<>();
    private List<String> mBannerList = new ArrayList<>();
    private ArrayList<HomeBean.GoodsBean.ItemBean> mGoodsList = new ArrayList<>();

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
    public void showHomeInfoData(String homeInfoData) {
        JsonObject rootJsonObject = new JsonParser().parse(homeInfoData).getAsJsonObject();
        int code = rootJsonObject.get("code").getAsInt();
        if(code!=200) {
            return;
        }
        JsonArray index = rootJsonObject.getAsJsonObject("datas").getAsJsonArray("index");
        JsonObject jsonObject;
        for (int i = 0; i < index.size(); i++) {
            jsonObject = index.get(i).getAsJsonObject();
            //广告图
            if (jsonObject.has("show_list")) {
                handlerAdvList(jsonObject.get("show_list").getAsJsonObject());
            }
            //Home7
            if (jsonObject.has("home7")) {
                handlerHome7(jsonObject.get("home7").getAsJsonObject());
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
            arrayList.add(JsonUtils.jsonObjectToBean(jsonArray.get(i).getAsJsonObject(), HomeBean.GoodsBean.ItemBean.class));
        }
        mGoodsList.clear();
        mGoodsList.addAll(arrayList);
        mHomeGoodsListAdapter.notifyDataSetChanged();
    }

    private void handlerAdvList(JsonObject jsonObject) {
        ArrayList<HomeBean.AdvListBean> arrayList = new ArrayList<>();
        JsonArray jsonArray = jsonObject.get("item").getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            arrayList.add(JsonUtils.jsonObjectToBean(jsonArray.get(i).getAsJsonObject(), HomeBean.AdvListBean.class));
        }
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
        mShortcutList.add(new HomeBean.ShortcutBean(jsonObject.get("square_image").getAsString(),
                jsonObject.get("square_type").getAsString(),
                jsonObject.get("square_data").getAsString(),
                jsonObject.get("square_ico_name").getAsString(),
                jsonObject.get("square_ico_color").getAsString()));
        for (int i = 1; i < 10; i++) {
            String rectangleImage = String.format(Locale.getDefault(), "rectangle%d_image", i);
            String rectangleType = String.format(Locale.getDefault(), "rectangle%d_type", i);
            String rectangleData = String.format(Locale.getDefault(), "rectangle%d_data", i);
            String rectangleName = String.format(Locale.getDefault(), "rectangle%d_ico_name", i);
            String rectangleColor = String.format(Locale.getDefault(), "rectangle%d_ico_color", i);
            mShortcutList.add(new HomeBean.ShortcutBean(
                    jsonObject.get(rectangleImage).getAsString(),
                    jsonObject.get(rectangleType).getAsString(),
                    jsonObject.get(rectangleData).getAsString(),
                    jsonObject.get(rectangleName).getAsString(),
                    jsonObject.get(rectangleColor).getAsString()));
        }
        mHomeShortcutAdapter.setList(mShortcutList);
        mHomeShortcutAdapter.notifyDataSetChanged();
    }
}