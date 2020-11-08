package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.OrderContract;

import io.reactivex.Observable;

public class OrderModel extends BaseModel implements OrderContract.Model {

    @Override
    public Observable<String> postMemberOrderList(String stateType, String orderKey, String page, String curpage) {
        return RetrofitUtils.getRawHttpService().memberOrderList(stateType, orderKey, page, curpage);
    }

    @Override
    public Observable<String> postMemberOrderDelete(String orderId) {
        return RetrofitUtils.getRawHttpService().memberOrderDelete(orderId);
    }

    @Override
    public Observable<String> postMemberOrderCancel(String orderId) {
        return RetrofitUtils.getRawHttpService().memberOrderCancel(orderId);
    }

    @Override
    public Observable<String> postMemberOrderReceive(String orderId) {
        return RetrofitUtils.getRawHttpService().memberOrderReceive(orderId);
    }
}
