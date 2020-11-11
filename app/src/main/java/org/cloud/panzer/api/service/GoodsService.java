package org.cloud.panzer.api.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * FileName: GoodsService
 * Author: Admin
 * Date: 2020/11/11 9:03
 * Description: GoodsService
 */
public interface GoodsService {
    @GET("index.php?act=goods&op=goods_body")
    Observable<String> getGoodsImagesData(@Query("goods_id") String id);

    @GET("index.php?act=goods&op=goods_detail")
    Observable<String> getGoodsDetailData(@Query("goods_id") String id);

    @GET("index.php?act=goods&op=calc")
    Observable<String> calc(@Query("goods_id") String goodsId, @Query("area_id") String areaId);

    @GET("index.php?act=goods&op=goods_evaluate")
    Observable<String> goodsEvaluate(@Query("goods_id") String goodsId, @Query("type") String type, @Query("page") String page, @Query("curpage") String curpage);
}
