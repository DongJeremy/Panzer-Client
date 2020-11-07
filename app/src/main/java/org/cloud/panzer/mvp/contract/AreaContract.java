package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface AreaContract {

    interface View extends IView {
        void showAreaList(String areaData, String type);
    }

    interface Model extends IModel {
        Observable<String> getAreaListById(String id);
    }
}
