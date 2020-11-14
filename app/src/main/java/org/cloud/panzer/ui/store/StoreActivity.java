package org.cloud.panzer.ui.store;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.JsonObject;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseDialog;
import org.cloud.core.base.BaseFragmentAdapter;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.rx.RxBus;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.Utils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.VoucherStoreListAdapter;
import org.cloud.panzer.bean.StoreInfoBean;
import org.cloud.panzer.bean.VoucherStoreBean;
import org.cloud.panzer.event.StoreBeanEvent;
import org.cloud.panzer.mvp.contract.StoreContract;
import org.cloud.panzer.mvp.presenter.StorePresenter;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;

public class StoreActivity extends BaseMvpActivity<StorePresenter> implements StoreContract.View {

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.searchEditText)
    AppCompatEditText searchEditText;
    @BindView(R.id.toolbarImageView)
    AppCompatImageView toolbarImageView;
    @BindView(R.id.cateImageView)
    AppCompatImageView cateImageView;
    @BindView(R.id.homeTextView)
    AppCompatTextView homeTextView;
    @BindView(R.id.goodsTextView)
    AppCompatTextView goodsTextView;
    @BindView(R.id.newTextView)
    AppCompatTextView newTextView;
    @BindView(R.id.activityTextView)
    AppCompatTextView activityTextView;
    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;
    @BindView(R.id.detailedTextView)
    AppCompatTextView detailedTextView;
    @BindView(R.id.voucherTextView)
    AppCompatTextView voucherTextView;
    @BindView(R.id.customerTextView)
    AppCompatTextView customerTextView;
    @BindView(R.id.voucherLinearLayout)
    LinearLayoutCompat voucherLinearLayout;
    @BindView(R.id.voucherRecyclerView)
    RecyclerView voucherRecyclerView;
    @BindView(R.id.nightTextView)
    AppCompatTextView nightTextView;

    private String storeIdString;
    private AppCompatTextView[] navigationTextView;
    private Drawable[] navigationNormalDrawable;
    private Drawable[] navigationPressDrawable;
    public StoreInfoBean storeInfoBean;

    public ArrayList<VoucherStoreBean> voucherArrayList;
    public VoucherStoreListAdapter voucherAdapter;

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_store;
    }

    @Override
    protected void initView() {
        this.storeIdString = getIntent().getStringExtra(BaseConstant.DATA_ID);
        if (TextUtils.isEmpty(this.storeIdString)) {
            BaseToast.getInstance().showDataError();
            App.getInstance().finish(getActivity());
        }
        this.navigationTextView = new AppCompatTextView[]{
                homeTextView, goodsTextView, newTextView, activityTextView
        };
        this.navigationNormalDrawable = new Drawable[]{
                Utils.getMipmap(R.mipmap.ic_navigation_store_home, R.color.grey),
                Utils.getMipmap(R.mipmap.ic_navigation_store_goods, R.color.grey),
                Utils.getMipmap(R.mipmap.ic_navigation_store_new, R.color.grey),
                Utils.getMipmap(R.mipmap.ic_navigation_store_activity, R.color.grey)
        };
        this.navigationPressDrawable = new Drawable[]{
                Utils.getMipmap(R.mipmap.ic_navigation_store_home_press),
                Utils.getMipmap(R.mipmap.ic_navigation_store_goods_press),
                Utils.getMipmap(R.mipmap.ic_navigation_store_new_press),
                Utils.getMipmap(R.mipmap.ic_navigation_store_activity_press)
        };
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new StoreHomeFragment());
//        arrayList.add(new StoreGoodsFragment());
//        arrayList.add(new StoreNewFragment());
//        arrayList.add(new StoreActivityFragment());
        this.mainViewPager.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), arrayList));
        this.mainViewPager.setOffscreenPageLimit(this.navigationTextView.length);
        setToolbar(this.mainToolbar);
        this.toolbarImageView.setImageResource(R.drawable.ic_action_search);
        this.storeInfoBean = new StoreInfoBean();
        this.voucherArrayList = new ArrayList<>();
        this.voucherAdapter = new VoucherStoreListAdapter(this.voucherArrayList);
        App.getInstance().setRecyclerView(getActivity(), this.voucherRecyclerView, this.voucherAdapter);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        updateNavigation(0);
        storeInfo();
    }

    @Override
    protected StorePresenter createPresenter() {
        return new StorePresenter();
    }

    @Override
    public void showStoreInfoSuccess(BaseBean baseBean) {
        JsonObject jsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        this.storeInfoBean = JsonUtils.jsonToBean(jsonObject.get("store_info"), StoreInfoBean.class);
        RxBus.getInstance().send(new StoreBeanEvent(baseBean));
        this.getVoucher();
    }

    @Override
    public void showStoreInfoFail(String reason) {
        BaseDialog.getInstance().query(StoreActivity.this.getActivity(), "获取数据失败", reason,
                (dialogInterface, i) -> StoreActivity.this.storeInfo(),
                (dialogInterface, i) -> App.getInstance().finish(StoreActivity.this.getActivity())
        );
    }

    @Override
    public void showVoucherTplListSuccess(BaseBean baseBean) {
        StoreActivity.this.voucherArrayList.clear();
        JsonObject jsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        StoreActivity.this.voucherArrayList.addAll(Objects.requireNonNull(JsonUtils.jsonToList(jsonObject.getAsJsonArray("voucher_list"),
                VoucherStoreBean.class)));
        StoreActivity.this.voucherAdapter.notifyDataSetChanged();
    }

    @Override
    public void showVoucherTplListFail(String reason) {
        BaseDialog.getInstance().query(StoreActivity.this.getActivity(), "获取数据失败", reason,
                (dialogInterface, i) -> StoreActivity.this.getVoucher(),
                (dialogInterface, i) -> App.getInstance().finish(StoreActivity.this.getActivity())
        );
    }

    private void storeInfo() {
        mPresenter.requestStoreInfoData(this.storeIdString);
    }

    public void getVoucher() {
        mPresenter.requestVoucherTplListData(this.storeIdString);
    }

    private void updateNavigation(int position) {
        int i = 0;
        while (true) {
            AppCompatTextView[] appCompatTextViewArr = this.navigationTextView;
            if (i < appCompatTextViewArr.length) {
                appCompatTextViewArr[i].setTextColor(Utils.getColors(R.color.grey));
                this.navigationTextView[i].setCompoundDrawablesWithIntrinsicBounds(null, this.navigationNormalDrawable[i], null, null);
                i++;
            } else {
                appCompatTextViewArr[position].setTextColor(Utils.getColors(R.color.primary));
                this.navigationTextView[position].setCompoundDrawablesWithIntrinsicBounds(null, this.navigationPressDrawable[position], null, null);
                this.mainViewPager.setCurrentItem(position);
                return;
            }
        }
    }
}