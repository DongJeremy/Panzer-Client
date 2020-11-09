package org.cloud.panzer.mvp.presenter;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.RegisterContract;
import org.cloud.panzer.mvp.model.RegisterModel;

public class RegisterPresenter extends BasePresenter<RegisterContract.Model, RegisterContract.View> {
    @Override
    protected RegisterContract.Model createModel() {
        return new RegisterModel();
    }

    public void requestRegister(String username, String password, String email) {
        getModel().register(username, password, password, email)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        BaseBean baseBean = JsonUtils.parseJsonToBaseBean(result);
                        if(baseBean.getCode()==200) {
                            getView().showRegisterSuccess(baseBean);
                        } else {
                            getView().showRegisterFail(JsonUtils.parseJsonToError(baseBean.getDatas()));
                        }
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showRegisterFail(errMsg);
                    }
                });
    }

    public void requestGetSmsCaptcha(String type, String phone, String secVal, String secKey) {
        getModel().getSmsCaptcha(type, phone, secVal, secKey)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showSmsCaptchaSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showSmsCaptchaFail(errMsg);
                    }
                });
    }

    public void requestGetState(String t) {
        getModel().getState(t)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showStateSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showStateFail(errMsg);
                    }
                });
    }
}
