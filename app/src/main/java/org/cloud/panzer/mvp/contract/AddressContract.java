package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface AddressContract {

    interface View extends IView {
        void showAddressList(String address);
        void showAddressAdd(String address);
        void showAddressDelete(String address);
        void showAddressEdit(String address);
    }

    interface Model extends IModel {
        Observable<String> doAddressList();

        Observable<String> doAddressAdd(String trueName, String mobPhone, String cityId, String areaId, String address, String areaInfo,
                                         String isDefault);

        Observable<String> doAddressDelete(String addressId);

        Observable<String> doAddressEdit(String addressId, String trueName, String mobPhone, String cityId, String areaId, String address,
                                          String areaInfo, String isDefault);
    }
}
