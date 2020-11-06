package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.MineContract;

import io.reactivex.Observable;

public class MineModel extends BaseModel implements MineContract.Model {
    @Override
    public Observable<String> getMemberIndex() {
        return RetrofitUtils.getRawHttpService().memberIndex();
    }
}
