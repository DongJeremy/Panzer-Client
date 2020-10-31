package org.cloud.panzer.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL="https://shopai.yokey.top/api/mobile/";

    @GET("index.php?w=index")
    Observable<String> getHomeInfoData();

    @GET("index.php?w=goods&t=goods_detail")
    Observable<String> getGoodsDetailData(@Query("goods_id") String id);
}
