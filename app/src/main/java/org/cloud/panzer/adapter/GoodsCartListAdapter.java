package org.cloud.panzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseViewHolder;
import org.cloud.core.utils.ImageUtils;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.CartBean;

import java.util.ArrayList;

import butterknife.BindView;

class GoodsCartListAdapter extends RecyclerView.Adapter<GoodsCartListAdapter.ViewHolder> {

    private final ArrayList<CartBean.GoodsBean> arrayList;
    private OnItemClickListener onItemClickListener;

    GoodsCartListAdapter(ArrayList<CartBean.GoodsBean> arrayList) {
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
        final CartBean.GoodsBean bean = arrayList.get(position);

        holder.mainCheckBox.setChecked(bean.isCheck());
        ImageUtils.getInstance().displayRadius(bean.getGoodsImageUrl(), holder.mainImageView);
        holder.nameTextView.setText(bean.getGoodsName());
        holder.moneyTextView.setText("￥");
        holder.moneyTextView.append(bean.getGoodsPrice());
        holder.numberEditText.setText(bean.getGoodsNum());
        holder.mobileTextView.setVisibility(View.GONE);
        holder.activityTextView.setVisibility(View.GONE);
        if (bean.isIfsole()) {
            holder.mobileTextView.setVisibility(View.VISIBLE);
        }
        if (bean.isIfxianshi()) {
            holder.activityTextView.setText("限时折扣");
            holder.activityTextView.setVisibility(View.VISIBLE);
        }
        if (bean.isIfgroupbuy()) {
            holder.activityTextView.setText("抢购");
            holder.activityTextView.setVisibility(View.VISIBLE);
        }
        holder.mainCheckBox.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onCheck(positionInt, holder.mainCheckBox.isChecked(), bean);
            }
        });
        holder.deleteImageView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onDelete(positionInt, bean);
            }
        });
        holder.subTextView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onSub(positionInt, bean);
            }
        });
        holder.addTextView.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onAdd(positionInt, bean);
            }
        });

        holder.mainRelativeLayout.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, bean);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        return new ViewHolder(LayoutInflater.from(group.getContext()).inflate(R.layout.item_list_goods_cart, group, false));
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(int position, CartBean.GoodsBean bean);
        void onDelete(int position, CartBean.GoodsBean bean);
        void onAdd(int position, CartBean.GoodsBean bean);
        void onSub(int position, CartBean.GoodsBean bean);
        void onCheck(int position, boolean isCheck, CartBean.GoodsBean bean);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.mainRelativeLayout)
        RelativeLayout mainRelativeLayout;
        @BindView(R.id.mainCheckBox)
        AppCompatCheckBox mainCheckBox;
        @BindView(R.id.mainImageView)
        AppCompatImageView mainImageView;
        @BindView(R.id.nameTextView)
        AppCompatTextView nameTextView;
        @BindView(R.id.moneyTextView)
        AppCompatTextView moneyTextView;
        @BindView(R.id.mobileTextView)
        AppCompatTextView mobileTextView;
        @BindView(R.id.activityTextView)
        AppCompatTextView activityTextView;
        @BindView(R.id.deleteImageView)
        AppCompatImageView deleteImageView;
        @BindView(R.id.addTextView)
        AppCompatTextView addTextView;
        @BindView(R.id.numberEditText)
        AppCompatEditText numberEditText;
        @BindView(R.id.subTextView)
        AppCompatTextView subTextView;

        private ViewHolder(View view) {
            super(view);
        }

    }
}
