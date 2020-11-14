package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.LocalRetrofit;
import org.cloud.panzer.mvp.contract.SplashContract;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class SplashModel extends BaseModel implements SplashContract.Model {
    @Override
    public Observable<String> getAndroid() {
        return LocalRetrofit.getLocalService().getAndroid();
    }

    @Override
    public Observable<ResponseBody> download(String url) {
        return LocalRetrofit.getLocalService().downloadApk(url);
    }
}
