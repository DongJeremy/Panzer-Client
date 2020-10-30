package org.cloud.panzer.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.HomeBean;

import java.util.ArrayList;

import butterknife.BindView;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    private final Activity activity;
    private final ArrayList<HomeBean> arrayList;
    private OnItemClickListener onItemClickListener;

    public HomeListAdapter(Activity activity, ArrayList<HomeBean> arrayList) {
        this.activity = activity;
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
        final HomeBean bean = arrayList.get(position);

        holder.home1ImageView.setVisibility(View.GONE);
        holder.home2LinearLayout.setVisibility(View.GONE);
        holder.mainRecyclerView.setVisibility(View.GONE);
        holder.home4LinearLayout.setVisibility(View.GONE);
        holder.home5LinearLayout.setVisibility(View.GONE);

        String title = "";
        switch (bean.getType()) {
            case "home1":
                title = bean.getHome1Bean().getTitle();
                holder.home1ImageView.setVisibility(View.VISIBLE);
                BaseImageLoader.getInstance().display(bean.getHome1Bean().getImage(), holder.home1ImageView);
                holder.home1ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome1Bean().getType(), bean.getHome1Bean().getData()));
                break;
            case "home2":
                title = bean.getHome2Bean().getTitle();
                holder.home2LinearLayout.setVisibility(View.VISIBLE);
                BaseImageLoader.getInstance().display(bean.getHome2Bean().getSquareImage(), holder.home21ImageView);
                BaseImageLoader.getInstance().display(bean.getHome2Bean().getRectangle1Image(), holder.home22ImageView);
                BaseImageLoader.getInstance().display(bean.getHome2Bean().getRectangle2Image(), holder.home23ImageView);
                holder.home21ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome2Bean().getSquareType(), bean.getHome2Bean().getSquareData()));
                holder.home22ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome2Bean().getRectangle1Type(), bean.getHome2Bean().getRectangle1Data()));
                holder.home23ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome2Bean().getRectangle2Type(), bean.getHome2Bean().getRectangle2Data()));
                break;
            case "home3":
                title = bean.getHome3Bean().getTitle();
                holder.mainRecyclerView.setVisibility(View.VISIBLE);
                Home3ListAdapter home3ListAdapter = new Home3ListAdapter(activity, bean.getHome3Bean().getItem());
                BaseApplication.getInstance().setRecyclerView(BaseApplication.getInstance().getApplicationContext(), holder.mainRecyclerView, home3ListAdapter);
                holder.mainRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
                break;
            case "home4":
                title = bean.getHome4Bean().getTitle();
                holder.home4LinearLayout.setVisibility(View.VISIBLE);
                BaseImageLoader.getInstance().display(bean.getHome4Bean().getSquareImage(), holder.home41ImageView);
                BaseImageLoader.getInstance().display(bean.getHome4Bean().getRectangle1Image(), holder.home42ImageView);
                BaseImageLoader.getInstance().display(bean.getHome4Bean().getRectangle2Image(), holder.home43ImageView);
                holder.home41ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome4Bean().getSquareType(), bean.getHome4Bean().getSquareData()));
                holder.home42ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome4Bean().getRectangle1Type(), bean.getHome4Bean().getRectangle1Data()));
                holder.home43ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome4Bean().getRectangle2Type(), bean.getHome4Bean().getRectangle2Data()));
                break;
            case "home5":
                title = bean.getHome5Bean().getTitle();
                holder.home5LinearLayout.setVisibility(View.VISIBLE);
                holder.subTitleTextView.setVisibility(TextUtils.isEmpty(bean.getHome5Bean().getStitle()) ? View.GONE : View.VISIBLE);
                holder.subTitleTextView.setText("———— ");
                holder.subTitleTextView.append(bean.getHome5Bean().getStitle() + " ————");
                BaseImageLoader.getInstance().display(bean.getHome5Bean().getSquareImage(), holder.home51ImageView);
                BaseImageLoader.getInstance().display(bean.getHome5Bean().getRectangle1Image(), holder.home52ImageView);
                BaseImageLoader.getInstance().display(bean.getHome5Bean().getRectangle2Image(), holder.home53ImageView);
                BaseImageLoader.getInstance().display(bean.getHome5Bean().getRectangle3Image(), holder.home54ImageView);
                holder.home51ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome5Bean().getSquareType(), bean.getHome5Bean().getSquareData()));
                holder.home52ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome5Bean().getRectangle1Type(), bean.getHome5Bean().getRectangle1Data()));
                holder.home53ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome5Bean().getRectangle2Type(), bean.getHome5Bean().getRectangle2Data()));
                holder.home54ImageView.setOnClickListener(view -> BaseApplication.getInstance().startTypeValue(activity, bean.getHome5Bean().getRectangle3Type(), bean.getHome5Bean().getRectangle3Data()));
                break;
            case "goods":
                title = bean.getGoodsBean().getTitle();
                holder.mainRecyclerView.setVisibility(View.VISIBLE);
                HomeGoodsListAdapter homeGoodsListAdapter = new HomeGoodsListAdapter(activity, bean.getGoodsBean().getItem());
                BaseApplication.getInstance().setRecyclerView(BaseApplication.getInstance(), holder.mainRecyclerView, homeGoodsListAdapter);
                holder.mainRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
                holder.mainRecyclerView.setPadding(BaseApplication.getInstance().dipToPx(2), 0, BaseApplication.getInstance().dipToPx(2), 0);
                break;
            case "goods1":
                title = bean.getGoods1Bean().getTitle();
                holder.mainRecyclerView.setVisibility(View.VISIBLE);
                HomeGoods1ListAdapter homeGoods1ListAdapter = new HomeGoods1ListAdapter(activity, bean.getGoods1Bean().getItem());
                BaseApplication.getInstance().setRecyclerView(BaseApplication.getInstance(), holder.mainRecyclerView, homeGoods1ListAdapter);
                holder.mainRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
                holder.mainRecyclerView.setPadding(BaseApplication.getInstance().dipToPx(2), 0, BaseApplication.getInstance().dipToPx(2), 0);
                break;
            case "goods2":
                title = bean.getGoods2Bean().getTitle();
                holder.mainRecyclerView.setVisibility(View.VISIBLE);
                HomeGoods2ListAdapter homeGoods2ListAdapter = new HomeGoods2ListAdapter(activity, bean.getGoods2Bean().getItem());
                BaseApplication.getInstance().setRecyclerView(BaseApplication.getInstance(), holder.mainRecyclerView, homeGoods2ListAdapter);
                holder.mainRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
                holder.mainRecyclerView.setPadding(BaseApplication.getInstance().dipToPx(2), 0, BaseApplication.getInstance().dipToPx(2), 0);
                break;
        }

        holder.titleTextView.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
        holder.titleTextView.setText("———— ");
        holder.titleTextView.append(title + " ————");

        holder.mainLinearLayout.setOnClickListener(view -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(positionInt, bean);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        View view = LayoutInflater.from(group.getContext()).inflate(R.layout.item_list_home, group, false);
        return new ViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onClick(int position, HomeBean bean);
    }

    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.mainLinearLayout)
        LinearLayoutCompat mainLinearLayout;
        @BindView(R.id.titleTextView)
        AppCompatTextView titleTextView;
        @BindView(R.id.subTitleTextView)
        AppCompatTextView subTitleTextView;
        //Home1
        @BindView(R.id.home1ImageView)
        AppCompatImageView home1ImageView;
        //Home2
        @BindView(R.id.home2LinearLayout)
        LinearLayoutCompat home2LinearLayout;
        @BindView(R.id.home21ImageView)
        AppCompatImageView home21ImageView;
        @BindView(R.id.home22ImageView)
        AppCompatImageView home22ImageView;
        @BindView(R.id.home23ImageView)
        AppCompatImageView home23ImageView;
        //Home4
        @BindView(R.id.home4LinearLayout)
        LinearLayoutCompat home4LinearLayout;
        @BindView(R.id.home41ImageView)
        AppCompatImageView home41ImageView;
        @BindView(R.id.home42ImageView)
        AppCompatImageView home42ImageView;
        @BindView(R.id.home43ImageView)
        AppCompatImageView home43ImageView;
        //Home5
        @BindView(R.id.home5LinearLayout)
        LinearLayoutCompat home5LinearLayout;
        @BindView(R.id.home51ImageView)
        AppCompatImageView home51ImageView;
        @BindView(R.id.home52ImageView)
        AppCompatImageView home52ImageView;
        @BindView(R.id.home53ImageView)
        AppCompatImageView home53ImageView;
        @BindView(R.id.home54ImageView)
        AppCompatImageView home54ImageView;
        //Common
        @BindView(R.id.mainRecyclerView)
        RecyclerView mainRecyclerView;

        private ViewHolder(View view) {
            super(view);
        }

    }
}
