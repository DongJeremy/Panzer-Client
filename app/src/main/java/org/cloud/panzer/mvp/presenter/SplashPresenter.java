package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.SplashContract;
import org.cloud.panzer.mvp.model.SplashModel;

public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    @Override
    protected SplashContract.Model createModel() {
        return new SplashModel();
    }

    public void requestGetAndroid() {
        getModel().getAndroid()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()){
                    @Override
                    public void onSuccess(String result) {
                        getView().showGetAndroidSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showGetAndroidFail(errMsg);
                    }
                });
    }
}
