package org.cloud.panzer.api.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AreaService {

    @GET("index.php?act=area&op=area_list")
    Observable<String> getAreaList(@Query("area_id") String areaId);
}
