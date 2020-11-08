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

    public void requestPay(String paySn) {
        getModel().postPay(paySn)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showPaySuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showPayFail(errMsg);
                    }
                });

    }
}
