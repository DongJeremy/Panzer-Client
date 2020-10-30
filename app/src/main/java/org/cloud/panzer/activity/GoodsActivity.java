package org.cloud.panzer.activity;

import android.content.Intent;
import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import org.cloud.core.base.BaseActivity;
import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseFragmentAdapter;
import org.cloud.core.base.BaseToast;
import org.cloud.core.rx.RxBus;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.TestEvent;
import org.cloud.panzer.fragment.GoodsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class GoodsActivity extends BaseActivity {

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
    protected void initView() {
        navigationTextView = new AppCompatTextView[]{goodsTextView, detailedTextView, evaluateTextView};
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
        getGoodsInfo();

        RxBus.getDefault().post(new TestEvent(1,"RxBus post test data from homeFragment"));
    }

    private void getGoodsInfo() {
//        GoodsModel.get().goodsDetailed(goodsIdString, new BaseHttpListener() {
//            @Override
//            public void onSuccess(BaseBean baseBean) {
//                BaseBusClient.get().post(new GoodsBeanEvent(baseBean));
//            }
//            @Override
//            public void onFailure(String reason) {
//                if (reason.equals("商品不存在")) {
//                    BaseToast.get().show(reason);
//                    BaseApplication.get().finish(getActivity());
//                } else {
//                    BaseToast.get().show(reason);
//                }
//            }
//        });
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
}