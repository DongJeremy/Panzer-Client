package org.cloud.panzer.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseViewHolder;
import org.cloud.core.utils.ImageUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.OrderBean;

import java.util.ArrayList;

import butterknife.BindView;

public class StoreOrderListAdapter extends RecyclerView.Adapter<StoreOrderListAdapter.ViewHolder> {

    private final ArrayList<OrderBean.OrderListBean> arrayList;
    private OnItemClickListener onItemClickListener = null;

    public StoreOrderListAdapter(ArrayList<OrderBean.OrderListBean> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_store_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int positionInt = position;
        final OrderBean.OrderListBean bean = arrayList.get(position);
        final GoodsOrderListAdapter goodsOrderListAdapter;

        goodsOrderListAdapter = new GoodsOrderListAdapter(bean.getExtendOrderGoods());
        App.getInstance().setRecyclerView(App.getInstance(), holder.mainRecyclerView, goodsOrderListAdapter);

        if (bean.getZengpinList().size() == 0) {
            holder.zenPinView.setVisibility(View.GONE);
            holder.zengPinLinearLayout.setVisibility(View.GONE);
        } else {
            holder.zenPinView.setVisibility(View.VISIBLE);
            holder.zengPinLinearLayout.setVisibility(View.VISIBLE);
            holder.zengPinDescTextView.setText(bean.getZengpinList().get(0).getGoodsName());
            ImageUtils.getInstance().display(bean.getZengpinList().get(0).getGoodsImageUrl(), holder.zengPinGoodsImageView);
        }

        holder.storeNameTextView.setText(bean.getStoreName());
        holder.storeDescTextView.setText(bean.getStateDesc());

        int count = 0;
        for (int i = 0; i < bean.getExtendOrderGoods().size(); i++) {
            count += Integer.parseInt(bean.getExtendOrderGoods().get(i).getGoodsNum());
        }
        String temp = "共 <font color='#FF0000'>" + count + " </font>件商品，合计<font color='#FF0000'>￥" + bean.getOrderAmount() + "</font>（含运费：<font color='#FF0000'>￥" + bean.getShippingFee() + "</font>";
        if (bean.getPointsNumber() != null) {
            temp = temp + " | <font color='#FF0000'>" + bean.getPointsNumber() + "</font>积分抵扣<font color='#FF0000'>￥" + bean.getPointsMoney() + "</font>）";
        } else {
            temp = temp + "）";
        }
        holder.totalTextView.setText(Html.fromHtml(temp));

        holder.optionTextView.setVisibility(View.VISIBLE);
        holder.operaTextView.setVisibility(View.VISIBLE);

        switch (bean.getOrderState()) {
            case "0":
                holder.optionTextView.setText("订单详细");
                holder.operaTextView.setText("删除订单");
                break;
            case "10":
                holder.optionTextView.setText("订单详细");
                holder.operaTextView.setText("取消订单");
                break;
            case "20":
                if (bean.getLockState().equals("0")) {
                    holder.optionTextView.setText("订单详细");
                    holder.operaTextView.setText("申请退款");
                } else {
                    holder.storeDescTextView.append("（退货/款中）");
                    holder.optionTextView.setVisibility(View.GONE);
                    holder.operaTextView.setText("订单详细");
                }
                break;
            case "30":
                if (bean.getLockState().equals("0")) {
                    holder.optionTextView.setText("查看物流");
                    holder.operaTextView.setText("确认收货");
                } else {
                    holder.storeDescTextView.append("（退货/款中）");
                    holder.optionTextView.setText("查看物流");
                    holder.operaTextView.setText("订单详细");
                }
                break;
            case "40":
                if (bean.getEvaluationState().equals("1")) {
                    if (bean.getEvaluationAgainState().equals("1")) {
                        holder.optionTextView.setText("删除订单");
                        holder.operaTextView.setText("订单详细");
                    } else {
                        holder.optionTextView.setText("删除订单");
                        holder.operaTextView.setText("追加评价");
                    }
                } else {
                    holder.optionTextView.setText("订单详细");
                    holder.operaTextView.setText("订单评价");
                }
                break;
        }

        holder.optionTextView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onOption(positionInt, bean);
            }
        });

        holder.operaTextView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onOpera(positionInt, bean);
            }
        });

        holder.mainLinearLayout.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, bean);
            }
        });

        goodsOrderListAdapter.setOnItemClickListener((position1, extendOrderGoodsBean) -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClickGoods(positionInt, position1, extendOrderGoodsBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position, OrderBean.OrderListBean bean);

        void onOption(int position, OrderBean.OrderListBean bean);

        void onOpera(int position, OrderBean.OrderListBean bean);

        void onClickGoods(int position, int itemPosition, OrderBean.OrderListBean.ExtendOrderGoodsBean bean);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.mainLinearLayout)
        LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.storeNameTextView)
        AppCompatTextView storeNameTextView;
        @BindView(R.id.storeDescTextView)
        AppCompatTextView storeDescTextView;
        @BindView(R.id.mainRecyclerView)
        RecyclerView mainRecyclerView;
        @BindView(R.id.zengPinView)
        View zenPinView;
        @BindView(R.id.zengPinLinearLayout)
        LinearLayoutCompat zengPinLinearLayout;
        @BindView(R.id.zengPinDescTextView)
        AppCompatTextView zengPinDescTextView;
        @BindView(R.id.zengPinGoodsImageView)
        AppCompatImageView zengPinGoodsImageView;
        @BindView(R.id.totalTextView)
        AppCompatTextView totalTextView;
        @BindView(R.id.optionTextView)
        AppCompatTextView optionTextView;
        @BindView(R.id.operaTextView)
        AppCompatTextView operaTextView;

        private ViewHolder(View view) {
            super(view);
        }
    }
}