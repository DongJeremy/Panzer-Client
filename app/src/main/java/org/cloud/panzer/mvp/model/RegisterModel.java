package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.RegisterContract;

import io.reactivex.Observable;

public class RegisterModel extends BaseModel implements RegisterContract.Model {
    @Override
    public Observable<String> register(String username, String password, String confirm, String email) {
        return RetrofitUtils.getRawHttpService().register(username, password, confirm, email, "wap");
    }

    @Override
    public Observable<String> getSmsCaptcha(String type, String phone, String secVal, String secKey) {
        return RetrofitUtils.getRawHttpService().getSmsCaptcha(type, phone, secVal, secKey);
    }

    @Override
    public Observable<String> getState(String t) {
        return RetrofitUtils.getRawHttpService().getState(t);
    }
}
