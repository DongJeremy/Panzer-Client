package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import java.io.File;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public interface SplashContract {

    interface View extends IView {
        void showGetAndroidSuccess(BaseBean baseBean);
        void showGetAndroidFail(String msg);

        void showDownloadSuccess(File file);
        void showDownloadFail();
        void showDownloadStart();
        void showDownloadProgress(int progress, int total);
    }

    interface Model extends IModel {
        Observable<String> getAndroid();
    }
}
