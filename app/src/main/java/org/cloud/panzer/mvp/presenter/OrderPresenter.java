package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.OrderContract;
import org.cloud.panzer.mvp.model.OrderModel;

public class OrderPresenter extends BasePresenter<OrderContract.Model, OrderContract.View> {
    @Override
    protected OrderContract.Model createModel() {
        return new OrderModel();
    }

    public void requestOrderList(String stateType, String orderKey, String curpage) {
        getModel().postMemberOrderList(stateType, orderKey, "20", curpage)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showOrderListSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showOrderListFail(errMsg);
                    }
                });
    }

    public void requestOrderDelete(String orderId) {
        getModel().postMemberOrderDelete(orderId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showOrderDeleteSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showOrderDeleteFail(errMsg);
                    }
                });
    }

    public void requestOrderCancel(String orderId) {
        getModel().postMemberOrderCancel(orderId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showOrderCancelSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showOrderCancelFail(errMsg);
                    }
                });
    }

    public void requestOrderReceive(String orderId) {
        getModel().postMemberOrderReceive(orderId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showOrderReceiveSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showOrderReceiveFail(errMsg);
                    }
                });
    }
}
