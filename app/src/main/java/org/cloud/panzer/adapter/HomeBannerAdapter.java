package org.cloud.panzer.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.util.BannerUtils;

import org.cloud.core.base.BaseImageLoader;
import org.cloud.panzer.R;

import java.util.List;

public class HomeBannerAdapter extends BannerAdapter<String, HomeBannerAdapter.BannerViewHolder> {

    public HomeBannerAdapter(List<String> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = (ImageView) BannerUtils.getView(parent, R.layout.item_home_banner);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, String data, int position, int size) {
        BaseImageLoader.getInstance().display(data, holder.imageView);
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public BannerViewHolder(@NonNull View view) {
            super(view);
            this.imageView = (ImageView) view;
        }
    }
}
