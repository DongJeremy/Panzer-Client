package org.cloud.panzer.api.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IndexService {

    @GET("index.php?w=index")
    Observable<String> getHomeInfoData();

    @GET("index.php?act=index&op=getgg&ac_id=1")
    Observable<String> getArticleListData();

    @GET("index.php?act=goods&op=goods_body")
    Observable<String> getGoodsImagesData(@Query("goods_id") String id);

    @GET("index.php?act=goods&op=goods_detail")
    Observable<String> getGoodsDetailData(@Query("goods_id") String id);

    @GET("index.php?act=member_cart&op=cart_list")
    Observable<String> getCartListData();

    @FormUrlEncoded
    @POST("index.php?act=member_cart&op=cart_edit_quantity")
    Observable<String> cartEditQuantity(@Field("cart_id") String cartId, @Field("quantity") String quantity);

    @FormUrlEncoded
    @POST("index.php?act=member_cart&op=cart_del")
    Observable<String> cartDelete(@Field("cart_id") String cartId);

    @FormUrlEncoded
    @POST("index.php?act=member_cart&op=cart_add")
    Observable<String> cartAdd(@Field("goods_id") String goodsId, @Field("quantity") String quantity);

    @GET("index.php?act=index&op=search_key_list")
    Observable<String> getSearchKeyList();
}
