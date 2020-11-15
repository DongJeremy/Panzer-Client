package org.cloud.core.net;

import org.cloud.core.mvp.IView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DefaultObserver;
import okhttp3.ResponseBody;

/**
 * FileName: FileDownLoadObserver
 * Author: Admin
 * Date: 2020/11/14 14:06
 * Description: FileDownLoadObserver
 */
public abstract class FileDownLoadObserver<T> extends DefaultObserver<T> {

    private IView mView;

    public FileDownLoadObserver(IView mView) {
        this.mView = mView;
    }

    @Override
    protected void onStart() {
        onDownLoadStart();
    }

    @Override
    public void onNext(@NonNull T t) {
        onDownLoadSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onDownLoadFail(e);
    }

    @Override
    public void onComplete() {

    }

    //开始下载的回调
    public abstract void onDownLoadStart();

    //下载成功的回调
    public abstract void onDownLoadSuccess(T t);

    //下载失败回调
    public abstract void onDownLoadFail(Throwable throwable);

    //下载进度监听
    public abstract void onProgress(int progress, int total);

    public File saveFile(ResponseBody responseBody, String destFileDir, String destFileName) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = responseBody.byteStream();
            final long total = responseBody.contentLength();
            long sum = 0;

            File dir = new File(destFileDir);
            if (!dir.exists()) {
                boolean b = dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);
                final long finalSum = sum;
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //这里就是对进度的监听回调
                onProgress((int) (finalSum * 100 / total), 100);
            }
            fos.flush();

            return file;

        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
