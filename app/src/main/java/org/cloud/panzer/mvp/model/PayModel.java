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

    @Override
    public Observable<String> payNew(String paySn, String password, String rcbPay, String pdPay, String paymentCode) {
        return RetrofitUtils.getRawHttpService().getPayNew(paySn, password, rcbPay, pdPay, paymentCode);
    }

    @Override
    public Observable<String> wxAppPay3(String paySn) {
        return RetrofitUtils.getRawHttpService().wxAppPay3(paySn);
    }

    @Override
    public Observable<String> checkPdPwd(String password) {
        return RetrofitUtils.getRawHttpService().checkPdPwd(password);
    }
}
