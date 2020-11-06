package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface CartContract {

    interface View extends IView {
        void showCartListData(String cartListData);
        void showCartEditQuantity(String cartEditData);
        void showCartDeleteData(int position, int positionGoods, String cartListData);
    }

    interface Model extends IModel {
        Observable<String> getCartListData();
        Observable<String> cartEditQuantity(String cartId, String quantity);
        Observable<String> cartDelete(String cartId);
    }
}
