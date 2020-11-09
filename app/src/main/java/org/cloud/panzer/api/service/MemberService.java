package org.cloud.panzer.api.service;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MemberService {

    @POST("index.php?act=member_index&op=index")
    Observable<String> memberIndex();

    @POST("index.php?act=member_address&op=address_list")
    Observable<String> memberAddressList();

    @FormUrlEncoded
    @POST("index.php?act=member_address&op=address_add")
    Observable<String> memberAddressAdd(@Field("true_name") String trueName, @Field("mob_phone") String mobPhone, @Field("city_id") String cityId,
                                        @Field("area_id") String areaId, @Field("address") String address, @Field("area_info") String areaInfo,
                                        @Field("is_default") String isDefault);

    @FormUrlEncoded
    @POST("index.php?act=member_address&op=address_del")
    Observable<String> memberAddressDelete(@Field("address_id") String addressId);

    @FormUrlEncoded
    @POST("index.php?act=member_address&op=address_edit")
    Observable<String> memberAddressEdit(@Field("address_id") String addressId, @Field("true_name") String trueName,
                                         @Field("mob_phone") String mobPhone, @Field("city_id") String cityId,
                                         @Field("area_id") String areaId, @Field("address") String address,
                                         @Field("area_info") String areaInfo, @Field("is_default") String isDefault);

    @FormUrlEncoded
    @POST("index.php?act=member_buy&op=buy_step1")
    Observable<String> memberBuyStep1(@Field("cart_id") String cartId, @Field("ifcart") String ifcart, @Field("address_id") String addressId);

    @FormUrlEncoded
    @POST("index.php?act=member_buy&op=buy_step2")
    Observable<String> memberBuyStep2(@FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @POST("index.php?act=member_buy&op=pay")
    Observable<String> memberPay(@Field("pay_sn") String paySn);

    @FormUrlEncoded
    @POST("index.php?act=member_order&op=order_list")
    Observable<String> memberOrderList(@Field("state_type") String stateType, @Field("order_key") String orderKey, @Field("page") String page,
                                       @Field("curpage") String curpage);

    @FormUrlEncoded
    @POST("index.php?act=member_order&op=order_delete")
    Observable<String> memberOrderDelete(@Field("order_id") String orderId);

    @FormUrlEncoded
    @POST("index.php?act=member_order&op=order_cancel")
    Observable<String> memberOrderCancel(@Field("order_id") String orderId);

    @FormUrlEncoded
    @POST("index.php?act=member_order&op=order_receive")
    Observable<String> memberOrderReceive(@Field("order_id") String orderId);

    @GET("index.php?act=member_payment&op=pay_new")
    Observable<String> getPayNew(@Query("pay_sn") String paySn, @Query("password") String password, @Query("rcb_pay") String rcbPay,
                                 @Query("pd_pay") String pdPay, @Query("payment_code") String paymentCode);

    @FormUrlEncoded
    @POST("index.php?act=member_payment&op=wx_app_pay3")
    Observable<String> wxAppPay3(@Field("pay_sn") String paySn);

    @FormUrlEncoded
    @POST("index.php?act=member_payment&op=pay_new")
    Observable<String> checkPdPwd(@Field("password") String password);
}
