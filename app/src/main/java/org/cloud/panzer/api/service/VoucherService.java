package org.cloud.panzer.api.service;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * FileName: VoucherService
 * Author: Admin
 * Date: 2020/11/11 14:43
 * Description: Voucher
 */
public interface VoucherService {
    @FormUrlEncoded
    @POST("index.php?act=voucher&op=voucher_tpl_list")
    Observable<String> voucherTplList(@Field("store_id") String id, @Field("gettype") String gettype);
}
