package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface LoginContract {

    interface View extends IView {
        void showLoginSuccess(String loginData);
    }

    interface Model extends IModel {
        Observable<String> postLogin(String username, String password, String client);
    }
}
