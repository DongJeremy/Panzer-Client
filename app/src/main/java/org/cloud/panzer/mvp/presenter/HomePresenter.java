package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.HomeContract;
import org.cloud.panzer.mvp.model.HomeInfoModel;

public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Override
    protected HomeContract.Model createModel() {
        return new HomeInfoModel();
    }

    public void requestHomeInfoData() {
        getModel().getHomeInfoData()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showHomeInfoData(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });

    }

    public void requestArticleListData() {
        getModel().getArticleListData()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showArticleListData(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });

    }
}
