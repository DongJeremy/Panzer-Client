package org.cloud.panzer.ui.goods;

import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.scrollablelayout.ScrollableLayout;

import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseDialog;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.StoreBuyListAdapter;
import org.cloud.panzer.bean.GoodsBuyBean;
import org.cloud.panzer.bean.StoreBuyBean;
import org.cloud.panzer.mvp.contract.GoodsBuyContract;
import org.cloud.panzer.mvp.presenter.GoodsBuyPresenter;
import org.cloud.panzer.ui.mine.AddressActivity;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;

public class BuyActivity extends BaseMvpActivity<GoodsBuyPresenter> implements GoodsBuyContract.View {

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;
    @BindView(R.id.mainScrollableLayout)
    ScrollableLayout mainScrollableLayout;
    @BindView(R.id.addressRelativeLayout)
    RelativeLayout addressRelativeLayout;
    @BindView(R.id.addressNameTextView)
    AppCompatTextView addressNameTextView;
    @BindView(R.id.addressMobileTextView)
    AppCompatTextView addressMobileTextView;
    @BindView(R.id.addressAreaTextView)
    AppCompatTextView addressAreaTextView;
    @BindView(R.id.payOnlineTextView)
    AppCompatTextView payOnlineTextView;
    @BindView(R.id.payOfflineTextView)
    AppCompatTextView payOfflineTextView;
    @BindView(R.id.invoiceNoTextView)
    AppCompatTextView invoiceNoTextView;
    @BindView(R.id.invoiceYesTextView)
    AppCompatTextView invoiceYesTextView;
    @BindView(R.id.mainRecyclerView)
    RecyclerView mainRecyclerView;
    @BindView(R.id.redPacketLinearLayout)
    LinearLayoutCompat redPacketLinearLayout;
    @BindView(R.id.redPacketTextView)
    AppCompatTextView redPacketTextView;
    @BindView(R.id.moneyTextView)
    AppCompatTextView moneyTextView;
    @BindView(R.id.balanceTextView)
    AppCompatTextView balanceTextView;

