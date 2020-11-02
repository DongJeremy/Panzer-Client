package org.cloud.panzer.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videoplayer.player.VideoView;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseBusClient;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.StatusBarUtil;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.EvaluateGoodsSimpleListAdapter;
import org.cloud.panzer.adapter.GoodsCommendListAdapter;
import org.cloud.panzer.adapter.SpecListAdapter;
import org.cloud.panzer.adapter.VoucherGoodsListAdapter;
import org.cloud.panzer.bean.EvaluateGoodsBean;
import org.cloud.panzer.bean.GoodsCommendBean;
import org.cloud.panzer.bean.VoucherGoodsBean;
import org.cloud.panzer.event.GoodsIdEvent;
import org.cloud.panzer.mvp.contract.GoodsContract;
import org.cloud.panzer.mvp.presenter.GoodsPresenter;
import org.cloud.panzer.view.CenterTextView;
import org.cloud.panzer.view.CountdownTextView;
import org.cloud.panzer.view.ScrollDetailsLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;

public class GoodsActivity extends BaseMvpActivity<GoodsPresenter> implements GoodsContract.View {

    @BindView(R.id.mainSlideDetailsLayout)
    ScrollDetailsLayout mainSlideDetailsLayout;
    @BindView(R.id.mainScrollView)
    NestedScrollView mainScrollView;
    @BindView(R.id.headerRelativeLayout)
    RelativeLayout headerRelativeLayout;
    @BindView(R.id.mainBanner)
    @SuppressWarnings("rawtypes")
    Banner mainBanner;
    @BindView(R.id.mainVideoPlayer)
    VideoView mainVideoPlayer;

