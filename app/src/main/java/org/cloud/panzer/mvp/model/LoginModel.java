package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.LoginContract;

import io.reactivex.Observable;

public class LoginModel extends BaseModel implements LoginContract.Model {
    @Override
    public Observable<String> postLogin(String username, String password, String client) {
        return RetrofitUtils.getRawHttpService().login(username, password, client);
    }
}
