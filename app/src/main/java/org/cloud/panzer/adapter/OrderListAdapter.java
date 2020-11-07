package org.cloud.panzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.OrderBean;

import java.util.ArrayList;

import butterknife.BindView;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    private final ArrayList<OrderBean> arrayList;
    private OnItemClickListener onItemClickListener = null;

    public OrderListAdapter(ArrayList<OrderBean> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_order, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int positionInt = position;
        final OrderBean bean = arrayList.get(position);
        final StoreOrderListAdapter storeOrderListAdapter;

        boolean showPay = false;
        for (int i = 0; i < bean.getOrderList().size(); i++) {
            if (bean.getOrderList().get(i).getOrderState().equals("10")) {
                showPay = true;
            }
        }

        if (bean.getPayAmount() == null) {
            holder.payTextView.setVisibility(View.GONE);
        } else {
            holder.payTextView.setText("订单支付：￥");
            holder.payTextView.setVisibility(View.VISIBLE);
            holder.payTextView.append(bean.getPayAmount());
        }

        storeOrderListAdapter = new StoreOrderListAdapter(bean.getOrderList());
        App.getInstance().setRecyclerView(App.getInstance(), holder.mainRecyclerView, storeOrderListAdapter);
        holder.payTextView.setVisibility(showPay ? View.VISIBLE : View.GONE);

        storeOrderListAdapter.setOnItemClickListener(new StoreOrderListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, OrderBean.OrderListBean orderListBean) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(positionInt, position, orderListBean);
                }
            }

            @Override
            public void onOption(int position, OrderBean.OrderListBean orderListBean) {
                if (onItemClickListener != null) {
                    onItemClickListener.onOption(positionInt, position, orderListBean);
                }
            }

            @Override
            public void onOpera(int position, OrderBean.OrderListBean orderListBean) {
                if (onItemClickListener != null) {
                    onItemClickListener.onOpera(positionInt, position, orderListBean);
                }
            }

            @Override
            public void onClickGoods(int position, int itemPosition, OrderBean.OrderListBean.ExtendOrderGoodsBean extendOrderGoodsBean) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemGoodsClick(position, itemPosition, bean.getOrderList().get(position));
                }
            }
        });

        holder.payTextView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onPay(positionInt, bean);
            }
        });

        holder.mainLinearLayout.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, bean);
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
        void onPay(int position, OrderBean bean);

        void onClick(int position, OrderBean bean);

        void onItemClick(int position, int itemPosition, OrderBean.OrderListBean bean);

        void onItemGoodsClick(int position, int itemPosition, OrderBean.OrderListBean bean);

        void onOption(int position, int itemPosition, OrderBean.OrderListBean bean);

        void onOpera(int position, int itemPosition, OrderBean.OrderListBean bean);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.mainLinearLayout)
        LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.payTextView)
        AppCompatTextView payTextView;
        @BindView(R.id.mainRecyclerView)
        RecyclerView mainRecyclerView;

        private ViewHolder(View view) {
            super(view);
        }
    }
}