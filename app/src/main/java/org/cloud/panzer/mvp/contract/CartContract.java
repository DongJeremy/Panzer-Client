package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface CartContract {

    interface View extends IView {
        void showCartListData(String cartListData);
        void showCartEditQuantity(String cartEditData);
        void showCartDeleteData(int position, int positionGoods, String cartListData);
    }

    interface Model extends IModel {
        Observable<String> getCartListData(String key);
        Observable<String> cartEditQuantity(String key, String cartId, String quantity);
        Observable<String> cartDelete(String key, String cartId);
    }
}
