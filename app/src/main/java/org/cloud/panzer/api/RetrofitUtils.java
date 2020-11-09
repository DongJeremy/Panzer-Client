package org.cloud.panzer.api;

import org.cloud.core.net.BaseRetrofit;
import org.cloud.panzer.app.Constant;

public class RetrofitUtils extends BaseRetrofit {
    private static ApiService httpJsonService;
    private static ApiService httpService;

    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static ApiService getHttpService() {
        if (httpJsonService == null) {
            httpJsonService = getRetrofit(Constant.BASE_URL).create(ApiService.class);
        }
        return httpJsonService;
    }

    public static ApiService getRawHttpService() {
        if (httpService == null) {
            httpService = getRawRetrofit(Constant.BASE_URL).create(ApiService.class);
        }
        return httpService;
    }
}
