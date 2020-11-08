package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.panzer.mvp.contract.CateContract;
import org.cloud.panzer.mvp.model.CateModel;

public class CatePresenter extends BasePresenter<CateContract.Model, CateContract.View> {
    @Override
    protected CateContract.Model createModel() {
        return new CateModel();
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
