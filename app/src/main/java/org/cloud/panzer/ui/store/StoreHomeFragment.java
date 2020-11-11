package org.cloud.panzer.ui.store;

import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.youth.banner.Banner;

import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseMvpFragment;
import org.cloud.core.base.BaseToast;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.adapter.GoodsListAdapter;
import org.cloud.panzer.bean.GoodsBean;
import org.cloud.panzer.bean.StoreInfoBean;
import org.cloud.panzer.mvp.contract.StoreHomeContract;
import org.cloud.panzer.mvp.presenter.StoreHomePresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * FileName: StoreHomeFragment
 * Author: Admin
 * Date: 2020/11/11 14:58
 * Description: HomeFragment
 */
public class StoreHomeFragment extends BaseMvpFragment<StoreHomePresenter> implements StoreHomeContract.View {

    @BindView(R.id.avatarImageView)
    AppCompatImageView avatarImageView;
    @BindView(R.id.favoritesRankLinearLayout)
    LinearLayoutCompat favoritesRankLinearLayout;
    @BindView(R.id.favoritesRankOneImageView)
    AppCompatImageView favoritesRankOneImageView;
    @BindView(R.id.favoritesRankOneTextView)
    AppCompatTextView favoritesRankOneTextView;
    @BindView(R.id.favoritesRankTextView)
    AppCompatTextView favoritesRankTextView;
    @BindView(R.id.favoritesRankThrImageView)
    AppCompatImageView favoritesRankThrImageView;
    @BindView(R.id.favoritesRankThrTextView)
    AppCompatTextView favoritesRankThrTextView;
    @BindView(R.id.favoritesRankTwoImageView)
    AppCompatImageView favoritesRankTwoImageView;
    @BindView(R.id.favoritesRankTwoTextView)
    AppCompatTextView favoritesRankTwoTextView;
    @BindView(R.id.favoritesTextView)
    AppCompatTextView favoritesTextView;
    @BindView(R.id.levelTextView)
    AppCompatTextView levelTextView;
    @BindView(R.id.mainBanner)
    Banner mainBanner;
    @BindView(R.id.mainRecyclerView)
    RecyclerView mainRecyclerView;
    @BindView(R.id.nameTextView)
    AppCompatTextView nameTextView;
    @BindView(R.id.numberTextView)
    AppCompatTextView numberTextView;
    @BindView(R.id.saleRankLinearLayout)
    LinearLayoutCompat saleRankLinearLayout;
    @BindView(R.id.saleRankOneImageView)
    AppCompatImageView saleRankOneImageView;
    @BindView(R.id.saleRankOneTextView)
    AppCompatTextView saleRankOneTextView;
    @BindView(R.id.saleRankTextView)
    AppCompatTextView saleRankTextView;
    @BindView(R.id.saleRankThrImageView)
    AppCompatImageView saleRankThrImageView;
    @BindView(R.id.saleRankThrTextView)
    AppCompatTextView saleRankThrTextView;
    @BindView(R.id.saleRankTwoImageView)
    AppCompatImageView saleRankTwoImageView;
    @BindView(R.id.saleRankTwoTextView)
    AppCompatTextView saleRankTwoTextView;

    private StoreInfoBean storeInfoBean;
    private ArrayList<GoodsBean> favoritesArrayList;
    private AppCompatImageView[] favoritesRankImageView;
    private AppCompatTextView[] saleRankNameTextView;
    private AppCompatImageView[] saleRankImageView;
    private ArrayList<GoodsBean> saleArrayList;
    private GoodsListAdapter mainAdapter;
    private ArrayList<GoodsBean> mainArrayList;
    private AppCompatTextView[] favoritesRankNameTextView;

    @Override
    protected StoreHomePresenter createPresenter() {
        return new StoreHomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store_home;
    }

    @Override
    protected void initView() {
        this.mainArrayList = new ArrayList<>();
        this.saleArrayList = new ArrayList<>();
        this.favoritesArrayList = new ArrayList<>();
        this.mainAdapter = new GoodsListAdapter(this.mainArrayList, true);

    }

    @Override
    protected void initListener() {
        this.favoritesTextView.setOnClickListener(view -> {
            if (this.storeInfoBean.isIsFavorate()) {
                favoritesDel();
            } else {
                favoritesAdd();
            }
        });
        this.favoritesRankTextView.setOnClickListener(view -> {
            this.favoritesRankTextView.setTextColor(App.getInstance().getColors(R.color.primary));
            this.favoritesRankLinearLayout.setVisibility(View.VISIBLE);
            this.saleRankTextView.setTextColor(App.getInstance().getColors(R.color.greyAdd));
            this.saleRankLinearLayout.setVisibility(View.GONE);
        });
        this.saleRankTextView.setOnClickListener(view -> {
            this.favoritesRankTextView.setTextColor(App.getInstance().getColors(R.color.greyAdd));
            this.favoritesRankLinearLayout.setVisibility(View.GONE);
            this.saleRankTextView.setTextColor(App.getInstance().getColors(R.color.primary));
            this.saleRankLinearLayout.setVisibility(View.VISIBLE);
        });
        this.mainAdapter.setOnItemClickListener(new GoodsListAdapter.OnItemClickListener() {
            public void onCart(int i, GoodsBean goodsBean) {
                addCart(goodsBean.getGoodsId());
            }
            public void onClick(int i, GoodsBean goodsBean) {
                App.getInstance().startGoods(getActivity(), goodsBean.getGoodsId());
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    private void addCart(String str) {
//        MemberCartModel.get().cartAdd(str, "1", new BaseHttpListener() {
//            public void onSuccess(BaseBean baseBean) {
//                BaseToast.getInstance().showSuccess();
//            }
//
//            public void onFailure(String str) {
//                BaseToast.getInstance().show(str);
//            }
//        });
    }

    @Override
    public void showFavoritesAddSuccess(BaseBean baseBean) {

    }

    @Override
    public void showFavoritesStoreAddSuccess(BaseBean baseBean) {
        if (baseBean.getDatas().equals("1")) {
            this.favoritesTextView.setText("已收藏");
            this.favoritesTextView.setBackgroundResource(R.color.blackSub);
            this.storeInfoBean.setStoreCollect(this.storeInfoBean.getStoreCollect() + 1);
            this.storeInfoBean.setIsFavorate(true);
            this.numberTextView.setText("粉丝：" + this.storeInfoBean.getStoreCollect());
            return;
        }
        BaseToast.getInstance().showFailure();
    }

    private void favoritesAdd() {
        mPresenter.requestFavoritesStoreAddData(this.storeInfoBean.getStoreId());
    }

    @Override
    public void showFavoritesStoreDelSuccess(BaseBean baseBean) {
        if (baseBean.getDatas().equals("1")) {
            this.favoritesTextView.setText("收藏");
            this.favoritesTextView.setBackgroundResource(R.color.primary);
            this.storeInfoBean.setStoreCollect(this.storeInfoBean.getStoreCollect() - 1);
            this.storeInfoBean.setIsFavorate(false);
            this.numberTextView.setText("粉丝：" + this.storeInfoBean.getStoreCollect());
            return;
        }
        BaseToast.getInstance().showFailure();
    }

    private void favoritesDel() {
        mPresenter.requestFavoritesStoreDelData(this.storeInfoBean.getStoreId());
    }

    @Override
    public void showFavoritesDelSuccess(BaseBean baseBean) {

    }

    @Override
    public void showFavoritesInfoSuccess(BaseBean baseBean) {

    }

}
