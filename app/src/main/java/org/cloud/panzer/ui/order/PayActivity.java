package org.cloud.panzer.ui.order;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;

import com.alipay.sdk.app.PayTask;
import com.google.gson.JsonObject;
import com.tencent.mm.opensdk.modelpay.PayReq;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseCountTime;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.PayResult;
import org.cloud.panzer.mvp.contract.PayContract;
import org.cloud.panzer.mvp.presenter.PayPresenter;

import java.util.Map;
import java.util.Objects;

import butterknife.BindView;

public class PayActivity extends BaseMvpActivity<PayPresenter> implements PayContract.View {

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.moneyTextView)
    AppCompatTextView moneyTextView;
    @BindView(R.id.snTextView)
    AppCompatTextView snTextView;
    @BindView(R.id.ownLinearLayout)
    LinearLayoutCompat ownLinearLayout;
    @BindView(R.id.preDepositRelativeLayout)
    RelativeLayout preDepositRelativeLayout;
    @BindView(R.id.preDepositTextView)
    AppCompatTextView preDepositTextView;
    @BindView(R.id.preDepositRadioButton)
    AppCompatRadioButton preDepositRadioButton;
    @BindView(R.id.rechargeCardRelativeLayout)
    RelativeLayout rechargeCardRelativeLayout;
    @BindView(R.id.rechargeCardTextView)
    AppCompatTextView rechargeCardTextView;
    @BindView(R.id.rechargeCardRadioButton)
    AppCompatRadioButton rechargeCardRadioButton;
    @BindView(R.id.passwordRelativeLayout)
    RelativeLayout passwordRelativeLayout;
    @BindView(R.id.passwordEditText)
    AppCompatEditText passwordEditText;
    @BindView(R.id.thrLinearLayout)
    LinearLayoutCompat thrLinearLayout;
    @BindView(R.id.aliPayRelativeLayout)
    RelativeLayout aliPayRelativeLayout;
    @BindView(R.id.aliPayRadioButton)
    AppCompatRadioButton aliPayRadioButton;
    @BindView(R.id.wxPayRelativeLayout)
    RelativeLayout wxPayRelativeLayout;
    @BindView(R.id.wxPayRadioButton)
    AppCompatRadioButton wxPayRadioButton;
    @BindView(R.id.payTextView)
    AppCompatTextView payTextView;

    private String paySnString;
    private String passwordString;
    private String rcbPayString;
    private String pdPayString;
    private String paymentCodeString;

    private final Handler mHandler = new Handler(msg -> {
        if (msg.what == 1) {
            @SuppressWarnings("unchecked")
            PayResult payResult = new PayResult((Map<String, String>) msg.obj);
            //String resultInfo = payResult.getResult();
            String resultStatus = payResult.getResultStatus();
            if (TextUtils.equals(resultStatus, "9000")) {
                payTextView.setText("订单支付");
                BaseToast.getInstance().show("支付成功");
                App.getInstance().finish(getActivity());
            } else {
                if (TextUtils.equals(resultStatus, "8000")) {
                    BaseToast.getInstance().show("支付结果确认中");
                } else {
                    BaseToast.getInstance().show("支付失败");
                }
            }
        }
        return false;
    });

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_pay;
    }

    @Override
    protected void initView() {
        paySnString = getIntent().getStringExtra(BaseConstant.DATA_ID);
        if (TextUtils.isEmpty(paySnString)) {
            BaseToast.getInstance().showDataError();
            App.getInstance().finish(getActivity());
        }

        setToolbar(mainToolbar, "订单支付");

        snTextView.setText("订单：");
        snTextView.append(paySnString);

        passwordString = "";
        rcbPayString = "";
        pdPayString = "";
        paymentCodeString = "";

        getData();
    }

    @Override
    protected void initListener() {
        preDepositRelativeLayout.setOnClickListener(v -> this.preDepositRadioButton.setChecked(true));

        this.payTextView.setOnClickListener(view -> {
            boolean isChecked = this.preDepositRadioButton.isChecked();
            if (this.rechargeCardRadioButton.isChecked()) {
                isChecked = true;
            }
            if (this.aliPayRadioButton.isChecked()) {
                isChecked = true;
            }
            if (this.wxPayRadioButton.isChecked()) {
                isChecked = true;
            }
            if (!isChecked) {
                BaseToast.getInstance().show("请选择支付方式！");
            } else {
                pay();
            }
        });

        this.aliPayRadioButton.setOnCheckedChangeListener((compoundButton, z) -> {
            if (z) {
                this.paymentCodeString = "alipay";
                this.preDepositRadioButton.setChecked(false);
                this.rechargeCardRadioButton.setChecked(false);
                this.wxPayRadioButton.setChecked(false);
                this.passwordRelativeLayout.setVisibility(View.GONE);
            }
        });

        this.wxPayRadioButton.setOnCheckedChangeListener((compoundButton, z) -> {
            if (z) {
                this.paymentCodeString = "wxpay";
                this.preDepositRadioButton.setChecked(false);
                this.rechargeCardRadioButton.setChecked(false);
                this.aliPayRadioButton.setChecked(false);
                this.passwordRelativeLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (App.getInstance().isWxPay()) {
            App.getInstance().setWxPay(false);
            if (App.getInstance().isWxPaySuccess()) {
                BaseToast.getInstance().show("微信支付成功");
                App.getInstance().finish(getActivity());
                return;
            }
            BaseToast.getInstance().show("微信支付失败");
        }
    }

    @Override
    protected PayPresenter createPresenter() {
        return new PayPresenter();
    }

    @Override
    public void showPayListSuccess(BaseBean baseBean) {
        JsonObject mainJsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        JsonObject jsonObject = mainJsonObject.getAsJsonObject("pay_info");
        //站内支付
        double payAmount = Double.parseDouble(jsonObject.get("pay_amount").getAsString());
        double preDeposit = Double.parseDouble(jsonObject.get("member_available_pd").getAsString());
        double rechargeCard = Double.parseDouble(jsonObject.get("member_available_rcb").getAsString());
        if (preDeposit > payAmount) {
            ownLinearLayout.setVisibility(View.VISIBLE);
            preDepositRelativeLayout.setVisibility(View.VISIBLE);
            preDepositTextView.append("（可用金额：￥" + jsonObject.get("member_available_pd").getAsString() + "）");
        }
        if (rechargeCard > payAmount) {
            ownLinearLayout.setVisibility(View.VISIBLE);
            rechargeCardRelativeLayout.setVisibility(View.VISIBLE);
            rechargeCardTextView.append("（可用金额：￥" + jsonObject.get("member_available_rcb").getAsString() + "）");
        }
        Log.e("TAG", "showPaySuccess: ");
        //第三方支付
        if (baseBean.getDatas().contains("wxpay")) {
            thrLinearLayout.setVisibility(View.VISIBLE);
            wxPayRelativeLayout.setVisibility(View.VISIBLE);
        }
        if (baseBean.getDatas().contains("alipay")) {
            thrLinearLayout.setVisibility(View.VISIBLE);
            aliPayRelativeLayout.setVisibility(View.VISIBLE);
        }
        if (ownLinearLayout.getVisibility() == View.GONE) {
            aliPayRadioButton.setChecked(true);
        }
        moneyTextView.setText("￥");
        moneyTextView.append(payAmount + "");
        payTextView.setText("订单支付：￥");
        payTextView.append(payAmount + "");
    }

    @Override
    public void showPayListFail(String reason) {
        BaseToast.getInstance().show(reason);
        new BaseCountTime(BaseConstant.TIME_COUNT, BaseConstant.TIME_TICK) {
            @Override
            public void onFinish() {
                super.onFinish();
                getData();
            }
        }.start();
    }

    @Override
    public void showPayNewSuccess(BaseBean baseBean) {
        PayActivity.this.payTextView.setEnabled(true);
        PayActivity.this.payTextView.setText("订单支付");
        BaseToast.getInstance().show("支付成功");
        App.getInstance().finish(PayActivity.this.getActivity());
    }

    @Override
    public void showPayNewFail(String reason) {
        PayActivity.this.payTextView.setEnabled(true);
        PayActivity.this.payTextView.setText("订单支付");
        BaseToast.getInstance().show("支付成功");
        App.getInstance().finish(PayActivity.this.getActivity());
    }

    @Override
    public void showWxAppPay3Success(BaseBean baseBean) {
        PayActivity.this.payTextView.setEnabled(true);
        PayActivity.this.payTextView.setText("订单支付");
        Log.e("TAG", "showWxAppPay3Success: ");
        Log.e("TAG", baseBean.getDatas());
        JsonObject jsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        PayReq payReq = new PayReq();
        //payReq.appId = jsonObject.get("appid").getAsString();
        payReq.appId = "wx5e6ad08007dc084d";
        payReq.partnerId = jsonObject.get("partnerid").getAsString();
        payReq.prepayId = jsonObject.get("prepayid").getAsString();
        payReq.nonceStr = jsonObject.get("noncestr").getAsString();
        payReq.timeStamp = jsonObject.get("timestamp").getAsString();
        payReq.packageValue = jsonObject.get("package").getAsString();
        //String wxPaySign = WxPayHelper.createWxPaySign();
        //payReq.sign = jsonObject.get("sign").getAsString();
        payReq.extData = "app data";
        App.getInstance().setWxPay(true);
        App.getInstance().setWxPaySuccess(false);
        App.getInstance().getIwxapi().sendReq(payReq);
    }

    @Override
    public void showWxAppPay3Fail(String reason) {
        PayActivity.this.payTextView.setEnabled(true);
        PayActivity.this.payTextView.setText("订单支付");
        BaseToast.getInstance().show(reason);
    }

    @Override
    public void showCheckPdPwdSuccess(BaseBean baseBean) {
        if (baseBean.getDatas().equals("1")) {
            ownPay();
            return;
        }
        PayActivity.this.payTextView.setEnabled(true);
        PayActivity.this.payTextView.setText("订单支付");
        BaseToast.getInstance().showFailure();
    }

    @Override
    public void showCheckPdPwdFail(String reason) {
        PayActivity.this.payTextView.setEnabled(true);
        PayActivity.this.payTextView.setText("订单支付");
        BaseToast.getInstance().show(reason);
    }

    @Override
    public void showAlipayNativePaySuccess(BaseBean baseBean) {
        payTextView.setEnabled(true);
        payTextView.setText("订单支付");
        JsonObject jsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        Log.e("TAG", baseBean.getDatas());
        final String signStr = jsonObject.get("signStr").getAsString();
        Runnable payRunnable = () -> {
            PayTask alipay = new PayTask(getActivity());
            Map<String, String> result = alipay.payV2(signStr, true);
            Message msg = new Message();
            msg.what = 1;
            msg.obj = result;
            mHandler.sendMessage(msg);
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void showAlipayNativePayFail(String reason) {
        payTextView.setEnabled(true);
        payTextView.setText("订单支付");
        BaseToast.getInstance().show(reason);
    }

    private void getData() {
        mPresenter.requestPayList(paySnString);
    }

    private void ownPay() {
        mPresenter.requestPayNew(this.paySnString, this.passwordString, this.rcbPayString, this.pdPayString);
    }

    private void pay() {
        String str = "0";
        this.pdPayString = this.preDepositRadioButton.isChecked() ? "1" : str;
        if (this.rechargeCardRadioButton.isChecked()) {
            str = "1";
        }
        this.rcbPayString = str;
        if (this.pdPayString.equals("1") || this.rcbPayString.equals("1")) {
            this.passwordString = Objects.requireNonNull(this.passwordEditText.getText()).toString();
            if (TextUtils.isEmpty(this.passwordString)) {
                BaseToast.getInstance().show("请输入支付密码！");
                return;
            }
            this.payTextView.setEnabled(false);
            this.payTextView.setText("支付中...");
            mPresenter.requestCheckPdPwd(this.passwordString);
        } else if (this.paymentCodeString.equals("alipay")) {
            payTextView.setEnabled(false);
            payTextView.setText("支付中...");
            mPresenter.requestAlipayNativePay(paySnString);
        } else if (this.paymentCodeString.equals("wxpay")) {
            this.payTextView.setEnabled(false);
            this.payTextView.setText("支付中...");
            mPresenter.requestWxAppPay3(this.paySnString);
        }
    }
}