package org.cloud.panzer.ui.choose;

import android.content.Intent;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.Utils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.AreaListAdapter;
import org.cloud.panzer.bean.AreaBean;
import org.cloud.panzer.mvp.contract.AreaContract;
import org.cloud.panzer.mvp.presenter.AreaPresenter;

import java.util.ArrayList;

import butterknife.BindView;

public class AreaActivity extends BaseMvpActivity<AreaPresenter> implements AreaContract.View {

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.areaTextView)
    AppCompatTextView areaTextView;
    @BindView(R.id.cityTextView)
    AppCompatTextView cityTextView;
    @BindView(R.id.provinceTextView)
    AppCompatTextView provinceTextView;
    @BindView(R.id.areaRecyclerView)
    RecyclerView areaRecyclerView;
    @BindView(R.id.cityRecyclerView)
    RecyclerView cityRecyclerView;
    @BindView(R.id.provinceRecyclerView)
    RecyclerView provinceRecyclerView;

    private String areaIdString;
    private String cityIdString;
    private String provinceIdString;
    private String areaNameString;
    private String cityNameString;
    private String provinceNameString;

    private AreaListAdapter areaAdapter;
    private ArrayList<AreaBean> areaArrayList;
    private AreaListAdapter cityAdapter;
    private ArrayList<AreaBean> cityArrayList;
    private AreaListAdapter provinceAdapter;
    private ArrayList<AreaBean> provinceArrayList;

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choose_area;
    }

    @Override
    protected void initView() {
        setToolbar(mainToolbar, "选择地区", R.color.whiteSub);
        this.areaIdString = "0";
        this.cityIdString = "0";
        this.provinceIdString = "0";

        this.areaArrayList = new ArrayList<>();
        this.areaAdapter = new AreaListAdapter(this.areaArrayList);
        App.getInstance().setRecyclerView(App.getInstance(), this.areaRecyclerView, this.areaAdapter);
        this.areaRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.cityArrayList = new ArrayList<>();
        this.cityAdapter = new AreaListAdapter(this.cityArrayList);
        App.getInstance().setRecyclerView(App.getInstance(), this.cityRecyclerView, this.cityAdapter);
        this.cityRecyclerView.setItemAnimator(new DefaultItemAnimator());

        this.provinceArrayList = new ArrayList<>();
        this.provinceAdapter = new AreaListAdapter(this.provinceArrayList);
        App.getInstance().setRecyclerView(App.getInstance(), this.provinceRecyclerView, this.provinceAdapter);
        this.provinceRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void initListener() {
        provinceAdapter.setOnItemClickListener((position, areaBean) -> {
            this.areaIdString = "";
            this.cityIdString = "";
            this.areaNameString = "";
            this.cityNameString = "";
            this.provinceIdString = areaBean.getAreaId();
            this.provinceNameString = areaBean.getAreaName();
            this.areaTextView.setText("地区");
            this.cityTextView.setText("城市");
            this.provinceTextView.setText(this.provinceNameString);
            this.areaTextView.setTextColor(Utils.getColors(R.color.greyAdd));
            this.cityTextView.setTextColor(Utils.getColors(R.color.greyAdd));
            this.provinceTextView.setTextColor(Utils.getColors(R.color.primary));
            getCity();
        });

        cityAdapter.setOnItemClickListener((position, areaBean) -> {
            areaIdString = "";
            areaNameString = "";
            cityIdString = areaBean.getAreaId();
            cityNameString = areaBean.getAreaName();
            areaTextView.setText("地区");
            cityTextView.setText(cityNameString);
            areaTextView.setTextColor(Utils.getColors(R.color.greyAdd));
            cityTextView.setTextColor(Utils.getColors(R.color.primary));
            getArea();
        });

        areaAdapter.setOnItemClickListener((position, areaBean) -> {
            areaIdString = areaBean.getAreaId();
            areaNameString = areaBean.getAreaName();
            Intent intent = new Intent();
            intent.putExtra("area_id", areaIdString);
            intent.putExtra("city_id", cityIdString);
            intent.putExtra("province_id", provinceIdString);
            intent.putExtra("area_name", areaNameString);
            intent.putExtra("city_name", cityNameString);
            intent.putExtra("province_name", provinceNameString);
            intent.putExtra("area_info", provinceNameString + " " + cityNameString + " " + areaNameString);
            App.getInstance().finishOk(getActivity(), intent);
        });
    }

    @Override
    protected void initData() {
        getProvince();
    }

    @Override
    protected AreaPresenter createPresenter() {
        return new AreaPresenter();
    }

    //自定义方法

    private void getArea() {
        mPresenter.requestArea(cityIdString, "area");
    }

    private void getCity() {
        mPresenter.requestArea(provinceIdString, "city");
    }

    private void getProvince() {
        mPresenter.requestArea("", "province");
    }

    @Override
    public void showAreaList(BaseBean baseBean, String type) {
        JsonObject mainJsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        JsonArray cartList = mainJsonObject.getAsJsonArray("area_list");

        switch(type) {
            case "area":
                this.areaArrayList.clear();
                areaArrayList.addAll(JsonUtils.jsonToList(cartList, AreaBean.class));
                areaAdapter.notifyDataSetChanged();
                break;
            case "city":
                cityArrayList.clear();
                cityArrayList.addAll(JsonUtils.jsonToList(cartList, AreaBean.class));
                cityAdapter.notifyDataSetChanged();
                break;
            case "province":
                provinceArrayList.clear();
                provinceArrayList.addAll(JsonUtils.jsonToList(cartList, AreaBean.class));
                provinceAdapter.notifyDataSetChanged();
                break;
        }
    }
}