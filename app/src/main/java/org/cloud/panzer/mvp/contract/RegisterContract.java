package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface RegisterContract {

    interface View extends IView {
        void showRegisterSuccess(BaseBean baseBean);

        void showRegisterFail(String reason);

        void showSmsCaptchaSuccess(BaseBean baseBean);

        void showSmsCaptchaFail(String reason);

        void showStateSuccess(BaseBean baseBean);

        void showStateFail(String reason);
    }

    interface Model extends IModel {
        Observable<String> register(String username, String password, String confirm, String email);

        Observable<String> getSmsCaptcha(String type, String phone, String sec_val, String sec_key);

        Observable<String> getState(String t);
    }
}
