package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface StoreContract {

    interface View extends IView {
        void showStoreInfoSuccess(BaseBean baseBean);
        void showStoreInfoFail(String reason);

        void showVoucherTplListSuccess(BaseBean baseBean);
        void showVoucherTplListFail(String reason);
    }

    interface Model extends IModel {
        Observable<String> storeInfo(String id);
        Observable<String> voucherTplList(String id, String gettype);
    }
}
