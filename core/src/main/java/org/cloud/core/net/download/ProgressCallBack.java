package org.cloud.core.net.download;

import android.os.Handler;
import android.util.Log;

import org.cloud.core.rx.RxBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * FileName: ProgressCallBack
 * Author: Admin
 * Date: 2020/11/14 10:45
 * Description: 下载进度的 CallBack
 */
public abstract class ProgressCallBack<T> {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final String destFileDir; // 本地文件存放路径
    private final String destFileName; // 文件名
    private Disposable mSubscription;
    private final Handler handler;

    /**
     * @param destFileDir  本地文件存放路径
     * @param destFileName 文件名
     */
    public ProgressCallBack(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        handler = new Handler();
        subscribeLoadProgress();
    }

    protected abstract void onSuccess(T t);

    protected abstract void progress(long progress, long total);

    protected abstract void onError(Throwable e);

    protected abstract void onStart();

    public void onCompleted() {
    }

    public File saveFile(ResponseBody body) {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = body.byteStream();
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            unsubscribe();
            return file;
            //onCompleted();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                Log.e("saveFile", e.getMessage());
            }
        }
        return null;
    }

    /**
     * 订阅加载的进度条
     */
    public void subscribeLoadProgress() {
        mSubscription = RxBus.getInstance().toObservable(DownLoadStateBean.class)
                .subscribe(downLoadStateBean -> handler.post(() -> progress(downLoadStateBean.getBytesLoaded(), downLoadStateBean.getTotal())));
        //将订阅者加入管理站
        addDispose(mSubscription);
    }

    /**
     * 取消订阅，防止内存泄漏
     */
    public void unsubscribe() {
        compositeDisposable.remove(mSubscription);
    }

    /**
     * @param disposable Disposable
     */
    public void addDispose(Disposable disposable) {
        if (disposable != null) {
            compositeDisposable.add(disposable);// 将所有 Disposable 放入集中处理
        }
    }
}
