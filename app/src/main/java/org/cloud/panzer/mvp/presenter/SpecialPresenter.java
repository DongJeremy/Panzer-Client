package org.cloud.panzer.mvp.presenter;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.core.utils.JsonUtils;
import org.cloud.panzer.mvp.contract.SpecialContract;
import org.cloud.panzer.mvp.model.SpecialModel;

public class SpecialPresenter extends BasePresenter<SpecialContract.Model, SpecialContract.View> {
    @Override
    protected SpecialContract.Model createModel() {
        return new SpecialModel();
    }

    public void requestSpecialData(String id) {
        getModel().special(id)
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()) {
                    @Override
                    public void onSuccess(String result) {
                        getView().showSpecialSuccess(JsonUtils.parseJsonToBaseBean(result));
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        getView().showSpecialFail(errMsg);
                    }
                });

    }
}
