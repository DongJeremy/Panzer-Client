package org.cloud.panzer.mvp.presenter;

import android.util.Log;

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
        getModel().postLogin(username, password, "wap")
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showLoginSuccess(result);
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
