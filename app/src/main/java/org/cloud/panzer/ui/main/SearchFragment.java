package org.cloud.panzer.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseMvpFragment;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.SearchHistoryListAdapter;
import org.cloud.panzer.adapter.SearchKeyListAdapter;
import org.cloud.panzer.bean.GoodsSearchData;
import org.cloud.panzer.mvp.contract.SearchContract;
import org.cloud.panzer.mvp.presenter.SearchPresenter;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;

public class SearchFragment extends BaseMvpFragment<SearchPresenter> implements SearchContract.View {

    @BindView(R.id.dengshiTextView)
    AppCompatTextView dengshiTextView;
    @BindView(R.id.fuwuTextView)
    AppCompatTextView fuwuTextView;
    @BindView(R.id.helpTextView)
    AppCompatTextView helpTextView;
    @BindView(R.id.historyRecyclerView)
    RecyclerView historyRecyclerView;
    @BindView(R.id.keyRecyclerView)
    RecyclerView keyRecyclerView;
    @BindView(R.id.peijianTextView)
    AppCompatTextView peijianTextView;
    @BindView(R.id.photoImageView)
    AppCompatImageView photoImageView;
    @BindView(R.id.quanziTextView)
    AppCompatTextView quanziTextView;
    @BindView(R.id.scanImageView)
    AppCompatImageView scanImageView;
    @BindView(R.id.searchEditText)
    AppCompatEditText searchEditText;
    @BindView(R.id.searchImageView)
    AppCompatImageView searchImageView;
    @BindView(R.id.storeTextView)
    AppCompatTextView storeTextView;

    private String gcId;
    private int position;

    private AppCompatTextView[] conditionTextView;

    private SearchHistoryListAdapter historyAdapter;
    private ArrayList<String> historyArrayList;
    private SearchKeyListAdapter keyAdapter;
    private ArrayList<String> keyArrayList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_search;
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void initView() {
        this.keyArrayList = new ArrayList<>();
        this.keyAdapter = new SearchKeyListAdapter(this.keyArrayList);
        App.getInstance().setRecyclerView(getActivity(), this.keyRecyclerView, this.keyAdapter);
        this.keyRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        this.historyArrayList = new ArrayList<>();
        this.historyAdapter = new SearchHistoryListAdapter(this.historyArrayList);
        App.getInstance().setRecyclerView(getActivity(), this.historyRecyclerView, this.historyAdapter);
        this.historyRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        this.conditionTextView = new AppCompatTextView[]{
                this.dengshiTextView,
                this.peijianTextView,
                this.storeTextView,
                this.fuwuTextView,
                this.quanziTextView,
                this.helpTextView
        };
    }

