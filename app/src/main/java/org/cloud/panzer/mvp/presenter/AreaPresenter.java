package org.cloud.panzer.mvp.presenter;

import android.util.Log;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.AreaContract;
import org.cloud.panzer.mvp.model.AreaModel;

public class AreaPresenter extends BasePresenter<AreaContract.Model, AreaContract.View> {
    @Override
    protected AreaContract.Model createModel() {
        return new AreaModel();
    }

    public void requestArea(String id, final String type) {
        getModel().getAreaListById(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showAreaList(JsonUtils.parseJsonData(result), type);
                    }
                    @Override
                    public boolean isSuccessFul(String result) {
                        return JsonUtils.checkJsonCodeSuccess(result);
                    }
                    @Override
                    public void onLogicError() {
                    }
                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        Log.e("ERROR", errMsg);
                        getView().showError(errMsg);
                    }
                });
    }
}
