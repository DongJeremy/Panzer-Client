package org.cloud.panzer.ui.order;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.JsonObject;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseCountTime;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.PayContract;
import org.cloud.panzer.mvp.presenter.PayPresenter;

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

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
//        if (App.getInstance().isWxPay()) {
//            App.getInstance().setWxPay(false);
//            if (App.getInstance().isSuccess()) {
//                BaseToast.getInstance().show("微信支付成功");
//            } else {
//                BaseToast.getInstance().show("微信支付失败");
//            }
//            App.getInstance().finish(getActivity());
//        }
    }

    @Override
    protected PayPresenter createPresenter() {
        return new PayPresenter();
    }

    @Override
    public void showPaySuccess(BaseBean baseBean) {
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
    public void showPayFail(String reason) {
        BaseToast.getInstance().show(reason);
        new BaseCountTime(BaseConstant.TIME_COUNT, BaseConstant.TIME_TICK) {
            @Override
            public void onFinish() {
                super.onFinish();
                getData();
            }
        }.start();
    }

    private void getData() {
        mPresenter.requestPay(paySnString);
    }
}