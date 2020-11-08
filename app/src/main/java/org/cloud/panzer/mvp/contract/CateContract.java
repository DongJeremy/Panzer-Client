package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

public interface CateContract {

    interface View extends IView {
        //void showCenterSuccess(String loginData);
    }

    interface Model extends IModel {
        //Observable<String> postLogin(String username, String password, String client);
    }
}
