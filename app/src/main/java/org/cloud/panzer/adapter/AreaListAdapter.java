package org.cloud.panzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.AreaBean;

import java.util.ArrayList;

import butterknife.BindView;

public class AreaListAdapter extends RecyclerView.Adapter<AreaListAdapter.ViewHolder> {

    private final ArrayList<AreaBean> arrayList;
    private OnItemClickListener onItemClickListener = null;

    public AreaListAdapter(ArrayList<AreaBean> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_area, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int positionInt = position;
        final AreaBean bean = arrayList.get(position);

        holder.titleTextView.setText(bean.getAreaName());
        holder.mainLinearLayout.setOnClickListener(v -> {
            if(this.onItemClickListener!=null) {
                this.onItemClickListener.onClick(positionInt, bean);
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
        void onClick(int position, AreaBean areaBean);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.mainLinearLayout)
        LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.titleTextView)
        AppCompatTextView titleTextView;

        private ViewHolder(View view) {
            super(view);
        }
    }
}