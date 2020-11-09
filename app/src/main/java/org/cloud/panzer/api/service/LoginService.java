package org.cloud.panzer.api.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * FileName: LoginService
 * Author: Admin
 * Date: 2020/11/9 16:17
 * Description: login
 */
public interface LoginService {

    @FormUrlEncoded
    @POST("index.php?act=login&op=index")
    Observable<String> login(@Field("username") String username, @Field("password") String password, @Field("client") String client);

    @FormUrlEncoded
    @POST("index.php?act=login&op=register")
    Observable<String> register(@Field("username") String username, @Field("password") String password,  @Field("password_confirm") String confirm,
                                @Field("email") String email, @Field("client") String client);
}
