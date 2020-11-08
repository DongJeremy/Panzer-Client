package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface GoodsContract {

    interface View extends IView {
        void showGoodsDetailSuccess(BaseBean baseBean);
        void showGoodsImagesData(String goodsInfoData);
        void showAddGoodsSuccess(BaseBean baseBean);
    }

    interface Model extends IModel {
        Observable<String> getGoodsDetailData(String id);
        Observable<String> getGoodsImagesData(String id);
        Observable<String> cartAddGoods(String goodsId, String quantity);
    }
}
