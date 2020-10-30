package org.cloud.panzer.api;

import org.cloud.panzer.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String BASE_URL="https://shopai.yokey.top/api/mobile/";

    @GET("index.php?w=index")
    Observable<BaseBean> getHomeInfoData();
}
