package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.GoodsEvaluateContract;

import io.reactivex.Observable;

public class GoodsEvaluateModel extends BaseModel implements GoodsEvaluateContract.Model {
    @Override
    public Observable<String> goodsEvaluate(String goodsId, String type, String page, String curpage) {
        return RetrofitUtils.getRawHttpService().goodsEvaluate(goodsId, type, page, curpage);
    }
}
