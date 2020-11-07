package org.cloud.panzer.api;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MemberService {

    @FormUrlEncoded
    @POST("index.php?act=login&op=index&client=wap")
    Observable<String> login(@Field("username") String username, @Field("password") String password, @Field("client") String client);

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
}
