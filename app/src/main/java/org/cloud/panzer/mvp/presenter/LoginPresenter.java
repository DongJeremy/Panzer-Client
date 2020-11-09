package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.LoginContract;
import org.cloud.panzer.mvp.model.LoginModel;

public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    @Override
    protected LoginContract.Model createModel() {
        return new LoginModel();
    }

    public void requestLogin(String username, String password) {
        getModel().postLogin(username, password)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showLoginSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });

    }
}
