package org.cloud.panzer.ui.mine;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.StringUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.AddressBean;
import org.cloud.panzer.mvp.contract.AddressContract;
import org.cloud.panzer.mvp.presenter.AddressPresenter;
import org.cloud.panzer.ui.choose.AreaActivity;

import java.util.Objects;

import butterknife.BindView;

public class AddressEditActivity extends BaseMvpActivity<AddressPresenter> implements AddressContract.View {

    @BindView(R.id.addressEditText)
    AppCompatEditText addressEditText;
    @BindView(R.id.areaEditText)
    AppCompatEditText areaEditText;
    @BindView(R.id.defaultSwitch)
    SwitchCompat defaultSwitch;
    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.mobileEditText)
    AppCompatEditText mobileEditText;
    @BindView(R.id.nameEditText)
    AppCompatEditText nameEditText;
    @BindView(R.id.saveTextView)
    AppCompatTextView saveTextView;

    private String areaId;
    private String areaInfo;
    private String cityId;
    private AddressBean addressBean;

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_address_edit;
    }

    @Override
    protected void initView() {
        setToolbar(this.mainToolbar, "编辑地址", R.color.whiteSub);
        this.addressBean = (AddressBean) getIntent().getSerializableExtra(BaseConstant.DATA_BEAN);
        if (this.addressBean == null) {
            BaseToast.getInstance().showDataError();
            App.getInstance().finish(getActivity());
        }
        this.cityId = this.addressBean.getCityId();
        this.areaId = this.addressBean.getAreaId();
        this.areaInfo = this.addressBean.getAreaInfo();
        this.nameEditText.setText(this.addressBean.getTrueName());
        this.mobileEditText.setText(this.addressBean.getMobPhone());
        this.areaEditText.setText(this.addressBean.getAreaInfo());
        this.addressEditText.setText(this.addressBean.getAddress());
        this.defaultSwitch.setChecked(this.addressBean.getIsDefault().equals("1"));
        this.nameEditText.setSelection(this.addressBean.getTrueName().length());
    }

    @Override
    protected void initListener() {
        this.areaEditText.setOnClickListener(view -> App.getInstance().start(getActivity(), AreaActivity.class, 1000));
        this.saveTextView.setOnClickListener(view -> editAddress());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == -1 && requestCode == 1000) {
            this.cityId = intent.getStringExtra("city_id");
            this.areaId = intent.getStringExtra("area_id");
            this.areaInfo = intent.getStringExtra("area_info");
            this.areaEditText.setText(this.areaInfo);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected AddressPresenter createPresenter() {
        return new AddressPresenter();
    }

    @Override
    public void showAddressList(BaseBean baseBean) {

    }

    @Override
    public void showAddressAdd(BaseBean baseBean) {

    }

    @Override
    public void showAddressDelete(BaseBean baseBean) {

    }

    @Override
    public void showAddressEdit(BaseBean baseBean) {
        this.saveTextView.setEnabled(true);
        this.saveTextView.setText("保存地址");
        BaseToast.getInstance().showSuccess();
        App.getInstance().finish(AddressEditActivity.this.getActivity());
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        this.saveTextView.setEnabled(true);
        this.saveTextView.setText("保存地址");
    }

    private void editAddress() {
        App.getInstance().hideKeyboard(getActivity());
        String trueName = Objects.requireNonNull(this.nameEditText.getText()).toString();
        String mobPhone = Objects.requireNonNull(this.mobileEditText.getText()).toString();
        String address = Objects.requireNonNull(this.addressEditText.getText()).toString();
        String isDefault = this.defaultSwitch.isChecked() ? "1" : "0";
        if (TextUtils.isEmpty(trueName) || TextUtils.isEmpty(mobPhone) || TextUtils.isEmpty(address)) {
            BaseToast.getInstance().show("请输入完整的信息！");
        } else if (!StringUtils.isMobile(mobPhone)) {
            BaseToast.getInstance().show("手机号码格式不正确！");
        } else if (TextUtils.isEmpty(this.cityId)) {
            BaseToast.getInstance().show("请选择区域！");
        } else {
            this.saveTextView.setEnabled(false);
            this.saveTextView.setText("修改中...");
            mPresenter.requestAddressEdit(this.addressBean.getAddressId(), trueName, mobPhone, this.cityId, this.areaId, address, this.areaInfo, isDefault);
        }
    }

}