    private GoodsBuyBean goodsBuyBean;
    private StoreBuyListAdapter mainAdapter;
    private ArrayList<StoreBuyBean> mainArrayList;

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_buy;
    }

    @Override
    protected void initView() {
        goodsBuyBean = new GoodsBuyBean();
        goodsBuyBean.setCartId(getIntent().getStringExtra(BaseConstant.DATA_ID));
        goodsBuyBean.setIfCart(getIntent().getStringExtra(BaseConstant.DATA_IFCART));
        goodsBuyBean.setPayName("online");
        setToolbar(mainToolbar, "确认订单信息");

        mainArrayList = new ArrayList<>();
        mainAdapter = new StoreBuyListAdapter(mainArrayList);
        App.getInstance().setRecyclerView(getActivity(), mainRecyclerView, mainAdapter);

        mainScrollableLayout.getHelper().setCurrentScrollableContainer(mainRecyclerView);
    }

    @Override
    protected void initListener() {
        addressRelativeLayout.setOnClickListener(view -> App.getInstance().start(getActivity(), AddressActivity.class, BaseConstant.CODE_ADDRESS));

        payOnlineTextView.setOnClickListener(view -> {
            goodsBuyBean.setPayName("online");
            payOnlineTextView.setBackgroundResource(R.drawable.selector_border_primary);
            payOfflineTextView.setBackgroundResource(R.drawable.selector_border_grey_add);
        });

        payOfflineTextView.setOnClickListener(view -> {
            goodsBuyBean.setPayName("offline");
            payOnlineTextView.setBackgroundResource(R.drawable.selector_border_grey_add);
            payOfflineTextView.setBackgroundResource(R.drawable.selector_border_primary);
        });

        //invoiceYesTextView.setOnClickListener(view -> App.getInstance().start(getActivity(), InvoiceActivity.class, BaseConstant.CODE_INVOICE));

        invoiceNoTextView.setOnClickListener(view -> {
            goodsBuyBean.setInvoiceId("0");
            invoiceYesTextView.setText("需要发票");
            invoiceNoTextView.setBackgroundResource(R.drawable.selector_border_primary);
            invoiceYesTextView.setBackgroundResource(R.drawable.selector_border_grey_add);
        });

        balanceTextView.setOnClickListener(view -> balance());
    }

    @Override
    protected void initData() {
        getData();
    }

    @Override
    protected GoodsBuyPresenter createPresenter() {
        return new GoodsBuyPresenter();
    }

    @Override
    public void showError(String msg) {
        super.showError(msg);
        BaseToast.getInstance().show(msg);
        App.getInstance().finish(getActivity());
    }

    private void getData() {
        mPresenter.requestGoodsBuyStep1(goodsBuyBean.getCartId(), goodsBuyBean.getIfCart(), goodsBuyBean.getAddressId());
    }

    @Override
    public void showGoodsBuyStep1Success(String goodsInfoData) {
        String jsonString = goodsInfoData.replace("[]", "null");
        handlerData(jsonString);
    }

    @Override
    public void showGoodsBuyStep2Success(String goodsInfoData) {
        JsonObject jsonObject = new JsonParser().parse(goodsInfoData).getAsJsonObject();
        if (jsonObject.get("payment_code").getAsString().equals("online")) {
            App.getInstance().startOrderPay(getActivity(), jsonObject.get("pay_sn").getAsString());
        } else {
            BaseToast.getInstance().show("提交成功，等待卖家发货...");
        }
        App.getInstance().finish(getActivity());
    }

    @Override
    public void showGoodsBuyStep2Error() {
        balanceTextView.setEnabled(true);
        balanceTextView.setText("提交订单");
    }

    private void handlerData(String jsonString) {
        JsonObject mainJsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
        mainArrayList.clear();
        //店铺列表
        JsonObject jsonObject = mainJsonObject.getAsJsonObject("store_cart_list");
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> map : entries) {
            Log.e("TAG", new Gson().toJson(map.getValue()));
            StoreBuyBean storeBuyBean = JsonUtils.jsonToBean(map.getValue(), StoreBuyBean.class);
            storeBuyBean.setKey(map.getKey());
            mainArrayList.add(storeBuyBean);
        }
        //收货地址
        if (!mainJsonObject.has("address_info")) {
            tipsAddress();
            return;
        }
        jsonObject = mainJsonObject.getAsJsonObject("address_info");
        goodsBuyBean.setAddressId(jsonObject.get("address_id").getAsString());
        addressNameTextView.setText(jsonObject.get("true_name").getAsString());
        addressMobileTextView.setText(jsonObject.get("mob_phone").getAsString());
        addressAreaTextView.setText(jsonObject.get("area_info").getAsString());
        addressAreaTextView.append(" " + jsonObject.get("address").getAsString());
        jsonObject = mainJsonObject.getAsJsonObject("address_api");
        goodsBuyBean.setOffPayHash(jsonObject.get("offpay_hash").getAsString());
        goodsBuyBean.setOffPayHashBatch(jsonObject.get("offpay_hash_batch").getAsString());
        //支付方式
        if (jsonObject.get("allow_offpay").getAsString().equals("1")) {
            payOfflineTextView.setVisibility(View.VISIBLE);
        } else {
            payOfflineTextView.setVisibility(View.GONE);
        }
        //店铺代金券
        jsonObject = jsonObject.getAsJsonObject("content");
        Set<Map.Entry<String, JsonElement>> entries2 = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> map : entries2) {
            String key = map.getKey();
            String value = map.getValue().getAsString();
            for (int i = 0; i < mainArrayList.size(); i++) {
                if (mainArrayList.get(i).getKey().equals(key)) {
                    mainArrayList.get(i).setLogisticsMoney(value);
                }
            }
        }
        //店铺总计
        jsonObject = mainJsonObject.getAsJsonObject("store_final_total_list");
        Set<Map.Entry<String, JsonElement>> entries3 = jsonObject.entrySet();
        for (Map.Entry<String, JsonElement> map : entries3) {
            String key = map.getKey();
            String value = map.getValue().getAsString();
            for (int i = 0; i < mainArrayList.size(); i++) {
                if (mainArrayList.get(i).getKey().equals(key)) {
                    mainArrayList.get(i).setTotalMoney(value);
                }
            }
        }
        //平台红包
        if (mainJsonObject.get("rpt_info") instanceof JsonNull) {
            redPacketLinearLayout.setVisibility(View.GONE);
        } else {
            redPacketLinearLayout.setVisibility(View.VISIBLE);
            jsonObject = mainJsonObject.getAsJsonObject("rpt_info");
            goodsBuyBean.setRpt(jsonObject.get("rpacket_id").getAsString() + "|" + jsonObject.get("rpacket_price").getAsString());
            redPacketTextView.setText("满￥");
            redPacketTextView.append(jsonObject.get("rpacket_limit").getAsString());
            redPacketTextView.append("元，优惠￥");
            redPacketTextView.append(jsonObject.get("rpacket_price").getAsString());
            redPacketTextView.append("元");
        }
        //要提交的信息
        goodsBuyBean.setVatHash(mainJsonObject.get("vat_hash").getAsString());
        //合计金额
        String temp = "合计金额：" + "<font color='#FF0000'>￥" + mainJsonObject.get("order_amount").getAsString() + " 元</font>";
        moneyTextView.setText(Html.fromHtml(temp));
        //更新列表
        mainAdapter.notifyDataSetChanged();
    }

    private void tipsAddress() {
        BaseDialog.getInstance().query(getActivity(), "请添加地址", "尚未添加收货地址", (dialog, which) -> App.getInstance().start(getActivity(),
                AddressActivity.class, BaseConstant.CODE_ADDRESS), (dialog, which) -> App.getInstance().finish(getActivity()));
    }

    private void balance() {
        //订单留言
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < mainArrayList.size(); i++) {
            temp.append(mainArrayList.get(i).getKey())
                    .append("|")
                    .append(mainArrayList.get(i).getMessage())
                    .append(",");
        }
        goodsBuyBean.setPayMessage(temp.toString());
        //优惠券处理
        temp = new StringBuilder();
        for (int i = 0; i < mainArrayList.size(); i++) {
            if (mainArrayList.get(i).getStoreVoucherInfo() != null) {
                temp.append(mainArrayList.get(i).getStoreVoucherInfo().getVoucherTId())
                        .append("|")
                        .append(mainArrayList.get(i).getStoreVoucherInfo().getVoucherStoreId())
                        .append("|")
                        .append(mainArrayList.get(i).getStoreVoucherInfo().getVoucherPrice())
                        .append(",");
            }
        }
        goodsBuyBean.setVoucher(temp.toString());

        balanceTextView.setEnabled(false);
        balanceTextView.setText("提交中...");
        mPresenter.requestGoodsBuyStep2(JsonUtils.jsonToMaps(JsonUtils.objectToJson(goodsBuyBean)));
    }
}