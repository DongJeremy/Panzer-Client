package org.cloud.core.net.download;

import org.jetbrains.annotations.NotNull;

import io.reactivex.observers.DisposableObserver;

/**
 * FileName: DownLoadSubscriber
 * Author: Admin
 * Date: 2020/11/14 10:45
 * Description: DownLoadSubscriber
 */
public class DownLoadSubscriber<T> extends DisposableObserver<T> {
    private final ProgressCallBack<T> fileCallBack;

    public DownLoadSubscriber(ProgressCallBack<T> fileCallBack) {
        this.fileCallBack = fileCallBack;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (fileCallBack != null)
            fileCallBack.onStart();
    }

    @Override
    public void onComplete() {
        if (fileCallBack != null)
            fileCallBack.onCompleted();
    }

    @Override
    public void onError(@NotNull Throwable e) {
        if (fileCallBack != null)
            fileCallBack.onError(e);
    }

    @Override
    public void onNext(@NotNull T t) {
        if (fileCallBack != null)
            fileCallBack.onSuccess(t);
    }
}