package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.AreaContract;
import org.cloud.panzer.mvp.contract.OrderContract;

import io.reactivex.Observable;

public class OrderModel extends BaseModel implements OrderContract.Model {
    @Override
    public Observable<String> getAreaListById(String id) {
        return RetrofitUtils.getRawHttpService().getAreaList(id);
    }
}
