package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface GoodsContract {

    interface View extends IView {
        void showGoodsDetailSuccess(BaseBean baseBean);

        void showAddGoodsSuccess(BaseBean baseBean);

        void showCalcSuccess(BaseBean baseBean);

        void showGoodsImagesData(String goodsInfoData);
    }

    interface Model extends IModel {
        Observable<String> getGoodsDetailData(String id);
        Observable<String> cartAddGoods(String goodsId, String quantity);
        Observable<String> calc(String goodsId, String areaId);
        Observable<String> getGoodsImagesData(String id);
    }
}
