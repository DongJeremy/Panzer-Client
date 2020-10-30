package org.cloud.panzer.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.Banner;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseFragment;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.EvaluateGoodsSimpleListAdapter;
import org.cloud.panzer.adapter.GoodsCommendListAdapter;
import org.cloud.panzer.adapter.VoucherGoodsListAdapter;
import org.cloud.panzer.bean.EvaluateGoodsBean;
import org.cloud.panzer.bean.GoodsCommendBean;
import org.cloud.panzer.bean.VoucherGoodsBean;
import org.cloud.panzer.mvp.contract.GoodsContract;
import org.cloud.panzer.mvp.model.HomeInfoModel;
import org.cloud.panzer.mvp.presenter.GoodsPresenter;
import org.cloud.panzer.view.CenterTextView;
import org.cloud.panzer.view.CountdownTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoodsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoodsFragment extends BaseFragment<GoodsPresenter> implements GoodsContract.View {

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
    protected GoodsPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
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

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void showHomeInfoData(HomeInfoModel.HomeInfo.Data homeInfoData) {

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
}