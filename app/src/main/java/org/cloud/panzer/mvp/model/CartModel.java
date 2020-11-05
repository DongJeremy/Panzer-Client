package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.CartContract;

import io.reactivex.Observable;

public class CartModel extends BaseModel implements CartContract.Model {
    @Override
    public Observable<String> getCartListData(String key) {
        return RetrofitUtils.getRawHttpService().getCartListData(key);
    }

    @Override
    public Observable<String> cartEditQuantity(String key, String cartId, String quantity) {
        return RetrofitUtils.getRawHttpService().cartEditQuantity(key, cartId, quantity);
    }

    @Override
    public Observable<String> cartDelete(String key, String cartId) {
        return RetrofitUtils.getRawHttpService().cartDelete(key, cartId);
    }
}
