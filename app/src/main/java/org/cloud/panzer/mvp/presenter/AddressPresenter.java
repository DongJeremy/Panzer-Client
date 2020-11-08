package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.AddressContract;
import org.cloud.panzer.mvp.model.AddressModel;

public class AddressPresenter extends BasePresenter<AddressContract.Model, AddressContract.View> {
    @Override
    protected AddressContract.Model createModel() {
        return new AddressModel();
    }

    public void requestAddressList() {
        getModel().doAddressList()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAddressList(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestAddressAdd(String trueName, String mobPhone, String cityId, String areaId, String address, String areaInfo,
                                  String isDefault) {
        getModel().doAddressAdd(trueName, mobPhone, cityId, areaId, address, areaInfo, isDefault)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAddressAdd(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });

    }

    public void requestAddressEdit(String addressId, String trueName, String mobPhone, String cityId, String areaId, String address,
                                   String areaInfo, String isDefault) {
        getModel().doAddressEdit(addressId, trueName, mobPhone, cityId, areaId, address, areaInfo, isDefault)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAddressEdit(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });
    }

    public void requestAddressDelete(String addressId) {
        getModel().doAddressDelete(addressId)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAddressDelete(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showError(errMsg);
                    }
                });
    }
}
