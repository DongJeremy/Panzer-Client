package org.cloud.panzer.ui.mine;

import android.content.Intent;
import android.text.TextUtils;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.StringUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.AddressContract;
import org.cloud.panzer.mvp.presenter.AddressPresenter;
import org.cloud.panzer.ui.choose.AreaActivity;

import java.util.Objects;

import butterknife.BindView;

public class AddressAddActivity extends BaseMvpActivity<AddressPresenter> implements AddressContract.View {

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

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mine_address_add;
    }

    @Override
    protected void initView() {
        setToolbar(this.mainToolbar, "添加地址");
        this.cityId = "";
        this.areaId = "";
        this.areaInfo = "";
//        this.nameEditText.setText(App.getInstance().getMemberBean().getTruename());
//        this.mobileEditText.setText(BaseApplication.get().getMemberBean().getMemberMobile());
//        this.addressEditText.setText(BaseApplication.get().getAddress());
    }

    @Override
    protected void initListener() {
        this.areaEditText.setOnClickListener(view -> App.getInstance().start(getActivity(), AreaActivity.class, 1000));
        this.saveTextView.setOnClickListener(view -> addAddress());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected AddressPresenter createPresenter() {
        return new AddressPresenter();
    }

    @Override
    public void showAddressList(String address) {

    }

    @Override
    public void showAddressAdd(String address) {
//        if (!BaseApplication.get().isAddAddress()) {
//            this.saveTextView.setEnabled(true);
//            this.saveTextView.setText("保存地址");
//            BaseToast.getInstance().showSuccess();
//            App.getInstance().finish(AddressAddActivity.this.getActivity());
//            return;
//        }
        //BaseApplication.get().setAddAddress(false);
        Intent intent = new Intent();
        String addressId = new JsonParser().parse(address).getAsJsonObject().get("address_id").getAsString();
        intent.putExtra(BaseConstant.DATA_ID, addressId);
        App.getInstance().finishOk(AddressAddActivity.this.getActivity(), intent);
    }

    @Override
    public void showAddressDelete(String address) {

    }

    @Override
    public void showAddressEdit(String address) {

    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        this.saveTextView.setEnabled(true);
        this.saveTextView.setText("保存地址");
    }

    private void addAddress() {
        App.getInstance().hideKeyboard(getActivity());
        String trueName = Objects.requireNonNull(this.nameEditText.getText()).toString();
        String mobPhone = Objects.requireNonNull(this.mobileEditText.getText()).toString();
        String address = Objects.requireNonNull(this.addressEditText.getText()).toString();
        String str = this.defaultSwitch.isChecked() ? "1" : "0";
        if (TextUtils.isEmpty(trueName) || TextUtils.isEmpty(mobPhone) || TextUtils.isEmpty(address)) {
            BaseToast.getInstance().show("请输入完整的信息！");
        } else if (!StringUtils.isMobile(mobPhone)) {
            BaseToast.getInstance().show("手机号码格式不正确！");
        } else if (TextUtils.isEmpty(this.cityId)) {
            BaseToast.getInstance().show("请选择区域！");
        } else {
            this.saveTextView.setEnabled(false);
            this.saveTextView.setText("添加中...");
            mPresenter.requestAddressAdd(trueName, mobPhone, this.cityId, this.areaId, address, this.areaInfo, str);
        }
    }
}