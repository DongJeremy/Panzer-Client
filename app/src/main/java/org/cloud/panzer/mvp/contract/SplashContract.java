package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface SplashContract {

    interface View extends IView {
        void showGetAndroidSuccess(BaseBean baseBean);
        void showGetAndroidFail(String msg);
    }

    interface Model extends IModel {
        Observable<String> getAndroid();
    }
}
