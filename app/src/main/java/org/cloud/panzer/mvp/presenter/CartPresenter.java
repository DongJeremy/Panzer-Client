package org.cloud.panzer.mvp.presenter;

import android.util.Log;

import org.cloud.core.base.BaseToast;
import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.CartContract;
import org.cloud.panzer.mvp.model.CartModel;

public class CartPresenter extends BasePresenter<CartContract.Model, CartContract.View> {
    @Override
    protected CartContract.Model createModel() {
        return new CartModel();
    }

    public void requestCartListData() {
        getModel().getCartListData()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showCartListData(JsonUtils.parseJsonToBaseBean(result));
                    }
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        Log.e("ERROR", errMsg);
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestCartEditQuantity(String cartId, String quantity) {
        getModel().cartEditQuantity(cartId, quantity)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showCartEditQuantity(JsonUtils.parseJsonToBaseBean(result));
                    }
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                        BaseToast.getInstance().show(errMsg);
                    }
                });
    }

    public void requestCartDelete(String cartId, final int position, final int positionGoods) {
        getModel().cartDelete(cartId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showCartDeleteData(position, positionGoods, JsonUtils.parseJsonToBaseBean(result));
                    }
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                        BaseToast.getInstance().show(errMsg);
                    }
                });
    }
}
