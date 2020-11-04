package org.cloud.panzer.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL="https://www.wpccw.com/mobile/";

    @GET("index.php?w=index")
    Observable<String> getHomeInfoData();

    @GET("index.php?act=index&op=getgg&ac_id=1")
    Observable<String> getArticleListData();

    @GET("index.php?act=goods&op=goods_body")
    Observable<String> getGoodsImagesData(@Query("goods_id") String id);

    @GET("index.php?act=goods&op=goods_detail")
    Observable<String> getGoodsDetailData(@Query("goods_id") String id);
}
