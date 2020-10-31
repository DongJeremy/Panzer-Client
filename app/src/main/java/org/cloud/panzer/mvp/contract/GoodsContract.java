package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface GoodsContract {

    interface View extends IView {
        void showGoodsDetailData(String homeInfoData);
    }

    interface Model extends IModel {
        Observable<String> getGoodsDetailData(String id);
    }
}
