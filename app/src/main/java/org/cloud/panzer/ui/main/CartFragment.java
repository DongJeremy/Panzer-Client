package org.cloud.panzer.ui.main;

import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.cloud.core.base.BaseMvpFragment;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.widget.PullRefreshView;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.CartListAdapter;
import org.cloud.panzer.bean.CartBean;
import org.cloud.panzer.mvp.contract.CartContract;
import org.cloud.panzer.mvp.presenter.CartPresenter;
import org.cloud.panzer.ui.home.ChatListActivity;

import java.util.ArrayList;

import butterknife.BindView;
import io.github.xudaojie.qrcodelib.CaptureActivity;

public class CartFragment extends BaseMvpFragment<CartPresenter> implements CartContract.View {

    @BindView(R.id.scanImageView)
    AppCompatImageView scanImageView;
    @BindView(R.id.balanceTextView)
    AppCompatTextView balanceTextView;
    @BindView(R.id.lineView)
    View lineView;
    @BindView(R.id.mainCheckBox)
    AppCompatCheckBox mainCheckBox;
    @BindView(R.id.mainPullRefreshView)
    PullRefreshView mainPullRefreshView;
    @BindView(R.id.messageDotTextView)
    AppCompatTextView messageDotTextView;
    @BindView(R.id.messageImageView)
    AppCompatImageView messageImageView;
    @BindView(R.id.moneyTextView)
    AppCompatTextView moneyTextView;
    @BindView(R.id.operaLinearLayout)
    LinearLayoutCompat operaLinearLayout;
    @BindView(R.id.photoImageView)
    AppCompatImageView photoImageView;
    @BindView(R.id.searchEditText)
    AppCompatEditText searchEditText;
    @BindView(R.id.tipsRelativeLayout)
    RelativeLayout tipsRelativeLayout;
    @BindView(R.id.tipsTextView)
    AppCompatTextView tipsTextView;

    private String cartIdString;
    public int countInt;
    public float moneyFloat;
    public CartListAdapter mainAdapter;
    public ArrayList<CartBean> mainArrayList;

    @Override
    public void onResume() {
        super.onResume();
        if (App.getInstance().isLogin()) {
            getCart();
        }
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_cart;
    }

    @Override
    protected CartPresenter createPresenter() {
        return new CartPresenter();
    }

    @Override
    protected void initView() {
        if (App.getInstance().isLogin()) {
            this.tipsTextView.setText("请稍后...");
        }
        this.countInt = 0;
        this.moneyFloat = 0.0f;
        this.cartIdString = "";
        this.mainArrayList = new ArrayList<>();
        this.mainAdapter = new CartListAdapter(this.mainArrayList);
        this.mainPullRefreshView.getRecyclerView().setAdapter(this.mainAdapter);
        this.mainPullRefreshView.setCanLoadMore(false);
    }

    @Override
    protected void initListener() {
        scanImageView.setOnClickListener(v -> App.getInstance().startCapture(getActivity()));
        messageImageView.setOnClickListener(view -> App.getInstance().startCheckLogin(getActivity(), ChatListActivity.class));
        tipsRelativeLayout.setOnClickListener(v -> {
            if (!App.getInstance().isLogin()) {
                App.getInstance().startLogin(getActivity());
            } else {
                getCart();
            }
        });
        mainCheckBox.setOnClickListener(view -> {
            for (int i = 0; i < mainArrayList.size(); i++) {
                mainArrayList.get(i).setCheck(mainCheckBox.isChecked());
                for (int j = 0; j < mainArrayList.get(i).getGoods().size(); j++) {
                    mainArrayList.get(i).getGoods().get(j).setCheck(mainCheckBox.isChecked());
                }
            }
            mainAdapter.notifyDataSetChanged();
            calc();
        });
        mainAdapter.setOnItemClickListener(new CartListAdapter.OnItemClickListener() {
            @Override
            public void onCheck(int position, boolean isCheck, CartBean cartBean) {
                mainArrayList.get(position).setCheck(isCheck);
                for (int i = 0; i < mainArrayList.get(position).getGoods().size(); i++) {
                    mainArrayList.get(position).getGoods().get(i).setCheck(isCheck);
                }
                mainAdapter.notifyItemChanged(position);
                checkAll();
                calc();
            }

            @Override
            public void onClick(int position, CartBean cartBean) {

            }

            @Override
            public void onGoods(int position, int positionGoods, CartBean.GoodsBean goodsBean) {
                App.getInstance().startGoods(getActivity(), goodsBean.getGoodsId());
            }

            @Override
            public void onGoodsAdd(int position, int positionGoods, CartBean.GoodsBean goodsBean) {
                int number = Integer.parseInt(goodsBean.getGoodsNum()) + 1;
                cartEditQuantity(goodsBean.getCartId(), number);
            }

            @Override
            public void onGoodsCheck(int position, int positionGoods, boolean isCheck, CartBean.GoodsBean goodsBean) {
                boolean check = true;
                mainArrayList.get(position).getGoods().get(positionGoods).setCheck(isCheck);
                for (int i = 0; i < mainArrayList.get(position).getGoods().size(); i++) {
                    if (!mainArrayList.get(position).getGoods().get(i).isCheck()) {
                        check = false;
                    }
                }
                mainArrayList.get(position).setCheck(check);
                mainAdapter.notifyItemChanged(position);
                checkAll();
                calc();
            }

            @Override
            public void onGoodsDelete(int position, int positionGoods, CartBean.GoodsBean goodsBean) {
                cartDel(position, positionGoods, goodsBean);
            }

            @Override
            public void onGoodsSub(int position, int positionGoods, CartBean.GoodsBean goodsBean) {
                int number = Integer.parseInt(goodsBean.getGoodsNum());
                if (number == 1) {
                    BaseToast.getInstance().show("最小要买一件哦...");
                    return;
                }
                number--;
                cartEditQuantity(goodsBean.getCartId(), number);
            }

            @Override
            public void onStore(int position, CartBean cartBean) {
                App.getInstance().startStore(getActivity(), cartBean.getStoreId());
            }
        });
    }

