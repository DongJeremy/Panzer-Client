package org.cloud.panzer.api.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * FileName: ConnectionService
 * Author: Admin
 * Date: 2020/11/9 16:20
 * Description: connect
 */
public interface ConnectionService {

    @GET("index.php?act=connect&op=get_state")
    Observable<String> getState(@Query("t") String t);

    @GET("index.php?act=connect&op=check_sms_captcha")
    Observable<String> checkSmsCaptcha(@Query("type") String type, @Query("phone") String phone, @Query("captcha") String captcha);

    @FormUrlEncoded
    @POST("index.php?act=connect&op=sms_register")
    Observable<String> smsRegister(@Field("phone") String phone, @Field("captcha") String captcha, @Field("password") String password,
                                   @Field("client") String client);

    @FormUrlEncoded
    @POST("index.php?act=connect&op=find_password")
    Observable<String> findPassword(@Field("phone") String phone, @Field("captcha") String captcha, @Field("password") String password,
                                    @Field("client") String client);

    @GET("index.php?act=connect&op=get_sms_captcha")
    Observable<String> getSmsCaptcha(@Query("type") String type, @Query("phone") String phone, @Query("sec_val") String secVal,
                                     @Query("sec_key") String secKey);

}
