package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface CartContract {

    interface View extends IView {
        void showCartListData(BaseBean baseBean);
        void showCartEditQuantity(BaseBean baseBean);
        void showCartDeleteData(int position, int positionGoods, BaseBean baseBean);
    }

    interface Model extends IModel {
        Observable<String> getCartListData();
        Observable<String> cartEditQuantity(String cartId, String quantity);
        Observable<String> cartDelete(String cartId);
    }
}
