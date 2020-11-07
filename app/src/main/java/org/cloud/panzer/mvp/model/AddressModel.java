package org.cloud.panzer.mvp.model;

import org.cloud.core.mvp.BaseModel;
import org.cloud.panzer.api.RetrofitUtils;
import org.cloud.panzer.mvp.contract.AddressContract;

import io.reactivex.Observable;

public class AddressModel extends BaseModel implements AddressContract.Model {
    @Override
    public Observable<String> doAddressList() {
        return RetrofitUtils.getRawHttpService().memberAddressList();
    }

    @Override
    public Observable<String> doAddressAdd(String trueName, String mobPhone, String cityId, String areaId, String address, String areaInfo,
                                            String isDefault) {
        return RetrofitUtils.getRawHttpService().memberAddressAdd(trueName, mobPhone, cityId, areaId, address, areaInfo, isDefault);
    }

    @Override
    public Observable<String> doAddressDelete(String addressId) {
        return RetrofitUtils.getRawHttpService().memberAddressDelete(addressId);
    }

    @Override
    public Observable<String> doAddressEdit(String addressId, String trueName, String mobPhone, String cityId, String areaId, String address, String areaInfo, String isDefault) {
        return RetrofitUtils.getRawHttpService().memberAddressEdit(addressId, trueName, mobPhone, cityId, areaId, address, areaInfo, isDefault);
    }
}
