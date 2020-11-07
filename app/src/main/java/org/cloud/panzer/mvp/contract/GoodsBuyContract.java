package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import java.util.Map;

import io.reactivex.Observable;

public interface GoodsBuyContract {

    interface View extends IView {
        void showGoodsBuyStep1Success(String goodsInfoData);

        void showGoodsBuyStep2Success(String goodsInfoData);
        void showGoodsBuyStep2Error();
    }

    interface Model extends IModel {
        Observable<String> buySetp1(String cardId, String ifCart, String addressId);
        Observable<String> buySetp2(Map<String, String> maps);
    }
}
