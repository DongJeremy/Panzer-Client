package org.cloud.panzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.GoodsCommendBean;

import java.util.ArrayList;

import butterknife.BindView;

public class GoodsCommendListAdapter extends RecyclerView.Adapter<GoodsCommendListAdapter.ViewHolder> {

    private final ArrayList<GoodsCommendBean> arrayList;
    private OnItemClickListener onItemClickListener;

    public GoodsCommendListAdapter(ArrayList<GoodsCommendBean> arrayList) {
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
        final GoodsCommendBean bean = arrayList.get(position);

        BaseImageLoader.getInstance().displayRadius(bean.getGoodsImageUrl(), holder.mainImageView);
        holder.nameTextView.setText(bean.getGoodsName());
        holder.moneyTextView.setText("￥");
        holder.moneyTextView.append(bean.getGoodsSalePrice());
        ViewGroup.LayoutParams layoutParams = holder.mainImageView.getLayoutParams();
        layoutParams.height = App.getInstance().getWidth() / 4 - App.getInstance().dipToPx(16);

        holder.mainImageView.setLayoutParams(layoutParams);

        holder.mainLinearLayout.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, bean);
            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        View view;
        view = LayoutInflater.from(group.getContext()).inflate(R.layout.item_list_goods_commend, group, false);
        return new ViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(int position, GoodsCommendBean bean);
    }

    class ViewHolder extends BaseViewHolder {
        @BindView(R.id.mainLinearLayout)
        LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.mainImageView)
        AppCompatImageView mainImageView;
        @BindView(R.id.nameTextView)
        AppCompatTextView nameTextView;
        @BindView(R.id.moneyTextView)
        AppCompatTextView moneyTextView;

        private ViewHolder(View view) {
            super(view);
        }

    }
}
