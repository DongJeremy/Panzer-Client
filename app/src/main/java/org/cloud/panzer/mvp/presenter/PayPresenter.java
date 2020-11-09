package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.PayContract;
import org.cloud.panzer.mvp.model.PayModel;

public class PayPresenter extends BasePresenter<PayContract.Model, PayContract.View> {
    @Override
    protected PayContract.Model createModel() {
        return new PayModel();
    }

    public void requestPayList(String paySn) {
        getModel().postPay(paySn)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showPayListSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showPayListFail(errMsg);
                    }
                });
    }

    public void requestPayNew(String paySn, String password, String rcbPay, String pdPay) {
        getModel().payNew(paySn, password, rcbPay, pdPay, "alipay")
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showPayNewSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showPayNewFail(errMsg);
                    }
                });
    }

    public void requestAlipayNativePay(String paySn) {
        getModel().payNew(paySn, "", "0", "0", "alipay")
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAlipayNativePaySuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showAlipayNativePayFail(errMsg);
                    }
                });
    }

    public void requestWxAppPay3(String paySn) {
        getModel().wxAppPay3(paySn)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showWxAppPay3Success(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showWxAppPay3Fail(errMsg);
                    }
                });
    }

    public void requestCheckPdPwd(String password) {
        getModel().checkPdPwd(password)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showCheckPdPwdSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showCheckPdPwdFail(errMsg);
                    }
                });
    }
}
