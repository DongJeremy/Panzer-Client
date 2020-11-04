package org.cloud.panzer.mvp.contract;

import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface HomeContract {

    interface View extends IView {
        void showHomeInfoData(String homeInfoData);
        void showArticleListData(String articleListData);
    }

    interface Model extends IModel {
        Observable<String> getHomeInfoData();
        Observable<String> getArticleListData();
    }
}
