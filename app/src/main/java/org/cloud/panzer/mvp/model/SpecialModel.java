package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.SpecialContract;

import io.reactivex.Observable;

public class SpecialModel extends BaseModel implements SpecialContract.Model {
    @Override
    public Observable<String> special(String id) {
        return RetrofitUtils.getRawHttpService().special(id);
    }
}
