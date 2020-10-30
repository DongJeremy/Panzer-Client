package org.cloud.panzer.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.util.BannerUtils;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseFragment;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.HomeBannerAdapter;
import org.cloud.panzer.adapter.HomeGoodsListAdapter;
import org.cloud.panzer.adapter.HomeShortcutAdapter;
import org.cloud.panzer.bean.HomeBean;
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
        JsonObject rootJsonObject = new JsonParser().parse(homeInfoData).getAsJsonObject();
        JsonArray index = rootJsonObject.get("index").getAsJsonArray();
        JsonObject jsonObject;
        String name;
        for (int i = 0; i < index.size(); i++) {
            jsonObject = index.get(i).getAsJsonObject();
            //广告图
            if (jsonObject.has("show_list")) {
                handlerAdvList(jsonObject.get("show_list").getAsString());
            }
            //Home7
            if (jsonObject.has("home7")) {
                handlerHome7(jsonObject.get("home7").getAsString());
            }
            //Goods
            name = "goods";
            if (jsonObject.has("goods")) {
                indexBean = new HomeBean();
                indexBean.setType("goods");
                indexBean.setGoodsBean(JsonUtil.json2Bean(jsonObject.getString(name), HomeBean.GoodsBean.class));
                mainArrayList.add(indexBean);
                JsonUtils.jsonToBean(jsonObject.get("goods").getAsString(), HomeBean.GoodsBean.class);
            }
        }


        try {
            JSONObject jsonObject;
            JSONObject mainJSONObject = new JSONObject(homeInfoData);
            JSONObject goodsInfoJSONObject = new JSONObject(mainJSONObject.getString("index"));
            String[] goodsImages = mainJSONObject.getString("goods_image").split(",");


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        gson.fromJson(homeInfoData);
        JSONParser jp = new JSONParser();
        goodses = (ArrayList<Goods>) jp.getJSONParserResult(json, "list");
    }

    private void handlerAdvList(String json) {
        JsonObject rootJsonObject = new JsonParser().parse(json).getAsJsonObject();
        ArrayList<HomeBean.AdvListBean> arrayList = new ArrayList<>();
        JsonArray jsonArray = rootJsonObject.get("item").getAsJsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            arrayList.add(JsonUtils.jsonToBean(jsonArray.get(i).getAsString(), HomeBean.AdvListBean.class));
        }
        if (arrayList.size() == 0) {
            mainBanner.setVisibility(View.GONE);
        } else {
            mainBanner.setVisibility(View.VISIBLE);
            List<String> image = new ArrayList<>();
            final List<String> type = new ArrayList<>();
            final List<String> data = new ArrayList<>();
            for (int i = 0; i < arrayList.size(); i++) {
                image.add(arrayList.get(i).getImage());
                type.add(arrayList.get(i).getType());
                data.add(arrayList.get(i).getData());
            }
            mainBanner.setOnBannerListener((o, position) -> BaseApplication.getInstance().startTypeValue(getActivity(), type.get(position), data.get(position));
            mainBanner.setDatas(image);
            mainBanner.start();
        }
    }

    private void handlerHome7(String json) {
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        for (int i = 0; i < jsonArray.size(); i++) {
            arrayList.add(JsonUtils.jsonToBean(jsonArray.get(i).getAsString(), HomeBean.AdvListBean.class));
        }
        //第一个
        final String squareType = jsonObject.getString("square_type");
        final String squareData = jsonObject.getString("square_data");
        oneTextView.setText(jsonObject.getString("square_ico_name"));
        oneImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("square_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("square_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), oneImageView);
        oneLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), squareType, squareData));
        //第二个
        final String rectangle1Type = jsonObject.getString("rectangle1_type");
        final String rectangle1Data = jsonObject.getString("rectangle1_data");
        twoTextView.setText(jsonObject.getString("rectangle1_ico_name"));
        twoImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("rectangle1_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("rectangle1_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), twoImageView);
        twoLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), rectangle1Type, rectangle1Data));
        //第三个
        final String rectangle2Type = jsonObject.getString("rectangle2_type");
        final String rectangle2Data = jsonObject.getString("rectangle2_data");
        thrTextView.setText(jsonObject.getString("rectangle2_ico_name"));
        thrImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("rectangle2_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("rectangle2_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), thrImageView);
        thrLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), rectangle2Type, rectangle2Data));
        //第四个
        final String rectangle3Type = jsonObject.getString("rectangle3_type");
        final String rectangle3Data = jsonObject.getString("rectangle3_data");
        fouTextView.setText(jsonObject.getString("rectangle3_ico_name"));
        fouImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("rectangle3_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("rectangle3_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), fouImageView);
        fouLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), rectangle3Type, rectangle3Data));
        //第五个
        final String rectangle4Type = jsonObject.getString("rectangle4_type");
        final String rectangle4Data = jsonObject.getString("rectangle4_data");
        fivTextView.setText(jsonObject.getString("rectangle4_ico_name"));
        fivImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("rectangle4_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("rectangle4_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), fivImageView);
        fivLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), rectangle4Type, rectangle4Data));
        //第六个
        final String rectangle5Type = jsonObject.getString("rectangle5_type");
        final String rectangle5Data = jsonObject.getString("rectangle5_data");
        sixTextView.setText(jsonObject.getString("rectangle5_ico_name"));
        sixImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("rectangle5_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("rectangle5_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), sixImageView);
        sixLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), rectangle5Type, rectangle5Data));
        //第七个
        final String rectangle6Type = jsonObject.getString("rectangle6_type");
        final String rectangle6Data = jsonObject.getString("rectangle6_data");
        sevTextView.setText(jsonObject.getString("rectangle6_ico_name"));
        sevImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("rectangle6_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("rectangle6_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), sevImageView);
        sevLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), rectangle6Type, rectangle6Data));
        //第八个
        final String rectangle7Type = jsonObject.getString("rectangle7_type");
        final String rectangle7Data = jsonObject.getString("rectangle7_data");
        eigTextView.setText(jsonObject.getString("rectangle7_ico_name"));
        eigImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("rectangle7_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("rectangle7_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), eigImageView);
        eigLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), rectangle7Type, rectangle7Data));
        //第九个
        final String rectangle8Type = jsonObject.getString("rectangle8_type");
        final String rectangle8Data = jsonObject.getString("rectangle8_data");
        nigTextView.setText(jsonObject.getString("rectangle8_ico_name"));
        nigImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("rectangle8_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("rectangle8_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), nigImageView);
        nigLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), rectangle8Type, rectangle8Data));
        //第十个
        final String rectangle9Type = jsonObject.getString("rectangle9_type");
        final String rectangle9Data = jsonObject.getString("rectangle9_data");
        tenTextView.setText(jsonObject.getString("rectangle9_ico_name"));
        tenImageView.setBackgroundDrawable(BaseApplication.get().getGradientDrawable(BaseApplication.get().dipToPx(28), Color.parseColor(jsonObject.getString("rectangle9_ico_color"))));
        BaseImageLoader.get().display(jsonObject.getString("rectangle9_image"), BaseApplication.get().dipToPx(28), BaseApplication.get().dipToPx(28), tenImageView);
        tenLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), rectangle9Type, rectangle9Data));
        navigationLinearLayout.setVisibility(View.VISIBLE);

    }
}