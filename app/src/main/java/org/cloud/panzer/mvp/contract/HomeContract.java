package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;
import org.cloud.panzer.mvp.model.HomeInfoModel;

import java.util.List;

import io.reactivex.Observable;

public interface HomeContract {

    interface View extends IView {
        void showHomeInfoData(List<HomeInfoModel.HomeInfo.Item> homeGrids);
    }

    interface Model extends IModel {
        Observable<HomeInfoModel.HomeInfo> getHomeInfoData();
    }
}
