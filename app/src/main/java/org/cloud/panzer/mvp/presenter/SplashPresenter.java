package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.net.download.DownLoadManager;
import org.cloud.core.net.download.ProgressCallBack;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.app.Constant;
import org.cloud.panzer.mvp.contract.SplashContract;
import org.cloud.panzer.mvp.model.SplashModel;

import java.io.File;

public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    @Override
    protected SplashContract.Model createModel() {
        return new SplashModel();
    }

    public void requestGetAndroid() {
        getModel().getAndroid()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showGetAndroidSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showGetAndroidFail(errMsg);
                    }
                });
    }

    public void requestDownloadApk(String url, String path, String filename) {
        DownLoadManager.getInstance(Constant.LOCAL_URL).download(url, new ProgressCallBack<File>(path, filename) {
            @Override
            public void onSuccess(File file) {
                getView().showDownloadSuccess(file);
            }

            @Override
            public void progress(long progress, long total) {
                getView().showDownloadProgress((int) (progress * 100 / total), 100);
            }

            @Override
            public void onError(Throwable e) {
                getView().showDownloadFail();
            }

            @Override
            protected void onStart() {
                getView().showDownloadStart();
            }
        });
    }
}
