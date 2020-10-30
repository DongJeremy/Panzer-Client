package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.panzer.mvp.contract.HomeContract;
import org.cloud.panzer.mvp.model.HomeInfoModel;

public class GoodsPresenter extends BasePresenter<HomeContract.Model, HomeContract.View> {
    @Override
    protected HomeContract.Model createModel() {
        return new HomeInfoModel();
    }

    public void requestGridData() {
        getModel().getHomeInfoData()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<HomeInfoModel.HomeInfo>(getView()) {
                    @Override
                    public void onSuccess(HomeInfoModel.HomeInfo result) {
                        if(result.getCode()==200) {
                            getView().showHomeInfoData(result.getDatas());
                        }
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });

    }
}
