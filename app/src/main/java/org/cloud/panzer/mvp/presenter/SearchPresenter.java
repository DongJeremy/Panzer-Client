package org.cloud.panzer.mvp.presenter;

import android.util.Log;

import org.cloud.core.mvp.BasePresenter;
import org.cloud.core.net.BaseObserver;
import org.cloud.core.rx.RxSchedulers;
import org.cloud.panzer.mvp.contract.SearchContract;
import org.cloud.panzer.mvp.model.SearchModel;

public class SearchPresenter extends BasePresenter<SearchContract.Model, SearchContract.View> {
    @Override
    protected SearchContract.Model createModel() {
        return new SearchModel();
    }

    public void requestGridData() {
        getModel().getHomeInfoData()
                .compose(RxSchedulers.applySchedulers(getLifecycleProvider()))
                .subscribe(new BaseObserver<String>(getView()){
                    @Override
                    public void onSuccess(String result) {
                        getView().showHomeInfoData(result);
                    }

                    @Override
                    public void onFailure(String errMsg, boolean isNetError) {
                        Log.e("ERROR", errMsg);
                        getView().showError(errMsg);
                    }
                });

    }
}