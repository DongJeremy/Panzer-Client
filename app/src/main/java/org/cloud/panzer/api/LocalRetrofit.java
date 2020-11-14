package org.cloud.panzer.api;

import android.util.Log;

import org.cloud.core.net.BaseLocalRetrofit;
import org.cloud.core.net.BaseRetrofit;
import org.cloud.panzer.app.Constant;

/**
 * FileName: LocalRetrofit
 * Author: Admin
 * Date: 2020/11/14 16:39
 * Description: localretrofit
 */
public class LocalRetrofit extends BaseLocalRetrofit {
    private static LocalApiService localHttpService;

    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static LocalApiService getLocalService() {
        if (localHttpService == null) {
            localHttpService = getRawRetrofit(Constant.LOCAL_URL).create(LocalApiService.class);
        }
        return localHttpService;
    }
}
