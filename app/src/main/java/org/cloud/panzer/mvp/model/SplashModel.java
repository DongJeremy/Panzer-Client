package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.SplashContract;

import io.reactivex.Observable;

public class SplashModel extends BaseModel implements SplashContract.Model {
    @Override
    public Observable<String> getAndroid() {
        return RetrofitUtils.getRawHttpService().getAndroid();
    }
}
