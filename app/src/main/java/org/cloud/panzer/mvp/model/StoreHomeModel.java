package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.StoreHomeContract;

import io.reactivex.Observable;

public class StoreHomeModel extends BaseModel implements StoreHomeContract.Model {

    @Override
    public Observable<String> favoritesAdd(String id) {
        return RetrofitUtils.getRawHttpService().favoritesAdd(id);
    }

    @Override
    public Observable<String> favoritesDel(String id) {
        return RetrofitUtils.getRawHttpService().favoritesDel(id);
    }

    @Override
    public Observable<String> favoritesInfo(String id) {
        return RetrofitUtils.getRawHttpService().favoritesInfo(id);
    }

    @Override
    public Observable<String> favoritesStoreAdd(String id) {
        return RetrofitUtils.getRawHttpService().favoritesStoreAdd(id);
    }

    @Override
    public Observable<String> favoritesStoreDel(String id) {
        return RetrofitUtils.getRawHttpService().favoritesStoreDel(id);
    }
}
