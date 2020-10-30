package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.HomeContract;

import io.reactivex.Observable;

public class HomeInfoModel extends BaseModel implements HomeContract.Model {
    @Override
    public Observable<HomeInfo> getHomeInfoData() {
        return RetrofitUtils.getHttpService().getHomeInfoData();
    }

    public static class HomeInfo {

        private int code;
        private Data datas;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Data getDatas() {
            return datas;
        }

        public void setDatas(Data datas) {
            this.datas = datas;
        }

        public static class Data {
            private Index index;
            private Seo seo;
            private String sm_info;
        }
    }
}
