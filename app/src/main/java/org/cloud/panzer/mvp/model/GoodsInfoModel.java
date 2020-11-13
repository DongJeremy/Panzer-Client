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
    public Observable<String> cartAddGoods(String goodsId, String quantity) {
        return RetrofitUtils.getRawHttpService().cartAdd(goodsId, quantity);
    }

    @Override
    public Observable<String> calc(String goodsId, String areaId) {
        return RetrofitUtils.getRawHttpService().calc(goodsId, areaId);
    }
}
