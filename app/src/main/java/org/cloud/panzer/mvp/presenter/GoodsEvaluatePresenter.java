package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.GoodsEvaluateContract;
import org.cloud.panzer.mvp.model.GoodsEvaluateModel;

public class GoodsEvaluatePresenter extends BasePresenter<GoodsEvaluateContract.Model, GoodsEvaluateContract.View> {
    @Override
    protected GoodsEvaluateContract.Model createModel() {
        return new GoodsEvaluateModel();
    }

    public void requestGoodsEvaluateData(String goodsId, String type, String curpage) {
        getModel().goodsEvaluate(goodsId, type, "20", curpage)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showGoodsEvaluateSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showGoodsEvaluateFail(errMsg);
                    }
                });
    }
}
