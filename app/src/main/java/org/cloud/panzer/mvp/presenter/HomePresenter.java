package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.panzer.mvp.contract.HomeContract;
import org.cloud.panzer.mvp.model.HomeInfoModel;

public class HomePresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Override
    protected HomeContract.Model createModel() {
        return new HomeInfoModel();
    }

    public void requestGridData() {
        getModel().getHomeGridData()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<HomeInfoModel.HomeGrid>(getView()) {
                    @Override
                    public void onSuccess(HomeInfoModel.HomeGrid result) {
                        if(result.getCode()==200) {
                            getView().showGridData(result.getDatas());
                        }
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });

    }

    public void requestBannerData() {
        getModel().getHomeBannerData()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<HomeInfoModel.HomeBanner>(getView()) {
                    @Override
                    public void onSuccess(HomeInfoModel.HomeBanner result) {
                        if(result.getCode()==200) {
                            getView().showBannerData(result.getDatas());
                        }
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });

    }
}
