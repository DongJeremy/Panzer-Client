package org.cloud.panzer.api.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * FileName: MemberFavoritesService
 * Author: Admin
 * Date: 2020/11/11 15:30
 * Description: MemberFavoritesService
 */
public interface MemberFavoritesService {
    @FormUrlEncoded
    @POST("index.php?act=member_favorites&op=favorites_add")
    Observable<String> favoritesAdd(@Field("goods_id") String id);

    @FormUrlEncoded
    @POST("index.php?act=member_favorites&op=favorites_del")
    Observable<String> favoritesDel(@Field("fav_id") String id);

    @FormUrlEncoded
    @POST("index.php?act=member_favorites&op=favorites_info")
    Observable<String> favoritesInfo(@Field("fav_id") String id);

    @GET("index.php?act=member_favorites&op=favorites_list")
    Observable<String> favoritesList(@Query("page") String page, @Query("curpage") String curpage);

    @FormUrlEncoded
    @POST("index.php?act=member_favorites_store&op=favorites_add")
    Observable<String> favoritesStoreAdd(@Field("store_id") String id);

    @FormUrlEncoded
    @POST("index.php?act=member_favorites_store&op=favorites_del")
    Observable<String> favoritesStoreDel(@Field("store_id") String id);

    @GET("index.php?act=member_favorites_store&op=favorites_list")
    Observable<String> favoritesStoreList(@Query("page") String page, @Query("curpage") String curpage);
}
