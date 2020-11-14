package org.cloud.panzer.ui.main;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseMvpFragment;
import org.cloud.core.rx.RxBus;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.HomeListAdapter;
import org.cloud.panzer.bean.ArticleBean;
import org.cloud.panzer.bean.HomeBean;
import org.cloud.panzer.mvp.contract.HomeContract;
import org.cloud.panzer.mvp.presenter.HomePresenter;
import org.cloud.panzer.ui.home.ChatListActivity;

import java.util.ArrayList;

import butterknife.BindView;

import static org.cloud.panzer.event.RxBusCode.RX_BUS_CODE_MAIN_SEARCH_SHOW;

public class HomeFragment extends BaseMvpFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.mainRecyclerView)
    RecyclerView mainRecyclerView;
    @BindView(R.id.mainSwipeRefreshLayout)
    SwipeRefreshLayout mainSwipeRefreshLayout;
    @BindView(R.id.messageDotTextView)
    AppCompatTextView messageDotTextView;
    @BindView(R.id.messageImageView)
    AppCompatImageView messageImageView;
    @BindView(R.id.photoImageView)
    AppCompatImageView photoImageView;
    @BindView(R.id.scanImageView)
    AppCompatImageView scanImageView;
    @BindView(R.id.searchEditText)
    AppCompatEditText searchEditText;

    public ArrayList<ArticleBean> articleArrayList;
    public HomeListAdapter mainAdapter;
    public ArrayList<HomeBean> mainArrayList;

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
        this.mainArrayList = new ArrayList<>();
        this.articleArrayList = new ArrayList<>();
        this.mainAdapter = new HomeListAdapter(getActivity(), this.mainArrayList, true);
        App.getInstance().setRecyclerView(getActivity(), mainRecyclerView, mainAdapter);
        App.getInstance().setSwipeRefreshLayout(mainSwipeRefreshLayout);
    }

    @Override
    protected void initListener() {
        scanImageView.setOnClickListener(v -> App.getInstance().startCapture(getActivity()));
        messageImageView.setOnClickListener(view -> App.getInstance().startCheckLogin(getActivity(), ChatListActivity.class));
        searchEditText.setOnClickListener(v-> RxBus.getInstance().send(RX_BUS_CODE_MAIN_SEARCH_SHOW));
        photoImageView.setOnClickListener(v-> App.getInstance().startImagePicker(getActivity(), 1, BaseConstant.CODE_ALBUM, true));
    }

    @Override
    protected void initData() {
        mPresenter.requestHomeInfoData();
        mPresenter.requestArticleListData();
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void showHomeInfoData(BaseBean baseBean) {
        JsonArray jsonArrays = JsonUtils.parseJsonToJsonArray(baseBean.getDatas());
        for (int i = 0; i < jsonArrays.size(); i++) {
            JsonObject jsonObject = jsonArrays.get(i).getAsJsonObject();
            HomeBean homeBean = new HomeBean();
            if (jsonObject.has("adv_list")) {
                homeBean.setType("adv_list");
                homeBean.setAdvListBean(JsonUtils.jsonToBean(jsonObject.get("adv_list"), HomeBean.AdvListBean.class));
                this.mainArrayList.add(homeBean);
            }
            if (jsonObject.has("home_nav")) {
                homeBean.setType("home_nav");
                homeBean.setHomeNavBean(JsonUtils.jsonToBean(jsonObject.get("home_nav"), HomeBean.HomeNavBean.class));
                this.mainArrayList.add(homeBean);
            }
            if (jsonObject.has("home1")) {
                homeBean.setType("home1");
                homeBean.setHome1Bean(JsonUtils.jsonToBean(jsonObject.get("home1"), HomeBean.Home1Bean.class));
                this.mainArrayList.add(homeBean);
            }
            if (jsonObject.has("goods")) {
                homeBean.setType("goods");
                homeBean.setGoodsBean(JsonUtils.jsonToBean(jsonObject.get("goods"), HomeBean.GoodsBean.class));
                this.mainArrayList.add(homeBean);
            }
        }
        this.mainAdapter.notifyDataSetChanged();
    }

    @Override
    public void showArticleListData(BaseBean baseBean) {
        JsonObject mainJsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        JsonArray jsonArrays = mainJsonObject.getAsJsonArray("article_list");
        this.articleArrayList.addAll(JsonUtils.jsonToList(jsonArrays, ArticleBean.class));
        if (articleArrayList.size() != 0) {
            HomeBean homeBean = new HomeBean();
            homeBean.setType("article");
            homeBean.setArticleList(this.articleArrayList);
            if (mainArrayList.size() == 0) {
                this.mainArrayList.add(homeBean);
            } else {
                this.mainArrayList.add(2, homeBean);
            }
        }
        this.mainAdapter.notifyDataSetChanged();
    }
}