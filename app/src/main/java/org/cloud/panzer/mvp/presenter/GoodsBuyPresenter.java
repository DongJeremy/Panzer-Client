package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.GoodsBuyContract;
import org.cloud.panzer.mvp.model.GoodsBuyModel;

import java.util.Map;

public class GoodsBuyPresenter extends BasePresenter<GoodsBuyContract.Model, GoodsBuyContract.View> {
    @Override
    protected GoodsBuyContract.Model createModel() {
        return new GoodsBuyModel();
    }

    public void requestGoodsBuyStep1(String cardId, String ifCart, String addressId) {
        getModel().buySetp1(cardId, ifCart, addressId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showGoodsBuyStep1Success(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showGoodsBuyStep1Fail(errMsg);
                    }
                });
    }

    public void requestGoodsBuyStep2(Map<String, String> maps) {
        getModel().buySetp2(maps)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showGoodsBuyStep2Success(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showGoodsBuyStep2Fail(errMsg);
                    }
                });
    }
}
