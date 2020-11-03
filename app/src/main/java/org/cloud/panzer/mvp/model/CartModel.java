package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.CartContract;

import io.reactivex.Observable;

public class CartModel extends BaseModel implements CartContract.Model {
    @Override
    public Observable<String> getHomeInfoData() {
        return RetrofitUtils.getRawHttpService().getHomeInfoData();
    }
}
