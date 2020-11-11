package org.cloud.panzer.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.GoodsBean;

import java.util.ArrayList;

import butterknife.BindView;

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.ViewHolder> {

    private final boolean isGridModel;
    private final ArrayList<GoodsBean> arrayList;
    private OnItemClickListener onItemClickListener = null;

    public GoodsListAdapter(ArrayList<GoodsBean> arrayList, boolean isGridModel) {
        this.isGridModel = isGridModel;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (this.isGridModel) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_goods, viewGroup, false));
        } else {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_goods_ver, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final int positionInt = position;
        final GoodsBean goodsBean = arrayList.get(position);

        int width = (App.getInstance().getWidth() / 2) - 16;
        BaseImageLoader.getInstance().displayRadius(goodsBean.getGoodsImageUrl(), holder.mainImageView);
        ViewGroup.LayoutParams layoutParams = holder.mainImageView.getLayoutParams();
        if (this.isGridModel) {
            layoutParams.width = width;
            layoutParams.height = width;
        }
        holder.mainImageView.setLayoutParams(layoutParams);
        holder.nameTextView.setText(goodsBean.getGoodsName());
        holder.descTextView.setText(goodsBean.getGoodsJingle());
        int i2 = 0;
        holder.descTextView.setVisibility(TextUtils.isEmpty(goodsBean.getGoodsJingle()) ? View.GONE : View.VISIBLE);
        holder.saleTextView.setText("销量：");
        holder.saleTextView.append(goodsBean.getGoodsSalenum());
        if (!TextUtils.isEmpty(goodsBean.getGradePrice())) {
            holder.moneyTextView.setText("￥");
            holder.moneyTextView.append(goodsBean.getGoodsAuthprice());
            holder.mobileTextView.setVisibility(View.VISIBLE);
            holder.mobileTextView.setText("佣金 ￥");
            holder.mobileTextView.append(goodsBean.getGradePrice());
        } else if (!TextUtils.isEmpty(goodsBean.getHhrPrice())) {
            holder.moneyTextView.setText("￥");
            holder.moneyTextView.append(goodsBean.getGoodsAuthprice());
            holder.mobileTextView.setVisibility(View.VISIBLE);
            holder.mobileTextView.setText("直减 ￥");
            holder.mobileTextView.append(goodsBean.getHhrPrice());
        } else {
            holder.moneyTextView.setText("￥");
            holder.moneyTextView.append(goodsBean.getGoodsPrice());
            holder.mobileTextView.setVisibility(View.GONE);
            holder.mobileTextView.setVisibility(goodsBean.getSoleFlag().equals("true") ? View.VISIBLE : View.GONE);
            holder.mobileTextView.setText(goodsBean.getSoleFlag().equals("true") ? "手机专享" : "");
        }
        if (goodsBean.getIsOwnShop() == null) {
            holder.ownShopTextView.setVisibility(View.GONE);
        } else {
            AppCompatTextView access$600 = holder.ownShopTextView;
            if (!goodsBean.getIsOwnShop().equals("1")) {
                i2 = 8;
            }
            access$600.setVisibility(i2);
        }
        if (this.isGridModel) {
            holder.descTextView.setVisibility(View.GONE);
        }
        holder.cartImageView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onCart(positionInt, goodsBean);
            }
        });
        holder.moreImageView.setOnClickListener(v -> {
//            MemberFavoritesModel.get().favoritesInfo(goodsBean.getGoodsId(), new BaseHttpListener() {
//                public void onSuccess(BaseBean baseBean) {
//                    if (viewHolder.favoritesTextView.getVisibility() == 0) {
//                        viewHolder.favoritesTextView.setVisibility(8);
//                        BaseAnimClient.objectAnimator(viewHolder.favoritesTextView, BaseAnimClient.ALPHA, 1.0f, 0.0f);
//                    } else {
//                        viewHolder.favoritesTextView.setVisibility(0);
//                        BaseAnimClient.objectAnimator(viewHolder.favoritesTextView, BaseAnimClient.ALPHA, 0.0f, 1.0f);
//                    }
//                    if (baseBean.getDatas().equals("null") || baseBean.getDatas().equals("[]")) {
//                        viewHolder.favoritesTextView.setText("收藏");
//                        viewHolder.favoritesTextView.setCompoundDrawablesWithIntrinsicBounds(0, (int) R.drawable.ic_favorite_white, 0, 0);
//                        return;
//                    }
//                    viewHolder.favoritesTextView.setText("已收藏");
//                    viewHolder.favoritesTextView.setCompoundDrawablesWithIntrinsicBounds(0, (int) R.drawable.ic_favorite_press, 0, 0);
//                }
//
//                public void onFailure(String str) {
//                    BaseToast.get().show(str);
//                }
//            });
        });
        holder.favoritesTextView.setOnClickListener(v -> {
//            if (holder.favoritesTextView.getText().toString().equals("收藏")) {
//                MemberFavoritesModel.get().favoritesAdd(goodsBean.getGoodsId(), new BaseHttpListener() {
//                    public void onSuccess(BaseBean baseBean) {
//                        viewHolder.favoritesTextView.setText("已收藏");
//                        BaseToast.get().show("收藏成功");
//                        viewHolder.favoritesTextView.setCompoundDrawablesWithIntrinsicBounds(0, (int) R.drawable.ic_favorite_press, 0, 0);
//                        if (viewHolder.favoritesTextView.getVisibility() == 0) {
//                            viewHolder.favoritesTextView.setVisibility(8);
//                            BaseAnimClient.objectAnimator(viewHolder.favoritesTextView, BaseAnimClient.ALPHA, 1.0f, 0.0f);
//                        }
//                    }
//
//                    public void onFailure(String str) {
//                        BaseToast.get().show(str);
//                    }
//                });
//            } else {
//                MemberFavoritesModel.get().favoritesDel(goodsBean.getGoodsId(), new BaseHttpListener() {
//                    public void onSuccess(BaseBean baseBean) {
//                        viewHolder.favoritesTextView.setText("收藏");
//                        BaseToast.get().show("取消收藏成功");
//                        viewHolder.favoritesTextView.setCompoundDrawablesWithIntrinsicBounds(0, (int) R.drawable.ic_favorite_white, 0, 0);
//                        if (viewHolder.favoritesTextView.getVisibility() == 0) {
//                            viewHolder.favoritesTextView.setVisibility(8);
//                            BaseAnimClient.objectAnimator(viewHolder.favoritesTextView, BaseAnimClient.ALPHA, 1.0f, 0.0f);
//                        }
//                    }
//
//                    public void onFailure(String str) {
//                        BaseToast.get().show(str);
//                    }
//                });
//            }
        });
        holder.mainRelativeLayout.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, goodsBean);
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
        void onCart(int position, GoodsBean goodsBean);

        void onClick(int position, GoodsBean goodsBean);

        //void onFavoritesClick(int position, GoodsBean goodsBean);
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.cartImageView)
        AppCompatImageView cartImageView;
        @BindView(R.id.descTextView)
        AppCompatTextView descTextView;
        @BindView(R.id.favoritesTextView)
        AppCompatTextView favoritesTextView;
        @BindView(R.id.mainImageView)
        AppCompatImageView mainImageView;
        @BindView(R.id.mainRelativeLayout)
        RelativeLayout mainRelativeLayout;
        @BindView(R.id.mobileTextView)
        AppCompatTextView mobileTextView;
        @BindView(R.id.moneyTextView)
        AppCompatTextView moneyTextView;
        @BindView(R.id.moreImageView)
        AppCompatImageView moreImageView;
        @BindView(R.id.nameTextView)
        AppCompatTextView nameTextView;
        @BindView(R.id.ownShopTextView)
        AppCompatTextView ownShopTextView;
        @BindView(R.id.saleTextView)
        AppCompatTextView saleTextView;

        private ViewHolder(View view) {
            super(view);
        }
    }
}