package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.SearchContract;

import io.reactivex.Observable;

public class SearchModel extends BaseModel implements SearchContract.Model {
    @Override
    public Observable<String> getSearchKeyList() {
        return RetrofitUtils.getRawHttpService().getSearchKeyList();
    }
}
