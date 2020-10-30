package org.cloud.panzer.api;

import org.cloud.panzer.mvp.model.HomeInfoModel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    public static final String BASE_URL="https://shopai.yokey.top/";

    @GET("api/mobile/index.php?w=index")
    Observable<HomeInfoModel.HomeInfo> getHomeInfoData();
}
