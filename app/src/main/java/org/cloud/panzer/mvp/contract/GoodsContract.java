package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;
import org.cloud.panzer.mvp.model.HomeInfoModel;

import io.reactivex.Observable;

public interface GoodsContract {

    interface View extends IView {
        void showHomeInfoData(HomeInfoModel.HomeInfo.Data homeInfoData);
    }

    interface Model extends IModel {
        Observable<HomeInfoModel.HomeInfo> getHomeInfoData();
    }
}
