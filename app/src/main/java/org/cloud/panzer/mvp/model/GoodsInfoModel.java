package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.GoodsContract;

import io.reactivex.Observable;

public class GoodsInfoModel extends BaseModel implements GoodsContract.Model {
    @Override
    public Observable<String> getGoodsDetailData(String id) {
        return RetrofitUtils.getRawHttpService().getGoodsDetailData(id);
    }

    @Override
    public Observable<String> getGoodsImagesData(String id) {
        return RetrofitUtils.getRawHttpService().getGoodsImagesData(id);
    }
}
