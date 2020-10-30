package org.cloud.panzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.VoucherGoodsBean;

import java.util.ArrayList;

import butterknife.BindView;

public class VoucherGoodsListAdapter extends RecyclerView.Adapter<VoucherGoodsListAdapter.ViewHolder> {

    private final ArrayList<VoucherGoodsBean> arrayList;
    private OnItemClickListener onItemClickListener;

    public VoucherGoodsListAdapter(ArrayList<VoucherGoodsBean> arrayList) {
        this.arrayList = arrayList;
        this.onItemClickListener = null;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final int positionInt = position;
        final VoucherGoodsBean bean = arrayList.get(position);
        holder.titleTextView.setText("面额￥");
        holder.titleTextView.append(bean.getVoucherTPrice());
        holder.titleTextView.append("元");
        holder.limitTextView.setText("需消费￥");
        holder.limitTextView.append(bean.getVoucherTPrice());
        holder.limitTextView.append("使用");
        holder.timeTextView.setText(bean.getVoucherTEndDateText());
        holder.timeTextView.append("前可用");

        holder.mainRelativeLayout.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, bean);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.item_list_voucher_goods, group, false);
        return new ViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(int position, VoucherGoodsBean bean);
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.mainRelativeLayout)
        RelativeLayout mainRelativeLayout;
        @BindView(R.id.titleTextView)
        AppCompatTextView titleTextView;
        @BindView(R.id.limitTextView)
        AppCompatTextView limitTextView;
        @BindView(R.id.timeTextView)
        AppCompatTextView timeTextView;

        private ViewHolder(View view) {
            super(view);
        }

    }
}
