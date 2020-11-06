package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.panzer.mvp.contract.CenterContract;
import org.cloud.panzer.mvp.model.CenterModel;

public class CenterPresenter extends BasePresenter<CenterContract.Model, CenterContract.View> {
    @Override
    protected CenterContract.Model createModel() {
        return new CenterModel();
    }

    public void requestLogin(String username, String password) {
        //String key = BaseShared.getInstance().getString(BaseConstant.SHARED_KEY);
//        getModel().postLogin(username, password, "wap")
//                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
//                .subscribe(new BaseObserver<String>(getView()) {
//                    @Override
//                    public void onSuccess(String result) {
//                        getView().showLoginSuccess(result);
//                    }
//
//                    @Override
//                    public void onFailure(String errMsg, boolean isNetError) {
//                        Log.e("ERROR", errMsg);
//                        getView().showError(errMsg);
//                    }
//                });

    }
}
