package org.cloud.panzer.ui.mine;

import android.content.Intent;
import android.util.Log;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.JsonObject;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.StatusBarUtils;
import org.cloud.core.widget.PullRefreshView;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.AddressListAdapter;
import org.cloud.panzer.bean.AddressBean;
import org.cloud.panzer.mvp.contract.AddressContract;
import org.cloud.panzer.mvp.presenter.AddressPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class AddressActivity extends BaseMvpActivity<AddressPresenter> implements AddressContract.View {

    @BindView(R.id.mainPullRefreshView)
    PullRefreshView mainPullRefreshView;
    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.toolbarImageView)
    AppCompatImageView toolbarImageView;

    private AddressListAdapter mainAdapter;
    private ArrayList<AddressBean> mainArrayList;

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected AddressPresenter createPresenter() {
        return new AddressPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_address;
    }

    @Override
    public void onResume() {
        super.onResume();
        getAddress();
    }

    @Override
    protected void initView() {
        setToolbar(this.mainToolbar, "地址管理", R.color.whiteSub);
        this.toolbarImageView.setImageResource(R.drawable.ic_action_add);
        this.mainArrayList = new ArrayList<>();
        this.mainAdapter = new AddressListAdapter(this.mainArrayList);
        this.mainPullRefreshView.getRecyclerView().setAdapter(this.mainAdapter);
    }

    @Override
    protected void initListener() {
        toolbarImageView.setOnClickListener(v -> App.getInstance().start(getActivity(), AddressAddActivity.class));
        mainPullRefreshView.setOnRefreshListener(new PullRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAddress();
            }

            @Override
            public void onLoadMore() {
                getAddress();
            }
        });
        mainAdapter.setOnItemClickListener(new AddressListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, AddressBean addressBean) {
                Intent intent = new Intent(AddressActivity.this.getActivity(), AddressEditActivity.class);
                intent.putExtra(BaseConstant.DATA_BEAN, addressBean);
                App.getInstance().start(AddressActivity.this.getActivity(), intent);
            }

            @Override
            public void onDelete(int position, AddressBean addressBean) {
                deleteAddress(addressBean.getAddressId());
            }

            @Override
            public void onEdit(int position, AddressBean addressBean) {
                Intent intent = new Intent(AddressActivity.this.getActivity(), AddressEditActivity.class);
                intent.putExtra(BaseConstant.DATA_BEAN, addressBean);
                App.getInstance().start(AddressActivity.this.getActivity(), intent);
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    public void showAddressList(BaseBean baseBean) {
        this.mainArrayList.clear();
        JsonObject mainJsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        List<AddressBean> addressList = JsonUtils.jsonToList(mainJsonObject.getAsJsonArray("address_list"), AddressBean.class);
        Log.e("address_list", addressList.size()+"");
        this.mainArrayList.addAll(Objects.requireNonNull(addressList));
        this.mainAdapter.notifyDataSetChanged();
        this.mainPullRefreshView.setComplete();
    }

    @Override
    public void showAddressDelete(BaseBean baseBean) {
        getAddress();
    }

    @Override
    public void showAddressAdd(BaseBean baseBean) {}

    @Override
    public void showAddressEdit(BaseBean baseBean) {}

    // 自定义数据和方法

    public void getAddress() {
        this.mainPullRefreshView.setLoading();
        mPresenter.requestAddressList();
    }

    private void deleteAddress(String str) {
        mPresenter.requestAddressDelete(str);
    }

}