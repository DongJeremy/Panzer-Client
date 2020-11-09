package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface PayContract {

    interface View extends IView {
        void showPayListSuccess(BaseBean baseBean);
        void showPayListFail(String reason);

        void showPayNewSuccess(BaseBean baseBean);
        void showPayNewFail(String reason);

        void showWxAppPay3Success(BaseBean baseBean);
        void showWxAppPay3Fail(String reason);

        void showCheckPdPwdSuccess(BaseBean baseBean);
        void showCheckPdPwdFail(String reason);

        void showAlipayNativePaySuccess(BaseBean baseBean);
        void showAlipayNativePayFail(String reason);
    }

    interface Model extends IModel {
        Observable<String> postPay(String paySn);
        Observable<String> payNew(String paySn, String password, String rcbPay, String pdPay, String paymentCode);
        Observable<String> wxAppPay3(String paySn);
        Observable<String> checkPdPwd(String password);
    }
}