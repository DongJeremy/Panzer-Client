package org.cloud.core.net.download;

import org.cloud.core.net.interceptor.ProgressInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * FileName: DownLoadManager
 * Author: Admin
 * Date: 2020/11/14 10:45
 * Description: 件下载管理，封装一行代码实现下载
 */
public class DownLoadManager {

    private static DownLoadManager instance;

    private static Retrofit retrofit;

    private DownLoadManager(String url) {
        buildNetWork(url);
    }

    /**
     * 单例模式
     *
     * @return DownLoadManager
     */
    public static DownLoadManager getInstance(String url) {
        if (instance == null) {
            instance = new DownLoadManager(url);
        }
        return instance;
    }

    public void download(String downUrl, final ProgressCallBack<File> callBack) {
        retrofit.create(ApiService.class)
                .download(downUrl)
                .subscribeOn(Schedulers.io())//请求网络 在调度者的io线程
                .observeOn(Schedulers.io()) //指定线程保存文件
                .map(callBack::saveFile)
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中更新ui
                .subscribe(new DownLoadSubscriber<>(callBack));
    }

    private void buildNetWork(String url) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new ProgressInterceptor())
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(url)
                .build();
    }

    private interface ApiService {
        @Streaming
        @GET
        Observable<ResponseBody> download(@Url String url);
    }
}
