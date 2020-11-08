package org.cloud.panzer.ui.main;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseMvpFragment;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.TimeUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.MemberBean;
import org.cloud.panzer.mvp.contract.MineContract;
import org.cloud.panzer.mvp.presenter.MinePresenter;
import org.cloud.panzer.ui.mine.AddressActivity;
import org.cloud.panzer.ui.mine.CenterActivity;

import butterknife.BindView;

public class MineFragment extends BaseMvpFragment<MinePresenter> implements MineContract.View {

    @BindView(R.id.nameTextView)
    AppCompatTextView nameTextView;
    @BindView(R.id.orderRelativeLayout)
    RelativeLayout orderRelativeLayout;
    @BindView(R.id.levelTextView)
    AppCompatTextView levelTextView;
    @BindView(R.id.goodsTextView)
    AppCompatTextView goodsTextView;
    @BindView(R.id.footprintTextView)
    AppCompatTextView footprintTextView;
    @BindView(R.id.storeTextView)
    AppCompatTextView storeTextView;
    @BindView(R.id.avatarImageView)
    AppCompatImageView avatarImageView;
    @BindView(R.id.mineLinearLayout)
    LinearLayoutCompat mineLinearLayout;
    @BindView(R.id.waitEvaluateDotTextView)
    AppCompatTextView waitEvaluateDotTextView;
    @BindView(R.id.waitEvaluateRelativeLayout)
    RelativeLayout waitEvaluateRelativeLayout;
    @BindView(R.id.waitPaymentDotTextView)
    AppCompatTextView waitPaymentDotTextView;
    @BindView(R.id.waitPaymentRelativeLayout)
    RelativeLayout waitPaymentRelativeLayout;
    @BindView(R.id.waitReceiptDotTextView)
    AppCompatTextView waitReceiptDotTextView;
    @BindView(R.id.waitReceiptRelativeLayout)
    RelativeLayout waitReceiptRelativeLayout;
    @BindView(R.id.waitReturnDotTextView)
    AppCompatTextView waitReturnDotTextView;
    @BindView(R.id.waitReturnRelativeLayout)
    RelativeLayout waitReturnRelativeLayout;
    @BindView(R.id.waitTakesDotTextView)
    AppCompatTextView waitTakesDotTextView;
    @BindView(R.id.waitTakesRelativeLayout)
    RelativeLayout waitTakesRelativeLayout;
    @BindView(R.id.redPacketDotTextView)
    AppCompatTextView redPacketDotTextView;
    // 收货地址
    @BindView(R.id.addressTextView)
    AppCompatTextView addressTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_mine;
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
    }

    @Override
    protected void initView() {
        this.nameTextView.setText("请登录");
        this.levelTextView.setText("登录后可见");
    }

    @Override
    protected void initListener() {
        mineLinearLayout.setOnClickListener(view -> App.getInstance().startCheckLogin(getActivity(), CenterActivity.class));
        addressTextView.setOnClickListener(view -> App.getInstance().startCheckLogin(getActivity(), AddressActivity.class));

        waitPaymentRelativeLayout.setOnClickListener(view -> App.getInstance().startOrder(getActivity(), 1));
        waitReceiptRelativeLayout.setOnClickListener(view -> App.getInstance().startOrder(getActivity(), 2));
        waitTakesRelativeLayout.setOnClickListener(view -> App.getInstance().startOrder(getActivity(), 3));
        waitEvaluateRelativeLayout.setOnClickListener(view -> App.getInstance().startOrder(getActivity(), 4));
    }

    @Override
    protected void initData() {
        if (App.getInstance().isLogin()) {
            getData();
            //getMessageCount();
        }
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void showMemberIndexSuccess(BaseBean baseBean) {
        JsonObject mainJsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        MemberBean memberInfo = JsonUtils.jsonToBean(mainJsonObject.getAsJsonObject("member_info"), MemberBean.class);
        BaseImageLoader.getInstance().displayRadius(memberInfo.getAvatar() + "?id=" + TimeUtils.getStampAll(), avatarImageView, App.getInstance().dipToPx(8));

        nameTextView.setText(memberInfo.getUserName());
        goodsTextView.setText(memberInfo.getFavoritesGoods());
        storeTextView.setText(memberInfo.getFavoritesStore());
        footprintTextView.setText(memberInfo.getGoodsBrowse());
        boolean z = false;
        waitPaymentDotTextView.setVisibility(memberInfo.getOrderNopayCount().equals("0") ? View.GONE : View.VISIBLE);
        waitReceiptDotTextView.setVisibility(memberInfo.getOrderNoreceiptCount().equals("0") ? View.GONE : View.VISIBLE);
        waitTakesDotTextView.setVisibility(memberInfo.getOrderNotakesCount().equals("0") ? View.GONE : View.VISIBLE);
        waitEvaluateDotTextView.setVisibility(memberInfo.getOrderNoevalCount().equals("0") ? View.GONE : View.VISIBLE);
        waitReturnDotTextView.setVisibility(memberInfo.getReturnX().equals("0") ? View.GONE : View.VISIBLE);
        if (memberInfo.getRepacketCount().equals("0")) {
            redPacketDotTextView.setVisibility(View.GONE);
        } else {
            redPacketDotTextView.setVisibility(View.VISIBLE);
            redPacketDotTextView.setText(memberInfo.getRepacketCount());
        }
        if (levelTextView.getText().toString().contains("登录后")) {
            levelTextView.setVisibility(View.GONE);
        } else {
            levelTextView.setVisibility(View.VISIBLE);
        }
        //getMobileInfo();
    }

    @Override
    public void showMemberIndexFail(String msg) {

    }

    // 自定义数据和方法

    private void getData() {
        mPresenter.requestMemberIndexData();
    }
}