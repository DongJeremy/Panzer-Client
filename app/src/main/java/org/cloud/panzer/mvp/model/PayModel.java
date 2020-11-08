package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.PayContract;

import io.reactivex.Observable;

public class PayModel extends BaseModel implements PayContract.Model {
    @Override
    public Observable<String> postPay(String paySn) {
        return RetrofitUtils.getRawHttpService().memberPay(paySn);
    }
}
