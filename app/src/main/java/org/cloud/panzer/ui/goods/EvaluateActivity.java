package org.cloud.panzer.ui.goods;

import android.text.TextUtils;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.JsonObject;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.Utils;
import org.cloud.core.widget.PullRefreshView;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.EvaluateGoodsListAdapter;
import org.cloud.panzer.bean.EvaluateGoodsBean;
import org.cloud.panzer.mvp.contract.GoodsEvaluateContract;
import org.cloud.panzer.mvp.presenter.GoodsEvaluatePresenter;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;

public class EvaluateActivity extends BaseMvpActivity<GoodsEvaluatePresenter> implements GoodsEvaluateContract.View {

    @BindView(R.id.mainPullRefreshView)
    PullRefreshView mainPullRefreshView;
    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.allTextView)
    AppCompatTextView allTextView;
    @BindView(R.id.goodTextView)
    AppCompatTextView goodTextView;
    @BindView(R.id.inTextView)
    AppCompatTextView inTextView;
    @BindView(R.id.badTextView)
    AppCompatTextView badTextView;
    @BindView(R.id.imageTextView)
    AppCompatTextView imageTextView;
    @BindView(R.id.additionTextView)
    AppCompatTextView additionTextView;

    private AppCompatTextView[] navigationTextView;
    private EvaluateGoodsListAdapter mainAdapter;
    private ArrayList<EvaluateGoodsBean> mainArrayList;

    private String goodsId;
    private int page;
    private String type;

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_evaluate;
    }

    @Override
    protected void initView() {
        this.goodsId = getIntent().getStringExtra(BaseConstant.DATA_ID);
        Log.e("TAG", goodsId);
        if (TextUtils.isEmpty(this.goodsId)) {
            BaseToast.getInstance().showDataError();
            App.getInstance().finish(getActivity());
            return;
        }
        this.navigationTextView = new AppCompatTextView[]{
                this.allTextView, this.goodTextView, this.inTextView, this.badTextView, this.imageTextView, this.additionTextView
        };
        setToolbar(this.mainToolbar, "商品评价", R.color.whiteSub);
        type = "";
        page = 1;
        this.mainArrayList = new ArrayList<>();
        this.mainAdapter = new EvaluateGoodsListAdapter(this.mainArrayList);
        this.mainPullRefreshView.getRecyclerView().setAdapter(this.mainAdapter);
    }

    @Override
    protected void initListener() {
        this.navigationTextView[0].setOnClickListener(v -> updateNavigation(0));
        this.navigationTextView[1].setOnClickListener(v -> updateNavigation(1));
        this.navigationTextView[2].setOnClickListener(v -> updateNavigation(2));
        this.navigationTextView[3].setOnClickListener(v -> updateNavigation(3));
        this.navigationTextView[4].setOnClickListener(v -> updateNavigation(4));
        this.navigationTextView[5].setOnClickListener(v -> updateNavigation(5));
    }

    @Override
    protected void initData() {
        updateNavigation(0);
    }

    @Override
    protected GoodsEvaluatePresenter createPresenter() {
        return new GoodsEvaluatePresenter();
    }

    private void updateNavigation(int i) {
        for (AppCompatTextView appCompatTextView : this.navigationTextView) {
            appCompatTextView.setTextColor(Utils.getColors(R.color.greyAdd));
            appCompatTextView.setBackgroundResource(R.drawable.border_evaluate);
        }
        this.navigationTextView[i].setTextColor(Utils.getColors(R.color.white));
        this.navigationTextView[i].setBackgroundResource(R.drawable.border_evaluate_press);
        this.page = 1;
        String str = "";
        if (i != 0) {
            str = i + str;
        }
        this.type = str;
        getEvaluate();
    }

    @Override
    public void showGoodsEvaluateSuccess(BaseBean baseBean) {
        if (this.page == 1) {
            this.mainArrayList.clear();
        }
        if (this.page <= baseBean.getPageTotal()) {
            JsonObject jsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
            this.mainArrayList.addAll(
                    Objects.requireNonNull(JsonUtils.jsonToList(jsonObject.getAsJsonArray("goods_eval_list"), EvaluateGoodsBean.class)));
            page++;
        }
        this.mainPullRefreshView.setComplete();
    }

    @Override
    public void showGoodsEvaluateFail(String reason) {
        if (this.mainArrayList.size() != 0) {
            BaseToast.getInstance().show(reason);
        } else {
            this.mainPullRefreshView.setFailure();
        }
    }

    private void getEvaluate() {
        this.mainPullRefreshView.setLoading();
        mPresenter.requestGoodsEvaluateData(this.goodsId, this.type, this.page + "");
    }
}