package org.cloud.panzer.mvp.presenter;

import android.os.Environment;

import androidx.annotation.NonNull;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.net.FileDownLoadObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.SplashContract;
import org.cloud.panzer.mvp.model.SplashModel;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.Okio;
import retrofit2.Response;

public class SplashPresenter extends BasePresenter<SplashContract.Model, SplashContract.View> {
    @Override
    protected SplashContract.Model createModel() {
        return new SplashModel();
    }

    public void requestGetAndroid() {
        getModel().getAndroid()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()){
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

    private void downloadFile(@NonNull String url, final String destDir, final String fileName, final FileDownLoadObserver<File> fileDownLoadObserver) {
        getModel().download(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(responseBody -> fileDownLoadObserver.saveFile(responseBody, destDir, fileName))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileDownLoadObserver);
    }

    public void requestDownloadApk(String url, String path, String filename) {
        downloadFile(url, path, filename, new FileDownLoadObserver<File>(getView()) {
            @Override
            public void onDownLoadStart() {
                getView().showDownloadStart();
            }

            @Override
            public void onDownLoadSuccess(File file) {
                getView().showDownloadSuccess(file);
            }

            @Override
            public void onDownLoadFail(Throwable throwable) {
                getView().showDownloadFail();
            }

            @Override
            public void onProgress(int progress, long total) {
                getView().showDownloadProgress(progress, total);
            }
        });
    }
}