    // toolbar
    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.toolbarView)
    View toolbarView;
    @BindView(R.id.toolbarLineView)
    View toolbarLineView;

    @BindView(R.id.favoritesImageView)
    AppCompatImageView favoritesImageView;
    @BindView(R.id.shareImageView)
    AppCompatImageView shareImageView;
    @BindView(R.id.saleRelativeLayout)
    RelativeLayout saleRelativeLayout;
    @BindView(R.id.saleTypeTextView)
    AppCompatTextView saleTypeTextView;
    @BindView(R.id.saleTimeTextView)
    CountdownTextView saleTimeTextView;
    @BindView(R.id.nameTextView)
    AppCompatTextView nameTextView;
    @BindView(R.id.descTextView)
    AppCompatTextView descTextView;
    @BindView(R.id.moneyTextView)
    AppCompatTextView moneyTextView;
    @BindView(R.id.marketPriceTextView)
    AppCompatTextView marketPriceTextView;
    @BindView(R.id.mobileTextView)
    AppCompatTextView mobileTextView;
    @BindView(R.id.saleTextView)
    AppCompatTextView saleTextView;
    @BindView(R.id.activityLinearLayout)
    LinearLayoutCompat activityLinearLayout;
    @BindView(R.id.activityTitleTextView)
    AppCompatTextView activityTitleTextView;
    @BindView(R.id.activityDescTextView)
    AppCompatTextView activityDescTextView;
    @BindView(R.id.manSongLinearLayout)
    LinearLayoutCompat manSongLinearLayout;
    @BindView(R.id.manSongDescTextView)
    AppCompatTextView manSongDescTextView;
    @BindView(R.id.manSongGoodsImageView)
    AppCompatImageView manSongGoodsImageView;
    @BindView(R.id.voucherTextView)
    AppCompatTextView voucherTextView;
    @BindView(R.id.areaRelativeLayout)
    RelativeLayout areaRelativeLayout;
    @BindView(R.id.areaAddressTextView)
    AppCompatTextView areaAddressTextView;
    @BindView(R.id.areaHaveTextView)
    AppCompatTextView areaHaveTextView;
    @BindView(R.id.areaChooseTextView)
    AppCompatTextView areaChooseTextView;
    @BindView(R.id.specRelativeLayout)
    RelativeLayout specRelativeLayout;
    @BindView(R.id.specOneTextView)
    AppCompatTextView specOneTextView;
    @BindView(R.id.specTwoTextView)
    AppCompatTextView specTwoTextView;
    private AppCompatTextView[] specTextView;
    @BindView(R.id.serviceDescTextView)
    AppCompatTextView serviceDescTextView;
    @BindView(R.id.serviceSevDayTextView)
    AppCompatTextView serviceSevDayTextView;
    @BindView(R.id.serviceQualityTextView)
    AppCompatTextView serviceQualityTextView;
    @BindView(R.id.serviceReissueTextView)
    AppCompatTextView serviceReissueTextView;
    @BindView(R.id.serviceLogisticsTextView)
    AppCompatTextView serviceLogisticsTextView;
    @BindView(R.id.evaluateRelativeLayout)
    RelativeLayout evaluateRelativeLayout;
    @BindView(R.id.evaluateDescTextView)
    AppCompatTextView evaluateDescTextView;
    @BindView(R.id.evaluateNumberTextView)
    AppCompatTextView evaluateNumberTextView;
    @BindView(R.id.evaluateRecyclerView)
    RecyclerView evaluateRecyclerView;
    @BindView(R.id.storeRelativeLayout)
    RelativeLayout storeRelativeLayout;
    @BindView(R.id.storeNameTextView)
    AppCompatTextView storeNameTextView;
    @BindView(R.id.storeOwnTextView)
    AppCompatTextView storeOwnTextView;
    @BindView(R.id.storeDescTextView)
    AppCompatTextView storeDescTextView;
    @BindView(R.id.storeDescPercentTextView)
    AppCompatTextView storeDescPercentTextView;
    @BindView(R.id.storeServiceTextView)
    AppCompatTextView storeServiceTextView;
    @BindView(R.id.storeServicePercentTextView)
    AppCompatTextView storeServicePercentTextView;
    @BindView(R.id.storeDeliveryTextView)
    AppCompatTextView storeDeliveryTextView;
    @BindView(R.id.storeDeliveryPercentTextView)
    AppCompatTextView storeDeliveryPercentTextView;
    @BindView(R.id.commendRecyclerView)
    RecyclerView commendRecyclerView;
    @BindView(R.id.customerTextView)
    CenterTextView customerTextView;
    @BindView(R.id.storeTextView)
    CenterTextView storeTextView;
    @BindView(R.id.addCartTextView)
    AppCompatTextView addCartTextView;
    @BindView(R.id.buyTextView)
    AppCompatTextView buyTextView;
    @BindView(R.id.chooseRelativeLayout)
    RelativeLayout chooseRelativeLayout;
    @BindView(R.id.chooseGoodsImageView)
    AppCompatImageView chooseGoodsImageView;
    @BindView(R.id.chooseNameTextView)
    AppCompatTextView chooseNameTextView;
    @BindView(R.id.choosePriceTextView)
    AppCompatTextView choosePriceTextView;
    @BindView(R.id.chooseStorageTextView)
    AppCompatTextView chooseStorageTextView;
    @BindView(R.id.chooseLineOneView)
    View chooseLineOneView;
    @BindView(R.id.chooseLineTwoView)
    View chooseLineTwoView;
    @BindView(R.id.chooseValueOneTextView)
    AppCompatTextView chooseValueOneTextView;
    @BindView(R.id.chooseValueTwoTextView)
    AppCompatTextView chooseValueTwoTextView;
    @BindView(R.id.chooseValueOneRecyclerView)
    RecyclerView chooseValueOneRecyclerView;
    @BindView(R.id.chooseValueTwoRecyclerView)
    RecyclerView chooseValueTwoRecyclerView;
    private View[] chooseLineView;
    private AppCompatTextView[] chooseValueTextView;
    private RecyclerView[] chooseValueRecyclerView;
    @BindView(R.id.chooseAddTextView)
    AppCompatTextView chooseAddTextView;
    @BindView(R.id.chooseNumberEditText)
    AppCompatEditText chooseNumberEditText;
    @BindView(R.id.chooseSubTextView)
    AppCompatTextView chooseSubTextView;
    @BindView(R.id.voucherLinearLayout)
    LinearLayoutCompat voucherLinearLayout;

    @BindView(R.id.mainWebView)
    WebView mainWebView;

    //    @BindView(R.id.voucherStoreNameTextView)
