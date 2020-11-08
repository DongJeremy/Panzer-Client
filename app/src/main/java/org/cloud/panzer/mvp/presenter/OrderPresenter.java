package org.cloud.panzer.mvp.presenter;

import android.util.Log;

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

    public void requestOrderList(String stateType, String orderKey, String page, String curpage) {
        getModel().postMemberOrderList(stateType, orderKey, page, curpage)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showOrderListSuccess(JsonUtils.parseJsonData(result));
                    }
                    @Override
                    public boolean isSuccessFul(String result) {
                        return JsonUtils.checkJsonCodeSuccess(result);
                    }
                    @Override
                    public void onLogicError() {
                    }
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        Log.e("ERROR", errMsg);
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestOrderDelete(String orderId) {
        getModel().postMemberOrderDelete(orderId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAreaList(JsonUtils.parseJsonData(result), type);
                    }
                    @Override
                    public boolean isSuccessFul(String result) {
                        return JsonUtils.checkJsonCodeSuccess(result);
                    }
                    @Override
                    public void onLogicError() {
                    }
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        Log.e("ERROR", errMsg);
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestOrderCancel(String orderId) {
        getModel().postMemberOrderCancel(orderId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAreaList(JsonUtils.parseJsonData(result), type);
                    }
                    @Override
                    public boolean isSuccessFul(String result) {
                        return JsonUtils.checkJsonCodeSuccess(result);
                    }
                    @Override
                    public void onLogicError() {
                    }
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        Log.e("ERROR", errMsg);
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestOrderReceive(String orderId) {
        getModel().postMemberOrderReceive(orderId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAreaList(JsonUtils.parseJsonData(result), type);
                    }
                    @Override
                    public boolean isSuccessFul(String result) {
                        return JsonUtils.checkJsonCodeSuccess(result);
                    }
                    @Override
                    public void onLogicError() {
                    }
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        Log.e("ERROR", errMsg);
                        getView().showError(errMsg);
                    }
                });
    }
}
