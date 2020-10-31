package org.cloud.panzer.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseBusClient;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseFragmentAdapter;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.panzer.R;
import org.cloud.panzer.event.GoodsBeanEvent;
import org.cloud.panzer.fragment.GoodsFragment;
import org.cloud.panzer.mvp.contract.GoodsContract;
import org.cloud.panzer.mvp.presenter.GoodsPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsActivity extends BaseMvpActivity<GoodsPresenter> implements GoodsContract.View {

    private AppCompatTextView[] navigationTextView;

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.mainViewPager)
    ViewPager2 mainViewPager;
    @BindView(R.id.goodsTextView)
    AppCompatTextView goodsTextView;
    @BindView(R.id.detailedTextView)
    AppCompatTextView detailedTextView;
    @BindView(R.id.evaluateTextView)
    AppCompatTextView evaluateTextView;

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
        navigationTextView = new AppCompatTextView[]{goodsTextView, detailedTextView, evaluateTextView};
    }

    @Override
    protected void initData() {
        goodsIdString = getIntent().getStringExtra(BaseConstant.DATA_ID);
        if (TextUtils.isEmpty(goodsIdString)) {
            BaseToast.getInstance().showDataError();
            BaseApplication.getInstance().finish(getActivity());
        }
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new GoodsFragment());
        //fragmentList.add(new DetailedFragment());
        //fragmentList.add(new EvaluateFragment());

        mainViewPager.setAdapter(new BaseFragmentAdapter(this, fragmentList));
        mainViewPager.setOffscreenPageLimit(navigationTextView.length);
        isShowBoolean = false;
        setToolbar(mainToolbar, "");
        updateNavigation(0);
        mPresenter.requestGoodsData(goodsIdString);
    }

    @Override
    protected void initListener() {
        for (int i = 0; i < navigationTextView.length; i++) {
            final int position = i;
            navigationTextView[i].setOnClickListener(view -> updateNavigation(position));
        }

        mainViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                updateNavigation(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

    private void updateNavigation(int position) {
        for (AppCompatTextView appCompatTextView : navigationTextView) {
            appCompatTextView.setTextColor(BaseApplication.getInstance().getColors(R.color.grey));
        }
        navigationTextView[position].setTextColor(BaseApplication.getInstance().getColors(R.color.primary));
        mainViewPager.setCurrentItem(position);
    }

    @Override
    public void onReturn() {
        if (mainViewPager.getCurrentItem() != 0) {
            mainViewPager.setCurrentItem(0);
            return;
        }
//        if (isShowBoolean) {
//            BaseBusClient.get().post(new GoodsGoneEvent(true));
//            return;
//        }
        if (BaseApplication.getInstance().inActivityStackTop() && !BaseApplication.getInstance().inActivityStack(MainActivity.class)) {
            BaseApplication.getInstance().start(getActivity(), MainActivity.class);
        }
        super.onReturn();
    }

    @Override
    public void showGoodsDetailData(String homeInfoData) {
        BaseBusClient.getInstance().getDefault().post(new GoodsBeanEvent(homeInfoData));
    }

    @Override
    public void showError(String msg) {

    }

}