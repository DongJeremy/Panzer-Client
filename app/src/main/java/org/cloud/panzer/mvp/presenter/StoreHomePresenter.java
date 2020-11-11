package org.cloud.panzer.mvp.presenter;

import org.cloud.core.base.BaseToast;
import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.StoreHomeContract;
import org.cloud.panzer.mvp.model.StoreHomeModel;

public class StoreHomePresenter extends BasePresenter<StoreHomeContract.Model, StoreHomeContract.View> {
    @Override
    protected StoreHomeContract.Model createModel() {
        return new StoreHomeModel();
    }

    public void requestFavoritesAddData(String id) {
        getModel().favoritesAdd(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showFavoritesAddSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        BaseToast.getInstance().show(errMsg);
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestFavoritesDelData(String id) {
        getModel().favoritesDel(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showFavoritesDelSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        BaseToast.getInstance().show(errMsg);
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestFavoritesInfoData(String id) {
        getModel().favoritesInfo(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showFavoritesInfoSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        BaseToast.getInstance().show(errMsg);
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestFavoritesStoreAddData(String id) {
        getModel().favoritesStoreAdd(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showFavoritesStoreAddSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        BaseToast.getInstance().show(errMsg);
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestFavoritesStoreDelData(String id) {
        getModel().favoritesStoreDel(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showFavoritesStoreDelSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        BaseToast.getInstance().show(errMsg);
                        getView().showError(errMsg);
                    }
                });
    }
}
