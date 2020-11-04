package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.bean.BaseBean;
import org.cloud.panzer.mvp.contract.HomeContract;

import io.reactivex.Observable;

public class HomeInfoModel extends BaseModel implements HomeContract.Model {
    @Override
    public Observable<String> getHomeInfoData() {
        return RetrofitUtils.getRawHttpService().getHomeInfoData();
    }

    @Override
    public Observable<String> getArticleListData() {
        return RetrofitUtils.getRawHttpService().getArticleListData();
    }
}
