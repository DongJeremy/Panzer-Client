package org.cloud.panzer.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.R;
import org.cloud.panzer.ui.common.ImageFullScreenActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class GoodsDetailListAdapter extends RecyclerView.Adapter<GoodsDetailListAdapter.ViewHolder> {

    private final ArrayList<String> arrayList;

    public GoodsDetailListAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final String bean = arrayList.get(position);
        BaseImageLoader.getInstance().displayFitWidth(bean, holder.detailsImageView);
        holder.detailsImageView.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Intent intent = new Intent(v.getContext(), ImageFullScreenActivity.class);
                intent.putExtra("img", bean);
                v.getContext().startActivity(
                        intent,
                        ActivityOptions.makeSceneTransitionAnimation((Activity) v.getContext(), v, "sharedView").toBundle()
                );
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        return new ViewHolder(LayoutInflater.from(group.getContext()).inflate(R.layout.item_list_goods_detail, group, false));
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.detailsImageView)
        AppCompatImageView detailsImageView;

        private ViewHolder(View view) {
            super(view);
        }

    }
}
