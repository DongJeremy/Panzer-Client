package org.cloud.panzer.mvp.contract;

import org.cloud.core.base.BaseBean;
import org.cloud.core.mvp.IModel;
import org.cloud.core.mvp.IView;

import io.reactivex.Observable;

public interface StoreHomeContract {

    interface View extends IView {
        void showFavoritesAddSuccess(BaseBean baseBean);
        void showFavoritesDelSuccess(BaseBean baseBean);
        void showFavoritesInfoSuccess(BaseBean baseBean);
        void showFavoritesStoreAddSuccess(BaseBean baseBean);
        void showFavoritesStoreDelSuccess(BaseBean baseBean);
    }

    interface Model extends IModel {
        Observable<String> favoritesAdd(String id);
        Observable<String> favoritesDel(String id);
        Observable<String> favoritesInfo(String id);
        Observable<String> favoritesStoreAdd(String id);
        Observable<String> favoritesStoreDel(String id);
    }
}
