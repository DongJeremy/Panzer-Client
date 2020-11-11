package org.cloud.panzer.api.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * FileName: GoodsService
 * Author: Admin
 * Date: 2020/11/11 9:03
 * Description: GoodsService
 */
public interface StoreService {
    @FormUrlEncoded
    @POST("index.php?act=store&op=store_info")
    Observable<String> storeInfo(@Field("store_id") String id);
}
