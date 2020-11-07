package org.cloud.panzer.ui.main;

import org.cloud.core.base.BaseMvpFragment;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.CartContract;
import org.cloud.panzer.mvp.presenter.CartPresenter;

public class CateFragment extends BaseMvpFragment<CartPresenter> implements CartContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main_cate;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected CartPresenter createPresenter() {
        return null;
    }

    @Override
    public void showCartListData(String cartListData) {

    }

    @Override
    public void showCartEditQuantity(String cartEditData) {

    }

    @Override
    public void showCartDeleteData(int position, int positionGoods, String cartListData) {

    }
}