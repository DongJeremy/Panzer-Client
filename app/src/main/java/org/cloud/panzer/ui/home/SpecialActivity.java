package org.cloud.panzer.ui.home;

import android.content.DialogInterface;
import android.os.Handler;
import android.text.TextUtils;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseDialog;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.HomeListAdapter;
import org.cloud.panzer.bean.HomeBean;
import org.cloud.panzer.mvp.contract.SpecialContract;
import org.cloud.panzer.mvp.presenter.SpecialPresenter;

import java.util.ArrayList;

import butterknife.BindView;

public class SpecialActivity extends BaseMvpActivity<SpecialPresenter> implements SpecialContract.View {

    @BindView(R.id.mainRecyclerView)
    RecyclerView mainRecyclerView;
    @BindView(R.id.mainSwipeRefreshLayout)
    SwipeRefreshLayout mainSwipeRefreshLayout;
    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;

    private String specialId;
    private HomeListAdapter mainAdapter;
    private ArrayList<HomeBean> mainArrayList;

    @Override
    protected SpecialPresenter createPresenter() {
        return new SpecialPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_special;
    }

    @Override
    protected void initView() {
        setToolbar(this.mainToolbar, "加载中...", R.color.whiteSub);
        this.specialId = getIntent().getStringExtra(BaseConstant.DATA_ID);
        if (TextUtils.isEmpty(this.specialId)) {
            BaseToast.getInstance().showDataError();
            App.getInstance().finish(getActivity());
        }
        this.mainArrayList = new ArrayList<>();
        this.mainAdapter = new HomeListAdapter(getActivity(), this.mainArrayList, false);
        App.getInstance().setRecyclerView(getActivity(), this.mainRecyclerView, this.mainAdapter);
        App.getInstance().setSwipeRefreshLayout(this.mainSwipeRefreshLayout);
    }

    @Override
    protected void initListener() {
        this.mainSwipeRefreshLayout.setOnRefreshListener(() -> {
            new Handler().postDelayed(new Runnable() {
                public final void run() {
                    mainSwipeRefreshLayout.setRefreshing(false);
                    getData();
                }
            }, 2000);
        });

    }

    @Override
    protected void initData() {
        getData();
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    private void getData() {
        if (this.mainArrayList.size() == 0) {
            BaseDialog.getInstance().progress(getActivity());
        }
        mPresenter.requestSpecialData(this.specialId);
    }

    @Override
    public void showSpecialSuccess(BaseBean baseBean) {
        if (SpecialActivity.this.mainArrayList.size() == 0) {
            BaseDialog.getInstance().cancel();
        }
        this.mainArrayList.clear();
        JsonObject jsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        JsonArray list = jsonObject.getAsJsonArray("list");
        this.setToolbar(this.mainToolbar, jsonObject.get("special_desc").getAsString());
        if (list.size() == 0) {
            BaseDialog.getInstance().query(this.getActivity(), "数据出错啦~", "此专题无任何数据...",
                    (dialog, which) -> App.getInstance().finish(this.getActivity()),
                    (dialog, which) -> App.getInstance().finish(this.getActivity()));
            return;
        } else {
            for (int i = 0; i < list.size(); i++) {
                JsonObject object = list.get(i).getAsJsonObject();
                if (object.has("adv_list")) {
                    HomeBean homeBean = new HomeBean();
                    homeBean.setType("adv_list");
                    homeBean.setAdvListBean(JsonUtils.jsonToBean(object.get("adv_list"), HomeBean.AdvListBean.class));
                    this.mainArrayList.add(homeBean);
                }
                if (object.has("home1")) {
                    HomeBean homeBean = new HomeBean();
                    homeBean.setType("home1");
                    homeBean.setHome1Bean(JsonUtils.jsonToBean(object.get("home1"), HomeBean.Home1Bean.class));
                    this.mainArrayList.add(homeBean);
                }
                if (object.has("home2")) {
                    HomeBean homeBean = new HomeBean();
                    homeBean.setType("home2");
                    homeBean.setHome2Bean(JsonUtils.jsonToBean(object.get("home2"), HomeBean.Home2Bean.class));
                    this.mainArrayList.add(homeBean);
                }
                if (object.has("home3")) {
                    HomeBean homeBean = new HomeBean();
                    homeBean.setType("home3");
                    homeBean.setHome3Bean(JsonUtils.jsonToBean(object.get("home3"), HomeBean.Home3Bean.class));
                    this.mainArrayList.add(homeBean);
                }
                if (object.has("home4")) {
                    HomeBean homeBean = new HomeBean();
                    homeBean.setType("home4");
                    homeBean.setHome4Bean(JsonUtils.jsonToBean(object.get("home4"), HomeBean.Home4Bean.class));
                    this.mainArrayList.add(homeBean);
                }
                if (object.has("goods")) {
                    HomeBean homeBean = new HomeBean();
                    homeBean.setType("goods");
                    homeBean.setGoodsBean(JsonUtils.jsonToBean(object.get("goods"), HomeBean.GoodsBean.class));
                    this.mainArrayList.add(homeBean);
                }
                if (object.has("goods1")) {
                    HomeBean homeBean = new HomeBean();
                    homeBean.setType("goods1");
                    homeBean.setGoods1Bean(JsonUtils.jsonToBean(object.get("goods1"), HomeBean.Goods1Bean.class));
                    this.mainArrayList.add(homeBean);
                }
                if (object.has("goods2")) {
                    HomeBean homeBean = new HomeBean();
                    homeBean.setType("goods2");
                    homeBean.setGoods2Bean(JsonUtils.jsonToBean(object.get("goods"), HomeBean.Goods2Bean.class));
                    this.mainArrayList.add(homeBean);
                }
            }
            this.mainAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showSpecialFail(String msg) {
        if (this.mainArrayList.size() == 0) {
            BaseDialog.getInstance().cancel();
        }
        BaseDialog.getInstance().queryLoadingFailure(getActivity(), msg,
                (dialogInterface, i) -> getData(),
                (dialogInterface, i) -> App.getInstance().finish(getActivity()));
    }
}