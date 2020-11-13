package org.cloud.panzer.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.indicator.RectangleIndicator;
import com.youth.banner.util.BannerUtils;

import org.cloud.core.base.BaseImageLoader;
import org.cloud.core.base.BaseViewHolder;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.bean.ArticleBean;
import org.cloud.panzer.bean.HomeBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {

    private final Activity activity;
    private final ArrayList<HomeBean> arrayList;
    private final boolean isHome;

    public HomeListAdapter(Activity activity, ArrayList<HomeBean> arrayList, boolean isHome) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.isHome = isHome;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_home, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final HomeBean bean = arrayList.get(position);
        holder.mainBanner.setVisibility(View.GONE);
        holder.navLinearLayout.setVisibility(View.GONE);
        holder.noticeLinearLayout.setVisibility(View.GONE);
        holder.mainRecyclerView.setVisibility(View.GONE);
        holder.home1ImageView.setVisibility(View.GONE);

        switch (bean.getType()) {
            case "adv_list":
                holder.mainBanner.setVisibility(View.VISIBLE);
                List<String> imageList = new ArrayList<>();
                setBanner(holder.mainBanner, imageList, new HomeBannerAdapter(imageList));
                ArrayList<HomeBean.AdvListBean.ItemBean> advList = bean.getAdvListBean().getItem();
                if (advList.size() == 0) {
                    holder.mainBanner.setVisibility(View.GONE);
                } else {
                    holder.mainBanner.setVisibility(View.VISIBLE);
                    final List<String> typeList = new ArrayList<>();
                    final List<String> dataList = new ArrayList<>();
                    for (int i = 0; i < advList.size(); i++) {
                        imageList.add(advList.get(i).getImage());
                        typeList.add(advList.get(i).getType());
                        dataList.add(advList.get(i).getData());
                    }
                    holder.mainBanner.setOnBannerListener((data, position1) ->
                            App.getInstance().startTypeValue(activity, typeList.get(position1), dataList.get(position1))
                    );
                    holder.mainBanner.setDatas(imageList);
                    holder.mainBanner.start();
                }
                break;
            case "article":
                holder.noticeLinearLayout.setVisibility(View.VISIBLE);
                List<String> messages = new ArrayList<>();
                for (ArticleBean articleBean : bean.getArticleList()) {
                    messages.add(articleBean.getArticleTitle());
                }
                holder.noticeMarqueeView.startWithList(messages);
                break;
            case "home_nav":
                holder.navLinearLayout.setVisibility(View.VISIBLE);
                HomeNavListAdapter homeNavListAdapter = new HomeNavListAdapter(activity, bean.getHomeNavBean().getItem());
                App.getInstance().setRecyclerView(App.getInstance(), holder.mainNavRecyclerView, homeNavListAdapter);
                holder.mainNavRecyclerView.setLayoutManager(new GridLayoutManager(activity, 5));
                break;
            case "home1":
                holder.home1ImageView.setVisibility(View.VISIBLE);
                BaseImageLoader.getInstance().display(bean.getHome1Bean().getImage(), holder.home1ImageView);
                holder.home1ImageView.setOnClickListener(view -> App.getInstance().startTypeValue(activity, bean.getHome1Bean().getType(), bean.getHome1Bean().getData()));
                break;
            case "goods":
                holder.mainRecyclerView.setVisibility(View.VISIBLE);
                HomeGoodsListAdapter homeGoodsListAdapter = new HomeGoodsListAdapter(activity, bean.getGoodsBean().getItem());
                App.getInstance().setRecyclerView(App.getInstance(), holder.mainRecyclerView, homeGoodsListAdapter);
                holder.mainRecyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
                holder.mainRecyclerView.setPadding(App.getInstance().dipToPx(2), 0, App.getInstance().dipToPx(2), 0);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.headerLinearLayout)
        LinearLayoutCompat headerLinearLayout;
        @BindView(R.id.navLinearLayout)
        LinearLayoutCompat navLinearLayout;
        @BindView(R.id.noticeLinearLayout)
        LinearLayoutCompat noticeLinearLayout;
        @BindView(R.id.mainBanner)
        Banner<String, HomeBannerAdapter> mainBanner;
        @BindView(R.id.mainNavRecyclerView)
        RecyclerView mainNavRecyclerView;
        @BindView(R.id.home1ImageView)
        AppCompatImageView home1ImageView;
        @BindView(R.id.mainRecyclerView)
        RecyclerView mainRecyclerView;
        @BindView(R.id.noticeMarqueeView)
        MarqueeView<String> noticeMarqueeView;
        public ViewHolder(View view) {
            super(view);
        }
    }

    public void setBanner(Banner<String, HomeBannerAdapter> banner, List<String> list, HomeBannerAdapter bannerAdapter) {
        banner.setAdapter(bannerAdapter);
        banner.setIndicator(new RectangleIndicator(activity));
        banner.setIndicatorSpace((int) BannerUtils.dp2px(4));
        banner.setIndicatorRadius(0);
        banner.setDatas(list);
        banner.start();
    }
}