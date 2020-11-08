package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import java.util.Map;

import io.reactivex.Observable;

public interface GoodsBuyContract {

    interface View extends IView {
        void showGoodsBuyStep1Success(BaseBean baseBean);
        void showGoodsBuyStep1Fail(String msg);

        void showGoodsBuyStep2Success(BaseBean baseBean);
        void showGoodsBuyStep2Fail(String msg);
    }

    interface Model extends IModel {
        Observable<String> buySetp1(String cardId, String ifCart, String addressId);
        Observable<String> buySetp2(Map<String, String> maps);
    }
}