//    AppCompatTextView voucherStoreNameTextView;
    @BindView(R.id.voucherRecyclerView)
    RecyclerView voucherRecyclerView;
    @BindView(R.id.nightTextView)
    AppCompatTextView nightTextView;

    private EvaluateGoodsSimpleListAdapter evaluateGoodsAdapter;
    private ArrayList<EvaluateGoodsBean> evaluateGoodsArrayList;

    private GoodsCommendListAdapter commendAdapter;
    private ArrayList<GoodsCommendBean> commendArrayList;

    private VoucherGoodsListAdapter voucherAdapter;
    private ArrayList<VoucherGoodsBean> voucherArrayList;

    private final ArrayList<String> goodsImageArrayList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> specNameArrayList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> specValueArrayList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> goodsSpecArrayList = new ArrayList<>();
    private ArrayList<HashMap<String, String>> specListArrayList = new ArrayList<>();
    private String[] specString = new String[]{"", ""};

    private String shareUrl;
    private String shareText;
    private String shareTitle;
    private String shareImageUrl = "";
    private String goodsId = "";
    private String storeId = "";
    private String memberId = "";
    private boolean isBackBoolean;

    private String goodsStorageString = "";
    private String lowerLimitString = "";
    private String upperLimitString = "";

    private String goodsIdString;
    private boolean isShowBoolean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_goods;
    }

    @Override
    protected void getIntent(Intent intent) {

    }

    @Override
    protected GoodsPresenter createPresenter() {
        return new GoodsPresenter();
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setImageNoStatusBar(this, true);

        //headerRelativeLayout配置
        LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) this.headerRelativeLayout.getLayoutParams();
        layoutParams.height = BaseApplication.getInstance().getWidth();
        headerRelativeLayout.setLayoutParams(layoutParams);
        // toolbar虚拟高度配置增加StatusBarHeight
        LinearLayoutCompat.LayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) this.toolbarView.getLayoutParams();
        layoutParams2.height = BaseApplication.getInstance().getStatusBarHeight();
        toolbarView.setLayoutParams(layoutParams2);
        setToolbar(this.mainToolbar, "商品详情");
        // toolbar初始状态透明
        this.mainToolbar.setAlpha(0.0f);
        this.toolbarView.setAlpha(0.0f);
        this.toolbarLineView.setAlpha(0.0f);
        // 配置WebView
        BaseApplication.getInstance().setWebView(mainWebView);

        RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.shareImageView.getLayoutParams();
        layoutParams3.height = BaseApplication.getInstance().getWidth() - BaseApplication.getInstance().dipToPx(112);
        this.shareImageView.setLayoutParams(layoutParams3);

        // 配置Banner
        mainBanner.setAdapter(new BannerImageAdapter<String>(goodsImageArrayList) {
            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                BaseImageLoader.getInstance().display(data, holder.imageView);
            }
        });
        // 评价
        evaluateGoodsArrayList = new ArrayList<>();
        evaluateGoodsAdapter = new EvaluateGoodsSimpleListAdapter(evaluateGoodsArrayList);
        BaseApplication.getInstance().setRecyclerView(getActivity(), evaluateRecyclerView, evaluateGoodsAdapter);
        // 推荐
        commendArrayList = new ArrayList<>();
        commendAdapter = new GoodsCommendListAdapter(commendArrayList);
        BaseApplication.getInstance().setRecyclerView(getActivity(), commendRecyclerView, commendAdapter);
        commendRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        // 代币券
        voucherArrayList = new ArrayList<>();
        voucherAdapter = new VoucherGoodsListAdapter(voucherArrayList);
        BaseApplication.getInstance().setRecyclerView(getActivity(), voucherRecyclerView, voucherAdapter);

        chooseValueRecyclerView = new RecyclerView[2];
        chooseValueRecyclerView[0] = chooseValueOneRecyclerView;
        chooseValueRecyclerView[1] = chooseValueTwoRecyclerView;

        chooseValueRecyclerView[0].setVisibility(View.GONE);
        chooseValueRecyclerView[1].setVisibility(View.GONE);
    }

    @Override
    protected void initData() {
        goodsIdString = getIntent().getStringExtra(BaseConstant.DATA_ID);
        if (TextUtils.isEmpty(goodsIdString)) {
            BaseToast.getInstance().showDataError();
            BaseApplication.getInstance().finish(getActivity());
        }
        isShowBoolean = false;
        //setToolbar(mainToolbar, "");
        //updateNavigation(0);
        // 获取数据
        mPresenter.requestGoodsData(goodsIdString);

        specTextView = new AppCompatTextView[2];
        specTextView[0] = specOneTextView;
        specTextView[1] = specTwoTextView;
        chooseLineView = new View[2];
        chooseLineView[0] = chooseLineOneView;
        chooseLineView[1] = chooseLineTwoView;
        chooseValueTextView = new AppCompatTextView[2];
        chooseValueTextView[0] = chooseValueOneTextView;
        chooseValueTextView[1] = chooseValueTwoTextView;

        specTextView[0].setVisibility(View.GONE);
        specTextView[1].setVisibility(View.GONE);
        chooseLineView[0].setVisibility(View.GONE);
        chooseLineView[1].setVisibility(View.GONE);
        chooseValueTextView[0].setVisibility(View.GONE);
        chooseValueTextView[1].setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {
        mainSlideDetailsLayout.setOnSlideDetailsListener(currentTargetIndex -> {
            int i = currentTargetIndex.ordinal();
            if (i == 0) {
                setToolbar(this.mainToolbar, "商品详情");
            } else if (i == 1) {
                setToolbar(this.mainToolbar, "商品介绍");
            }
        });
        mainScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            float scrollYY = (float) v.getScrollY();
            float width = (float) (BaseApplication.getInstance().getWidth() - BaseApplication.getInstance().dipToPx(48));
            int i5 = Float.compare(scrollYY, 0.0f);
            if (i5 == 0) {
                this.mainToolbar.setAlpha(0.0f);
                this.toolbarView.setAlpha(0.0f);
                this.toolbarLineView.setAlpha(0.0f);
            } else if (i5 > 0) {
                float max = 1.0f - Math.max((width - scrollYY) / width, 0.0f);
                this.mainToolbar.setAlpha(max);
                this.toolbarView.setAlpha(max);
                this.toolbarLineView.setAlpha(max);
            } else {
                this.mainToolbar.setAlpha(0.0f);
                this.toolbarView.setAlpha(0.0f);
                this.toolbarLineView.setAlpha(0.0f);
            }
        });
        commendAdapter.setOnItemClickListener((position, bean) -> {
            goodsIdString = bean.getGoodsId();
            mPresenter.requestGoodsData(goodsIdString);
        });
    }

    // 解析json数据
    private void handlerData(String homeInfoData) {
        String temp = "";
        JsonObject rootJsonObject = new JsonParser().parse(homeInfoData).getAsJsonObject();
        int code = rootJsonObject.get("code").getAsInt();
        if (code != 200) {
            return;
        }
        JsonObject mainJsonObject = rootJsonObject.getAsJsonObject("datas");
        JsonObject goodsInfoJSONObject = mainJsonObject.getAsJsonObject("goods_info");
        String[] goodsImages = mainJsonObject.get("goods_image").getAsString().split(",");
        goodsId = goodsInfoJSONObject.get("goods_id").getAsString();
        shareUrl = BaseConstant.URL_GOODS_DETAILED + goodsId;
        // 是否是视频文件
        JsonElement videoInfo = mainJsonObject.get("video_path");
        if (videoInfo instanceof JsonNull) {
            mainVideoPlayer.setVisibility(View.GONE);
        } else {
            mainVideoPlayer.setUrl(videoInfo.getAsString());
            StandardVideoController controller = new StandardVideoController(this);
            mainVideoPlayer.setVideoController(controller);
            mainVideoPlayer.start();
        }
        //轮播图
        goodsImageArrayList.clear();
        Collections.addAll(goodsImageArrayList, goodsImages);
        shareImageUrl = goodsImageArrayList.get(0);
        mainBanner.setDatas(goodsImageArrayList);
        mainBanner.start();
        //商品信息
        nameTextView.setText(goodsInfoJSONObject.get("goods_name").getAsString());
        descTextView.setText(goodsInfoJSONObject.get("goods_jingle").getAsString());
        descTextView.setVisibility(TextUtils.isEmpty(goodsInfoJSONObject.get("goods_jingle").getAsString()) ? View.GONE : View.VISIBLE);
        goodsStorageString = goodsInfoJSONObject.get("goods_storage").getAsString();
        shareTitle = nameTextView.getText().toString();
        shareText = descTextView.getText().toString();
        moneyTextView.setText("￥");
        marketPriceTextView.setText("￥");
        mobileTextView.setVisibility(View.GONE);
        saleRelativeLayout.setVisibility(View.GONE);
        if (goodsInfoJSONObject.has("goods_sale_type") && !goodsInfoJSONObject.get("goods_sale_type").getAsString().equals("0")) {
            activityLinearLayout.setVisibility(View.VISIBLE);
            activityTitleTextView.setText(goodsInfoJSONObject.get("title").getAsString());
            switch (goodsInfoJSONObject.get("sale_type").getAsString()) {
                case "sole":
                    mobileTextView.setVisibility(View.VISIBLE);
                    temp = "手机专享价格￥" + goodsInfoJSONObject.get("sale_price").getAsString();
                    break;
                case "xianshi":
                    saleRelativeLayout.setVisibility(View.VISIBLE);
                    saleTypeTextView.setText("限时打折");
                    lowerLimitString = goodsInfoJSONObject.get("lower_limit").getAsString();
                    temp = "直降￥" + goodsInfoJSONObject.get("down_price").getAsString() + "，最低 " + lowerLimitString + " 件起";
                    saleTimeTextView.init("", Long.parseLong(goodsInfoJSONObject.get("xs_time").getAsString()), "距离结束：", "");
                    saleTimeTextView.start(0);
                    break;
                case "groupbuy":
                    upperLimitString = goodsInfoJSONObject.get("upper_limit").getAsString();
                    temp = "直降￥" + goodsInfoJSONObject.get("down_price").getAsString() + "，限购 " + upperLimitString + " 件";
                    break;
                case "robbuy":
                    saleRelativeLayout.setVisibility(View.VISIBLE);
                    saleTypeTextView.setText("限时抢购");
                    upperLimitString = goodsInfoJSONObject.get("upper_limit").getAsString();
                    temp = "限购 " + upperLimitString + " 件，" + goodsInfoJSONObject.get("remark").getAsString();
                    saleTimeTextView.init("", Long.parseLong(goodsInfoJSONObject.get("end_time").getAsString()), "距离结束：", "");
                    saleTimeTextView.start(0);
                    break;
            }
            activityDescTextView.setText(temp);
            moneyTextView.append(goodsInfoJSONObject.get("sale_price").getAsString());
            marketPriceTextView.append(goodsInfoJSONObject.get("goods_price").getAsString());
        } else {
            activityLinearLayout.setVisibility(View.GONE);
            moneyTextView.append(goodsInfoJSONObject.get("goods_price").getAsString());
            marketPriceTextView.append(goodsInfoJSONObject.get("goods_marketprice").getAsString());
        }
        marketPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        saleTextView.setText("销量：");
        saleTextView.append(goodsInfoJSONObject.get("goods_salenum").getAsString());

        //满送
        JsonElement mansongInfo = mainJsonObject.get("mansong_info");
        if (mansongInfo instanceof JsonNull) {
            manSongLinearLayout.setVisibility(View.GONE);
        } else {
            manSongLinearLayout.setVisibility(View.VISIBLE);
            JsonArray jsonArray = mansongInfo.getAsJsonObject().getAsJsonArray("rules");
            JsonObject manSongJsonObject = jsonArray.get(0).getAsJsonObject();
            temp = "单笔订单满￥" + manSongJsonObject.get("price").getAsString() + "，立减￥" + manSongJsonObject.get("discount").getAsString() + "，送礼品";
            manSongDescTextView.setText(temp);
            if (manSongJsonObject.has("goods_image_url")) {
                BaseImageLoader.getInstance().display(manSongJsonObject.get("goods_image_url").getAsString(), manSongGoodsImageView);
            }
        }
        //代金券
        if (mainJsonObject.has("voucher")) {
            voucherArrayList.clear();
            voucherTextView.setVisibility(View.VISIBLE);
            JsonArray voucher = mainJsonObject.get("voucher").getAsJsonArray();
            voucherArrayList.addAll(JsonUtils.jsonToList(voucher, VoucherGoodsBean.class));
            voucherAdapter.notifyDataSetChanged();
        } else {
            voucherTextView.setVisibility(View.GONE);
        }
        //SpecName
        JsonElement specName = goodsInfoJSONObject.get("spec_name");
        if (!(specName instanceof JsonNull)) {
            JsonObject jsonObject = specName.getAsJsonObject();
            for (Map.Entry<String, JsonElement> elementEntry : jsonObject.entrySet()) {
                HashMap<String, String> hashMap1 = new HashMap<>();
                String key = elementEntry.getKey();
                String value = jsonObject.get(key).getAsString();
                hashMap1.put("id", key);
                hashMap1.put("value", value);
                specNameArrayList.add(hashMap1);
            }
            for (int i = 0; i < specNameArrayList.size(); i++) {
                if (i < 2) {
                    chooseLineView[i].setVisibility(View.VISIBLE);
                    chooseValueRecyclerView[i].setVisibility(View.VISIBLE);
                    chooseValueTextView[i].setVisibility(View.VISIBLE);
                    chooseValueTextView[i].setText(specNameArrayList.get(i).get("value"));
                }
            }
        } else {
            specTextView[0].setText("默认");
            specTextView[0].setVisibility(View.VISIBLE);
        }
        //specValue
        JsonElement specValue = goodsInfoJSONObject.get("spec_value");
        if (!(specValue instanceof JsonNull)) {
            specValueArrayList = new ArrayList<>();
            JsonObject jsonObject = specValue.getAsJsonObject();
            if (specNameArrayList.size() != 0) {
                for (int i = 0; i < specNameArrayList.size(); i++) {
                    String id = specNameArrayList.get(i).get("id");
                    String value = specNameArrayList.get(i).get("value");
                    JsonObject object = jsonObject.getAsJsonObject(id);
                    for (Map.Entry<String, JsonElement> elementEntry : object.entrySet()) {
                        HashMap<String, String> hashMap1 = new HashMap<>();
                        String key = elementEntry.getKey();
                        hashMap1.put("value", object.get(key).getAsString());
                        hashMap1.put("parent_value", value);
                        hashMap1.put("parent_id", id);
                        hashMap1.put("id", key);
                        specValueArrayList.add(hashMap1);
                    }
                }
            }
        }
        // mainWebView
        String imagesUrlString = BaseConstant.URL_GOODS_BODY + goodsIdString;
        Log.e("DEBUG", imagesUrlString);

        mainWebView.loadUrl(imagesUrlString);

        //goodsSpec
        JsonElement goodsSpec = goodsInfoJSONObject.get("goods_spec");
        if (!(goodsSpec instanceof JsonNull)) {
            goodsSpecArrayList = new ArrayList<>();
            JsonObject jsonObject = goodsSpec.getAsJsonObject();
            for (Map.Entry<String, JsonElement> elementEntry : jsonObject.entrySet()) {
                HashMap<String, String> hashMap1 = new HashMap<>();
                String key = elementEntry.getKey();
                String value = jsonObject.get(key).getAsString();
                for (int i = 0; i < specValueArrayList.size(); i++) {
                    String id = specValueArrayList.get(i).get("id");
                    if (key.equals(id)) {
                        String parent_value = specValueArrayList.get(i).get("parent_value");
                        hashMap1.put("key", key);
                        hashMap1.put("value", value);
                        hashMap1.put("content", parent_value + "：" + value);
                    }
                }
                goodsSpecArrayList.add(hashMap1);
            }
            for (int i = 0; i < goodsSpecArrayList.size(); i++) {
                if (i < 2) {
                    specTextView[i].setVisibility(View.VISIBLE);
                    specTextView[i].setText(goodsSpecArrayList.get(i).get("content"));
                    specString[i] = goodsSpecArrayList.get(i).get("key");
                }
            }
        }
        //specList
        //noinspection unchecked
        ArrayList<HashMap<String, String>>[] specArrayList = new ArrayList[2];
        SpecListAdapter[] specAdapter = new SpecListAdapter[2];
        JsonElement specList = mainJsonObject.get("spec_list");
        specListArrayList = new ArrayList<>(JsonUtils.jsonToList(specList));
        for (int i = 0; i < specNameArrayList.size(); i++) {
            if (i < 2) {
                specArrayList[i] = new ArrayList<>();
                String value = specNameArrayList.get(i).get("value");
                for (int j = 0; j < specValueArrayList.size(); j++) {
                    if (value.equals(specValueArrayList.get(j).get("parent_value"))) {
                        HashMap<String, String> hashMap = new HashMap<>(specValueArrayList.get(j));
                        hashMap.put("default", "0");
                        for (int k = 0; k < goodsSpecArrayList.size(); k++) {
                            if (goodsSpecArrayList.get(k).get("value").equals(hashMap.get("value"))) {
                                hashMap.put("default", "1");
                                break;
                            }
                        }
                        specArrayList[i].add(hashMap);
                    }
                }
            }
        }
        isBackBoolean = true;
        if (specArrayList[0] != null) {
            for (int i = 0; i < specArrayList[0].size(); i++) {
                String id = specArrayList[0].get(i).get("id");
                if (id.equals(specString[0])) {
                    isBackBoolean = false;
                    break;
                }
            }
        }
        for (int i = 0; i < specArrayList.length; i++) {
            if (i < 2) {
                if (specArrayList[i] != null) {
                    final int positionInt = i;
                    specAdapter[i] = new SpecListAdapter(specArrayList[i]);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                    chooseValueRecyclerView[i].setLayoutManager(linearLayoutManager);
                    chooseValueRecyclerView[i].setAdapter(specAdapter[i]);
                    specAdapter[i].setOnItemClickListener((position, id, value) -> {
                        if (isBackBoolean) {
                            if (positionInt == 1) {
                                specString[positionInt - 1] = id;
                            } else {
                                specString[positionInt + 1] = id;
                            }
                        } else {
                            specString[positionInt] = id;
                        }
                        refreshSpecData();
                    });
                }
            }
        }
        //虚拟物品
        if (goodsInfoJSONObject.get("is_virtual").getAsString().equals("0")) {
            areaRelativeLayout.setVisibility(View.VISIBLE);
        } else {
            areaRelativeLayout.setVisibility(View.GONE);
        }
        //店铺信息
        JsonObject storeInfo = mainJsonObject.getAsJsonObject("store_info");
        storeId = storeInfo.get("store_id").getAsString();
        memberId = storeInfo.get("member_id").getAsString();

        temp = "由 “" + storeInfo.get("store_name").getAsString() + "” 销售和发货，并享受售后服务";
        storeNameTextView.setText(storeInfo.get("store_name").getAsString());
        //voucherStoreNameTextView.setText(storeNameTextView.getText().toString());
        storeOwnTextView.setText(storeInfo.get("is_own_shop").getAsString().equals("1") ? "自营店" : "");
        JsonObject storeCredit = storeInfo.getAsJsonObject("store_credit");
        JsonObject storeDesccredit = storeCredit.getAsJsonObject("store_desccredit");
        storeDescTextView.setText(storeDesccredit.get("credit").getAsString());
        storeDescPercentTextView.setText(storeInfo.get("is_own_shop").getAsString().equals("1") ? "平" : storeDesccredit.get("percent_text").getAsString());

        JsonObject servicecredit = storeCredit.getAsJsonObject("store_servicecredit");
        storeServiceTextView.setText(servicecredit.get("credit").getAsString());
        storeServicePercentTextView.setText(storeInfo.get("is_own_shop").getAsString().equals("1") ? "平" : storeDesccredit.get("percent_text").getAsString());

        JsonObject deliverycredit = storeCredit.getAsJsonObject("store_deliverycredit");
        storeDeliveryTextView.setText(deliverycredit.get("credit").getAsString());
        storeDeliveryPercentTextView.setText(storeInfo.get("is_own_shop").getAsString().equals("1") ? "平" : storeDesccredit.get("percent_text").getAsString());
        serviceDescTextView.setText(temp);
        //服务信息
        if (goodsInfoJSONObject.has("contractlist") && !(goodsInfoJSONObject.get("contractlist") instanceof JsonNull)) {
            serviceSevDayTextView.setVisibility(View.VISIBLE);
            serviceQualityTextView.setVisibility(View.VISIBLE);
            serviceReissueTextView.setVisibility(View.VISIBLE);
            serviceLogisticsTextView.setVisibility(View.VISIBLE);
        } else {
            serviceSevDayTextView.setVisibility(View.GONE);
            serviceQualityTextView.setVisibility(View.GONE);
            serviceReissueTextView.setVisibility(View.GONE);
            serviceLogisticsTextView.setVisibility(View.GONE);
        }
        //评价信息
        JsonObject goodsEvaluateInfo = mainJsonObject.getAsJsonObject("goods_evaluate_info");
        evaluateDescTextView.setText("好评率 ");
        evaluateDescTextView.append(goodsEvaluateInfo.get("good_percent").getAsString() + "%");
        evaluateNumberTextView.setText("(");
        evaluateNumberTextView.append(goodsEvaluateInfo.get("all").getAsString() + "人评价)");

        JsonElement goodsEvalList = mainJsonObject.get("goods_eval_list");
        if ((goodsEvalList instanceof JsonNull)) {
            evaluateRecyclerView.setVisibility(View.GONE);
        } else {
            evaluateRecyclerView.setVisibility(View.VISIBLE);
            evaluateGoodsArrayList.clear();
            evaluateGoodsArrayList.addAll(JsonUtils.jsonToList(goodsEvalList.getAsJsonArray(), EvaluateGoodsBean.class));
            evaluateGoodsAdapter.notifyDataSetChanged();
        }
        //商品推荐
        commendArrayList.clear();
        commendArrayList.addAll(JsonUtils.jsonToList(mainJsonObject.getAsJsonArray("goods_commend_list"), GoodsCommendBean.class));
        commendAdapter.notifyDataSetChanged();
        //选择页面
        BaseImageLoader.getInstance().display(goodsImageArrayList.get(0), chooseGoodsImageView);
        chooseNameTextView.setText(nameTextView.getText().toString());
        choosePriceTextView.setText(moneyTextView.getText().toString());
        chooseStorageTextView.setText("库存：");
        chooseStorageTextView.append(goodsStorageString);
        if (!TextUtils.isEmpty(lowerLimitString)) {
            chooseNumberEditText.setText(lowerLimitString);
        }
        chooseNumberEditText.setSelection(chooseNumberEditText.getText().length());
//        haveGoods = !goodsStorageString.equals("0");
    }

    private void refreshSpecData() {
        for (int i = 0; i < specListArrayList.size(); i++) {
            String key = specListArrayList.get(i).get("key");
            if (Objects.requireNonNull(key).contains(specString[0]) && key.contains(specString[1])) {
                goodsId = specListArrayList.get(i).get("value");
                break;
            }
        }
        BaseBusClient.getDefault().post(new GoodsIdEvent(goodsId));
    }

    @Override
    public void showGoodsDetailData(String homeInfoData) {
        handlerData(homeInfoData);
    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void onStart() {
        super.onStart();
        mainBanner.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainBanner.stop();
    }

    public void onPause() {
        super.onPause();
        this.mainVideoPlayer.pause();
    }

    public void onResume() {
        super.onResume();
        this.mainVideoPlayer.resume();
    }

    public void onDestroy() {
        super.onDestroy();
        //this.mainVideoPlayer.release();
    }

    public void onBackPressed() {
        if (!this.mainVideoPlayer.onBackPressed()) {
            super.onBackPressed();
        }
    }


}