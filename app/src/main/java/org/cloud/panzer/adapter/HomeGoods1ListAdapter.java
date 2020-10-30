package org.cloud.panzer.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.PanzerApplication;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.HomeBean;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeGoods1ListAdapter extends RecyclerView.Adapter<HomeGoods1ListAdapter.ViewHolder> {
    private final Activity activity;
    private final ArrayList<HomeBean.Goods1Bean.ItemBean> arrayList;

    HomeGoods1ListAdapter(Activity activity, ArrayList<HomeBean.Goods1Bean.ItemBean> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final HomeBean.Goods1Bean.ItemBean bean = arrayList.get(position);

        int width = BaseApplication.getInstance().getWidth() / 2 - 16;
        BaseImageLoader.getInstance().displayRadius(bean.getGoodsImage(), holder.mainImageView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
        holder.mainImageView.setLayoutParams(layoutParams);
        holder.nameTextView.setText(bean.getGoodsName());
        holder.moneyTextView.setText("￥");
        holder.moneyTextView.append(bean.getXianshiPrice());

        holder.mainRelativeLayout.setOnClickListener(view -> ((PanzerApplication) PanzerApplication.getInstance()).startGoods(activity, bean.getGoodsId()));

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.item_list_home_goods1, group, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.mainRelativeLayout)
        RelativeLayout mainRelativeLayout;
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
