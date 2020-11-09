package org.cloud.panzer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.CartBean;

import java.util.ArrayList;

import butterknife.BindView;

/**
* FileName: CartListAdapter
* Author: Admin
* Date: 2020/11/9 9:01
* Description: 购物车适配器
*/
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {

    private final ArrayList<CartBean> arrayList;

    public OnItemClickListener onItemClickListener = null;

    public CartListAdapter(ArrayList<CartBean> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_cart, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int positionInt = position;
        final CartBean bean = this.arrayList.get(position);
        final GoodsCartListAdapter goodsCartListAdapter;

        holder.storeNameTextView.setText(bean.getStoreName());
        holder.mainCheckBox.setChecked(bean.isCheck());
        goodsCartListAdapter = new GoodsCartListAdapter(bean.getGoods());
        App.getInstance().setRecyclerView(App.getInstance(), holder.mainRecyclerView, goodsCartListAdapter);

        if (bean.getMansong() == null || bean.getMansong().size() == 0) {
            holder.mansongLineView.setVisibility(View.GONE);
            holder.manSongLinearLayout.setVisibility(View.GONE);
        } else {
            holder.mansongLineView.setVisibility(View.VISIBLE);
            holder.manSongLinearLayout.setVisibility(View.VISIBLE);
            holder.manSongDescTextView.setText(bean.getMansong().get(0).getDesc());
            BaseImageLoader.getInstance().display(bean.getMansong().get(0).getUrl(), holder.manSongGoodsImageView);
        }

        goodsCartListAdapter.setOnItemClickListener(new GoodsCartListAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, CartBean.GoodsBean goodsBean) {
                if (onItemClickListener != null) {
                    onItemClickListener.onGoods(positionInt, position, goodsBean);
                }
            }

            @Override
            public void onDelete(int position, CartBean.GoodsBean goodsBean) {
                if (onItemClickListener != null) {
                    onItemClickListener.onGoodsDelete(positionInt, position, goodsBean);
                }
            }

            @Override
            public void onAdd(int position, CartBean.GoodsBean goodsBean) {
                if (onItemClickListener != null) {
                    onItemClickListener.onGoodsAdd(positionInt, position, goodsBean);
                }
            }

            @Override
            public void onSub(int position, CartBean.GoodsBean goodsBean) {
                if (onItemClickListener != null) {
                    onItemClickListener.onGoodsSub(positionInt, position, goodsBean);
                }
            }

            @Override
            public void onCheck(int position, boolean isCheck, CartBean.GoodsBean goodsBean) {
                if (onItemClickListener != null) {
                    onItemClickListener.onGoodsCheck(positionInt, position, isCheck, goodsBean);
                }
            }
        });

        holder.mainCheckBox.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onCheck(positionInt, holder.mainCheckBox.isChecked(), bean);
            }
        });

        holder.storeLinearLayout.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onStore(positionInt, bean);
            }
        });

        holder.mainLinearLayout.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, bean);
            }
        });
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    public interface OnItemClickListener {
        void onCheck(int position, boolean isCheck, CartBean cartBean);

        void onClick(int position, CartBean cartBean);

        void onGoods(int position, int positionGoods, CartBean.GoodsBean goodsBean);

        void onGoodsAdd(int position, int positionGoods, CartBean.GoodsBean goodsBean);

        void onGoodsCheck(int position, int positionGoods, boolean z, CartBean.GoodsBean goodsBean);

        void onGoodsDelete(int position, int positionGoods, CartBean.GoodsBean goodsBean);

        void onGoodsSub(int position, int positionGoods, CartBean.GoodsBean goodsBean);

        void onStore(int position, CartBean cartBean);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.mainCheckBox)
        public AppCompatCheckBox mainCheckBox;
        @BindView(R.id.mainLinearLayout)
        public LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.mainRecyclerView)
        public RecyclerView mainRecyclerView;
        @BindView(R.id.manSongDescTextView)
        public AppCompatTextView manSongDescTextView;
        @BindView(R.id.manSongGoodsImageView)
        public AppCompatImageView manSongGoodsImageView;
        @BindView(R.id.manSongLinearLayout)
        public LinearLayoutCompat manSongLinearLayout;
        @BindView(R.id.mansongLineView)
        public View mansongLineView;
        @BindView(R.id.storeLinearLayout)
        public LinearLayoutCompat storeLinearLayout;
        @BindView(R.id.storeNameTextView)
        public AppCompatTextView storeNameTextView;

        private ViewHolder(View view) {
            super(view);
        }
    }

}
