package org.cloud.panzer.mvp.presenter;

import android.util.Log;

import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseShared;
import org.cloud.core.base.BaseToast;
import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.panzer.mvp.contract.CartContract;
import org.cloud.panzer.mvp.model.CartModel;

public class CartPresenter extends BasePresenter<CartContract.Model, CartContract.View> {
    @Override
    protected CartContract.Model createModel() {
        return new CartModel();
    }

    public void requestCartListData() {
        String key = BaseShared.getInstance().getString(BaseConstant.SHARED_KEY);
        getModel().getCartListData(key)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showCartListData(result);
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        Log.e("ERROR", errMsg);
                        getView().showError(errMsg);
                    }
                });

    }

    public void requestCartEditQuantity(String cartId, String quantity) {
        String key = BaseShared.getInstance().getString(BaseConstant.SHARED_KEY);
        getModel().cartEditQuantity(key, cartId, quantity)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showCartListData(result);
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                        BaseToast.getInstance().show(errMsg);
                    }
                });
    }

    public void requestCartDelete(String cartId) {
        String key = BaseShared.getInstance().getString(BaseConstant.SHARED_KEY);
        getModel().cartDelete(key, cartId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showCartDeleteData(result);
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                        BaseToast.getInstance().show(errMsg);
                    }
                });
    }
}