    @Override
    protected void initData() {
        getCart();
    }

    @Override
    public void showError(String msg) {
        tipsTextView.setText(msg);
    }

    @Override
    public void showCartListData(String cartListData) {
        mainArrayList.clear();
        JsonObject mainJsonObject = new JsonParser().parse(cartListData).getAsJsonObject();
        JsonArray cartList = mainJsonObject.getAsJsonArray("cart_list");
        moneyFloat = mainJsonObject.get("sum").getAsFloat();
        countInt = mainJsonObject.get("cart_count").getAsInt();
        mainArrayList.addAll(JsonUtils.jsonToList(cartList, CartBean.class));
        for (int i = 0; i < mainArrayList.size(); i++) {
            mainArrayList.get(i).setCheck(true);
            for (int j = 0; j < mainArrayList.get(i).getGoods().size(); j++) {
                mainArrayList.get(i).getGoods().get(j).setCheck(true);
            }
        }
        if (mainArrayList.size() == 0) {
            tipsEmpty();
        } else {
            tipsRelativeLayout.setVisibility(View.GONE);
            operaLinearLayout.setVisibility(View.VISIBLE);
            mainPullRefreshView.setVisibility(View.VISIBLE);
            lineView.setVisibility(View.VISIBLE);
            calc();
        }
        mainAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCartEditQuantity(String cartEditData) {
        getCart();
    }

    @Override
    public void showCartDeleteData(int position, int positionGoods, String cartListData) {
        mainArrayList.get(position).getGoods().remove(positionGoods);
        if (mainArrayList.get(position).getGoods().size() == 0) {
            mainArrayList.remove(position);
        }
        if (mainArrayList.size() == 0) {
            tipsEmpty();
        }
        if (position == 0) {
            mainAdapter.notifyDataSetChanged();
        } else {
            mainAdapter.notifyItemChanged(position);
        }
        calc();
    }

    // 自定义数据和方法

    private void getCart() {
        mPresenter.requestCartListData();
    }

    private void tipsEmpty() {
        tipsRelativeLayout.setVisibility(View.VISIBLE);
        operaLinearLayout.setVisibility(View.GONE);
        mainPullRefreshView.setVisibility(View.GONE);
        lineView.setVisibility(View.GONE);
        tipsTextView.setText("购物车是空的，去逛逛吧！");
    }

    private void checkAll() {
        boolean check = true;
        for (int i = 0; i < mainArrayList.size(); i++) {
            for (int j = 0; j < mainArrayList.get(i).getGoods().size(); j++) {
                if (!mainArrayList.get(i).getGoods().get(j).isCheck()) {
                    check = false;
                }
            }
        }
        mainCheckBox.setChecked(check);
    }

    private void cartEditQuantity(String cartId, int quantity) {
        mPresenter.requestCartEditQuantity(cartId, quantity + "");
    }

    private void cartDel(final int position, final int positionGoods, CartBean.GoodsBean goodsBean) {
        mPresenter.requestCartDelete(goodsBean.getCartId(), position, positionGoods);
    }

    private void calc() {
        countInt = 0;
        moneyFloat = 0.0f;
        cartIdString = "";
        for (int i = 0; i < mainArrayList.size(); i++) {
            for (int j = 0; j < mainArrayList.get(i).getGoods().size(); j++) {
                if (mainArrayList.get(i).getGoods().get(j).isCheck()) {
                    String cartId = mainArrayList.get(i).getGoods().get(j).getCartId();
                    int count = Integer.parseInt(mainArrayList.get(i).getGoods().get(j).getGoodsNum());
                    float money = Float.parseFloat(mainArrayList.get(i).getGoods().get(j).getGoodsPrice()) * count;
                    countInt += count;
                    moneyFloat += money;
                    cartIdString += cartId + "|" + count + ",";
                }
            }
        }
        if (!TextUtils.isEmpty(cartIdString)) {
            balanceTextView.setEnabled(true);
            cartIdString = cartIdString.substring(0, cartIdString.length() - 1);
        } else {
            balanceTextView.setEnabled(false);
        }

        String temp = "共 <font color='#FF0000'>" + countInt + "</font> 件，" + "总计：" + "<font color='#FF0000'>￥" + moneyFloat + " 元</font>";
        moneyTextView.setText(Html.fromHtml(temp));
    }
}