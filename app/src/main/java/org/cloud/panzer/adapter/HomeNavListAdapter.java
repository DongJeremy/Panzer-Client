package org.cloud.panzer.adapter;

import android.app.Activity;
import android.util.Log;
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
import org.cloud.panzer.bean.HomeBean;

import java.util.List;

import butterknife.BindView;

public class HomeNavListAdapter extends RecyclerView.Adapter<HomeNavListAdapter.ViewHolder> {

    private final List<HomeBean.HomeNavBean.ItemBean> mList;
    private final Activity activity;

    public HomeNavListAdapter(Activity activity, List<HomeBean.HomeNavBean.ItemBean> mList) {
        this.mList = mList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_home_nav, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HomeBean.HomeNavBean.ItemBean bean = mList.get(position);
        BaseImageLoader.getInstance().display(bean.getImage(), holder.mainImageView);
        holder.mainTextView.setText(bean.getWord());
        holder.mainLinearLayout.setOnClickListener(v -> {
            Log.e("TAG", bean.getType());
            App.getInstance().startTypeValue(this.activity, bean.getType(), bean.getData());
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.mainImageView)
        AppCompatImageView mainImageView;
        @BindView(R.id.mainLinearLayout)
        LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.mainTextView)
        AppCompatTextView mainTextView;

        public ViewHolder(@NonNull View view) {
            super(view);
        }
    }
}
