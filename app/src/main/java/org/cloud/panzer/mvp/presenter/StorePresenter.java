package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.StoreContract;
import org.cloud.panzer.mvp.model.StoreModel;

public class StorePresenter extends BasePresenter<StoreContract.Model, StoreContract.View> {
    @Override
    protected StoreContract.Model createModel() {
        return new StoreModel();
    }

    public void requestStoreInfoData(String id) {
        getModel().storeInfo(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showStoreInfoSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showStoreInfoFail(errMsg);
                    }
                });
    }

    public void requestVoucherTplListData(String id) {
        getModel().voucherTplList(id, "free")
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showVoucherTplListSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showVoucherTplListFail(errMsg);
                    }
                });
    }
}
