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
import org.cloud.panzer.bean.VoucherStoreBean;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * FileName: VoucherStoreListAdapter
 * Author: Admin
 * Date: 2020/11/11 14:16
 * Description: VoucherStoreListAdapter
 */
public class VoucherStoreListAdapter extends RecyclerView.Adapter<VoucherStoreListAdapter.ViewHolder> {

    private final ArrayList<VoucherStoreBean> arrayList;
    private OnItemClickListener onItemClickListener = null;

    public VoucherStoreListAdapter(ArrayList<VoucherStoreBean> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_voucher_store, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int positionInt = position;
        final VoucherStoreBean voucherStoreBean = this.arrayList.get(position);
        holder.titleTextView.setText("面额￥");
        holder.titleTextView.append(voucherStoreBean.getVoucherTPrice());
        holder.titleTextView.append("元");
        holder.limitTextView.setText("需消费￥");
        holder.limitTextView.append(voucherStoreBean.getVoucherTPrice());
        holder.limitTextView.append("使用");
        holder.timeTextView.setText(voucherStoreBean.getVoucherTEndDateText());
        holder.timeTextView.append("前可用");
        holder.mainRelativeLayout.setOnClickListener(v->{
            if(onItemClickListener!=null) {
                onItemClickListener.onClick(positionInt, voucherStoreBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position, VoucherStoreBean voucherStoreBean);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.limitTextView)
        public AppCompatTextView limitTextView;
        @BindView(R.id.mainRelativeLayout)
        public RelativeLayout mainRelativeLayout;
        @BindView(R.id.timeTextView)
        public AppCompatTextView timeTextView;
        @BindView(R.id.titleTextView)
        public AppCompatTextView titleTextView;

        private ViewHolder(View view) {
            super(view);
        }
    }
}
