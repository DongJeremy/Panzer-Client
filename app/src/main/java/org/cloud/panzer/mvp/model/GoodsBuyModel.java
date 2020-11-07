package org.cloud.panzer.mvp.model;

import com.google.gson.JsonObject;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.bean.GoodsBuyBean;
import org.cloud.panzer.mvp.contract.GoodsBuyContract;

import java.util.Map;

import io.reactivex.Observable;

public class GoodsBuyModel extends BaseModel implements GoodsBuyContract.Model {
    @Override
    public Observable<String> buySetp1(String cardId, String ifCart, String addressId) {
        return RetrofitUtils.getRawHttpService().memberBuyStep1(cardId, ifCart, addressId);
    }

    @Override
    public Observable<String> buySetp2(Map<String, String> maps) {
        return RetrofitUtils.getRawHttpService().memberBuyStep2(maps);
    }
}
