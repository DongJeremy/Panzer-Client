package org.cloud.panzer.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.HomeBean;

import java.util.ArrayList;

import butterknife.BindView;

public class Home3ListAdapter extends RecyclerView.Adapter<Home3ListAdapter.ViewHolder> {

    private final Activity activity;
    private final ArrayList<HomeBean.Home3Bean.ItemBean> arrayList;

    Home3ListAdapter(Activity activity, ArrayList<HomeBean.Home3Bean.ItemBean> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final HomeBean.Home3Bean.ItemBean bean = arrayList.get(position);
        BaseImageLoader.getInstance().display(bean.getImage(), holder.mainImageView);
        holder.mainLinearLayout.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getType(), bean.getData()));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.item_list_home_3, group, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.mainLinearLayout)
        LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.mainImageView)
        AppCompatImageView mainImageView;

        private ViewHolder(View view) {
            super(view);
        }
    }
}