    @Override
    protected void initListener() {
        scanImageView.setOnClickListener(v -> App.getInstance().startCapture(getActivity()));
        searchEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId != 3) {
                return false;
            }
            startSearch();
            return false;
        });
        photoImageView.setOnClickListener(v -> App.getInstance().startImagePicker(getActivity(), 1, BaseConstant.CODE_ALBUM, true));
        searchImageView.setOnClickListener(v -> {
            startSearch();
        });
        keyAdapter.setOnItemClickListener((position, str) -> {
            GoodsSearchData goodsSearchData = new GoodsSearchData();
            goodsSearchData.setKeyword(str);
            App.getInstance().startGoodsList(getActivity(), goodsSearchData);
        });
        historyAdapter.setOnItemClickListener((position, str) -> {
            GoodsSearchData goodsSearchData = new GoodsSearchData();
            goodsSearchData.setKeyword(str);
            App.getInstance().startGoodsList(getActivity(), goodsSearchData);
        });
        int i = 0;
        while (true) {
            AppCompatTextView[] appCompatTextViewArr = this.conditionTextView;
            if (i < appCompatTextViewArr.length) {
                int finalI = i;
                appCompatTextViewArr[i].setOnClickListener(v -> clickCondition(finalI));
                i++;
            } else {
                return;
            }
        }
    }

    @Override
    protected void initData() {
        getSearchKey();
        this.position = -1;
        this.gcId = "";
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void showSearchKeyList(String searchKeyList) {
        JsonObject mainJsonObject = new JsonParser().parse(searchKeyList).getAsJsonObject();
        JsonArray searchList = mainJsonObject.getAsJsonArray("list");
        for (int i = 0; i < searchList.size(); i++) {
            this.keyArrayList.add(searchList.get(i).getAsString());
        }
        this.keyAdapter.notifyDataSetChanged();

        this.historyArrayList.clear();
        JsonArray searchHistoryList = mainJsonObject.getAsJsonArray("his_list");
        for (int i = 0; i < searchHistoryList.size(); i++) {
            this.historyArrayList.add(searchHistoryList.get(i).getAsString());
        }
        this.historyAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        getSearchKey();
    }

    // 自定义数据和方法

    private void getSearchKey() {
        mPresenter.requestSearchKeyList();
    }

    private void startSearch() {
        String obj = Objects.requireNonNull(this.searchEditText.getText()).toString();
        if ((obj.contains("打开灯世界搜索") || obj.contains("打开灯饰界搜索")) && obj.contains("赢取红包")) {
            boolean contains = obj.contains("P");
            String replace = obj.replace("打开灯世界搜索：\"", "")
                    .replace("打开灯饰界搜索：\"", "").replace("\"赢取红包", "").replace("P", "");
//            if (contains) {
//                Intent intent = new Intent(getActivity(), PeerInviteActivity.class);
//                intent.putExtra(BaseConstant.DATA_KEYWORD, replace);
//                App.getInstance().start((Activity) getActivity(), intent);
//            } else {
//                Intent intent2 = new Intent(getActivity(), ShareInviteActivity.class);
//                intent2.putExtra(BaseConstant.DATA_KEYWORD, replace);
//                App.getInstance().start((Activity) getActivity(), intent2);
//            }
            this.searchEditText.setText("");
            return;
        }
        switch (this.position) {
            case 0:
            case 5:
                GoodsSearchData goodsSearchData = new GoodsSearchData();
                goodsSearchData.setKeyword(obj);
                App.getInstance().startGoodsList(getActivity(), goodsSearchData);
                break;
            case 1:
                this.gcId = "1093";
                GoodsSearchData goodsSearchData2 = new GoodsSearchData();
                goodsSearchData2.setKeyword(obj);
                goodsSearchData2.setGcId(this.gcId);
                App.getInstance().startGoodsList(getActivity(), goodsSearchData2);
                break;
//            case 2:
//                Intent intent3 = new Intent(getActivity(), StreetActivity.class);
//                intent3.putExtra(BaseConstant.DATA_KEYWORD, obj);
//                App.getInstance().start((Activity) getActivity(), intent3);
//                break;
//            case 3:
//                GoodsSearchData goodsSearchData3 = new GoodsSearchData();
//                goodsSearchData3.setKeyword(obj);
//                Intent intent4 = new Intent(getActivity(), ServerListActivity.class);
//                intent4.putExtra(BaseConstant.DATA_BEAN, goodsSearchData3);
//                App.getInstance().start((Activity) getActivity(), intent4);
//                break;
//            case 4:
//                Intent intent5 = new Intent(getActivity(), CmsSearchActivity.class);
//                intent5.putExtra(BaseConstant.DATA_KEYWORD, obj);
//                App.getInstance().start((Activity) getActivity(), intent5);
//                break;
//            default:
//                Intent intent6 = new Intent(getActivity(), ArticleSearchActivity.class);
//                intent6.putExtra(BaseConstant.DATA_KEYWORD, obj);
//                App.getInstance().start((Activity) getActivity(), intent6);
        }
    }

    private void clickCondition(int i) {
        for (AppCompatTextView appCompatTextView : this.conditionTextView) {
            appCompatTextView.setBackgroundResource(R.drawable.selector_border_white_24dip);
            appCompatTextView.setTextColor(App.getInstance().getColors(R.color.title));
        }
        if (i != this.position) {
            this.conditionTextView[i].setBackgroundResource(R.drawable.selector_border_primary_24dip);
            this.conditionTextView[i].setTextColor(App.getInstance().getColors(R.color.white));
            this.position = i;
            return;
        }
        this.position = -1;
    }

}