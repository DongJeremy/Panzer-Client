package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface PayContract {

    interface View extends IView {
        void showPaySuccess(BaseBean baseBean);
        void showPayFail(String reason);
    }

    interface Model extends IModel {
        Observable<String> postPay(String paySn);
    }
}
