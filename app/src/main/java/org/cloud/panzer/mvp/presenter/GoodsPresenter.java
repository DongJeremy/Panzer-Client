package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.panzer.mvp.contract.GoodsContract;
import org.cloud.panzer.mvp.model.GoodsInfoModel;

public class GoodsPresenter extends BasePresenter<GoodsContract.Model, GoodsContract.View> {
    @Override
    protected GoodsContract.Model createModel() {
        return new GoodsInfoModel();
    }

    public void requestGoodsData(String id) {
        getModel().getGoodsDetailData(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showGoodsDetailData(result);
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });
    }
}
