package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.CartContract;

import io.reactivex.Observable;

public class CartModel extends BaseModel implements CartContract.Model {
    @Override
    public Observable<String> getCartListData() {
        return RetrofitUtils.getRawHttpService().getCartListData();
    }

    @Override
    public Observable<String> cartEditQuantity(String cartId, String quantity) {
        return RetrofitUtils.getRawHttpService().cartEditQuantity(cartId, quantity);
    }

    @Override
    public Observable<String> cartDelete(String cartId) {
        return RetrofitUtils.getRawHttpService().cartDelete(cartId);
    }
}
