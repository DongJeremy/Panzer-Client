package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface SearchContract {

    interface View extends IView {
        void showSearchKeyList(String searchKeyList);
    }

    interface Model extends IModel {
        Observable<String> getSearchKeyList();
    }
}
