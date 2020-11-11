package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.StoreContract;

import io.reactivex.Observable;

public class StoreModel extends BaseModel implements StoreContract.Model {
    @Override
    public Observable<String> storeInfo(String id) {
        return RetrofitUtils.getRawHttpService().storeInfo(id);
    }

    @Override
    public Observable<String> voucherTplList(String id, String gettype) {
        return RetrofitUtils.getRawHttpService().voucherTplList(id, gettype);
    }
}
