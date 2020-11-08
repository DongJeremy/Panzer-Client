package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface OrderContract {

    interface View extends IView {
        void showOrderListSuccess(BaseBean baseBean);
        void showOrderListFail(String jsonData);

        void showOrderDeleteSuccess(BaseBean baseBean);
        void showOrderDeleteFail(String jsonData);

        void showOrderCancelSuccess(BaseBean baseBean);
        void showOrderCancelFail(String jsonData);

        void showOrderReceiveSuccess(BaseBean baseBean);
        void showOrderReceiveFail(String jsonData);
    }

    interface Model extends IModel {
        Observable<String> postMemberOrderList(String stateType, String orderKey, String page, String curpage);
        Observable<String> postMemberOrderDelete(String orderId);
        Observable<String> postMemberOrderCancel(String orderId);
        Observable<String> postMemberOrderReceive(String orderId);
    }
}
