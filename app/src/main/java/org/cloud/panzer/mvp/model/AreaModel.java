package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.AreaContract;

import io.reactivex.Observable;

public class AreaModel extends BaseModel implements AreaContract.Model {
    @Override
    public Observable<String> getAreaListById(String id) {
        return RetrofitUtils.getRawHttpService().getAreaList(id);
    }
}
