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
import org.cloud.panzer.bean.AddressBean;

import java.util.ArrayList;

import butterknife.BindView;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {

    private final ArrayList<AddressBean> arrayList;
    private OnItemClickListener onItemClickListener = null;

    public AddressListAdapter(ArrayList<AddressBean> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_address, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AddressBean addressBean = this.arrayList.get(position);
        holder.nameTextView.setText(addressBean.getTrueName());
        holder.mobileTextView.setText(addressBean.getMobPhone());
        holder.areaTextView.setText(addressBean.getAreaInfo());
        holder.areaTextView.setText(String.format(" %s", addressBean.getAddress()));
        holder.defaultTextView.setVisibility(addressBean.getIsDefault().equals("1") ? View.VISIBLE : View.GONE);
        holder.editTextView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onEdit(position, addressBean);
            }
        });
        holder.deleteTextView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDelete(position, addressBean);
            }
        });
        holder.mainRelativeLayout.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(position, addressBean);
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
        void onClick(int position, AddressBean addressBean);
        void onDelete(int position, AddressBean addressBean);
        void onEdit(int position, AddressBean addressBean);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.areaTextView)
        AppCompatTextView areaTextView;
        @BindView(R.id.defaultTextView)
        AppCompatTextView defaultTextView;
        @BindView(R.id.deleteTextView)
        AppCompatTextView deleteTextView;
        @BindView(R.id.editTextView)
        AppCompatTextView editTextView;
        @BindView(R.id.mainRelativeLayout)
        RelativeLayout mainRelativeLayout;
        @BindView(R.id.mobileTextView)
        AppCompatTextView mobileTextView;
        @BindView(R.id.nameTextView)
        AppCompatTextView nameTextView;

        private ViewHolder(View view) {
            super(view);
        }
    }
}
