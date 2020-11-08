package org.cloud.panzer.mvp.presenter;

import org.cloud.core.base.BaseToast;
import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.GoodsContract;
import org.cloud.panzer.mvp.model.GoodsInfoModel;

public class GoodsPresenter extends BasePresenter<GoodsContract.Model, GoodsContract.View> {
    @Override
    protected GoodsContract.Model createModel() {
        return new GoodsInfoModel();
    }

    public void requestGoodsDetailData(String id) {
        getModel().getGoodsDetailData(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showGoodsDetailSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestGoodsImagesData(String id) {
        getModel().getGoodsImagesData(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showGoodsImagesData(result);
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestCartAdd(String id, String quantity) {
        getModel().cartAddGoods(id, quantity)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAddGoodsSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                        BaseToast.getInstance().show(errMsg);
                    }
                });
    }
}
