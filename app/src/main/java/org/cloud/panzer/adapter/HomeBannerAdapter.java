package org.cloud.panzer.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import org.cloud.panzer.R;
import org.cloud.panzer.mvp.model.HomeInfoModel;

import java.util.List;

public class HomeBannerAdapter extends BannerAdapter<HomeInfoModel.HomeInfo.HomeBanner, HomeBannerAdapter.BannerViewHolder> {

    public HomeBannerAdapter(List<HomeInfoModel.HomeInfo.HomeBanner> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.item_home_banner);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, HomeInfoModel.HomeInfo.HomeBanner data, int position, int size) {
        Glide.with(holder.itemView)
                .load(data.getImage())
                .thumbnail(Glide.with(holder.itemView).load(R.mipmap.banner_loading))
//                .apply(RequestOptions.bitmapTransform(new RoundedCorners(30)))
                .into(holder.imageView);
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public BannerViewHolder(@NonNull View view) {
            super(view);
            this.imageView = (ImageView) view;
        }
    }
}
