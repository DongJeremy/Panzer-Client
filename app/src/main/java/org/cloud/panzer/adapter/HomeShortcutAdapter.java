package org.cloud.panzer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.PanzerApplication;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.HomeBean;
import org.cloud.panzer.mvp.model.HomeInfoModel;

import java.util.List;

import butterknife.BindView;

public class HomeShortcutAdapter extends RecyclerView.Adapter<HomeShortcutAdapter.ViewHolder> {

    private List<HomeBean.ShortcutBean> mList;
    private final Context mContext;

    public HomeShortcutAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(List<HomeBean.ShortcutBean> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_home_shortcut, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HomeBean.ShortcutBean bean = mList.get(position);
//        final String squareType = bean.getType();
//        final String squareData = bean.getData();
        holder.mAppCompatTextView.setText(bean.iconName);
        int dp28 = PanzerApplication.getInstance().dipToPx(28);
        holder.mAppCompatImageView.setBackgroundDrawable(PanzerApplication.getInstance().getGradientDrawable(dp28, Color.parseColor(bean.iconColor)));
        Glide.with(mContext).load(bean.image).apply(new RequestOptions().override(dp28, dp28)).into(holder.mAppCompatImageView);
        //BaseImageLoader.get().display(bean.getImage(), dp28, dp28, holder.mAppCompatImageView);
        //oneLinearLayout.setOnClickListener(view -> BaseApplication.get().startTypeValue(getActivity(), squareType, squareData));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ViewHolder extends BaseViewHolder {

        @BindView(R.id.itemImageView)
        AppCompatImageView mAppCompatImageView;

        @BindView(R.id.itemTextView)
        AppCompatTextView mAppCompatTextView;

        public ViewHolder(@NonNull View view) {
            super(view);
        }
    }
}
