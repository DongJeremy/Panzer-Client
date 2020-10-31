package org.cloud.panzer.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseFragment;
import org.cloud.core.base.BaseImageLoader;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.EvaluateGoodsSimpleListAdapter;
import org.cloud.panzer.adapter.GoodsCommendListAdapter;
import org.cloud.panzer.adapter.VoucherGoodsListAdapter;
import org.cloud.panzer.bean.EvaluateGoodsBean;
import org.cloud.panzer.bean.GoodsCommendBean;
import org.cloud.panzer.bean.VoucherGoodsBean;
import org.cloud.panzer.event.GoodsBeanEvent;
import org.cloud.panzer.mvp.presenter.GoodsPresenter;
import org.cloud.panzer.view.CenterTextView;
import org.cloud.panzer.view.CountdownTextView;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoodsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoodsFragment extends BaseFragment {

    @BindView(R.id.headerRelativeLayout)
    RelativeLayout headerRelativeLayout;
    @BindView(R.id.mainBanner)
    @SuppressWarnings("rawtypes")
    Banner mainBanner;
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
    @BindView(R.id.cartTextView)
    CenterTextView cartTextView;
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
    @BindView(R.id.chooseMoneyTextView)
    AppCompatTextView chooseMoneyTextView;
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
    //@BindView(R.id.chooseValueOneRecyclerView)
    //RecyclerView chooseValueOneRecyclerView;
    //@BindView(R.id.chooseValueTwoRecyclerView)
    //RecyclerView chooseValueTwoRecyclerView;
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
    @BindView(R.id.voucherStoreNameTextView)
    AppCompatTextView voucherStoreNameTextView;
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

    private String shareUrl;
    private String shareText;
    private String shareTitle;
    private String shareImageUrl = "";
    private String goodsId = "";

    private String goodsStorageString = "";
    private String lowerLimitString = "";
    private String upperLimitString = "";

    public static GoodsFragment newInstance(String param1, String param2) {
        GoodsFragment fragment = new GoodsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods_goods;
    }

    @Override
    protected void initView() {

        mainBanner.setAdapter(new BannerImageAdapter<String>(goodsImageArrayList) {

            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                BaseImageLoader.getInstance().display(data, holder.imageView);
            }
        });
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(BaseApplication.getInstance().getWidth(), BaseApplication.getInstance().getWidth());
        headerRelativeLayout.setLayoutParams(layoutParams);

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
        //chooseValueRecyclerView[0] = chooseValueOneRecyclerView;
        //chooseValueRecyclerView[1] = chooseValueTwoRecyclerView;

        //chooseValueRecyclerView[0].setVisibility(View.GONE);
        //chooseValueRecyclerView[1].setVisibility(View.GONE);
    }

    @Override
    protected void initListener() {

    }

    @Subscribe
    public void onGoodsBeanEvent(GoodsBeanEvent event) {

        String jsonString = event.getGoodsBean();
        initData();
        initListener();
        handlerData(jsonString);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected boolean useEventBus() {
        return true;
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

    private void handlerData(String homeInfoData) {
        String temp = "";
        JsonObject rootJsonObject = new JsonParser().parse(homeInfoData).getAsJsonObject();
        int code = rootJsonObject.get("code").getAsInt();
        if (code != 200) {
            return;
        }
        JsonObject mainJsonObject = rootJsonObject.getAsJsonObject("datas");
        JsonObject goodsInfoJSONObject = mainJsonObject.getAsJsonObject("goods_content");
        String[] goodsImages = mainJsonObject.get("goods_image").getAsString().split(",");
        goodsId = goodsInfoJSONObject.get("goods_id").getAsString();
        shareUrl = BaseConstant.URL_GOODS_DETAILED + goodsId;

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
    }
}