package org.cloud.panzer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseShared;
import org.cloud.panzer.ui.common.FindPassActivity;
import org.cloud.panzer.ui.common.LoginActivity;
import org.cloud.panzer.ui.common.RegisterActivity;
import org.cloud.panzer.ui.goods.GoodsActivity;
import org.cloud.panzer.ui.home.ChatListActivity;
import org.cloud.panzer.ui.main.MainActivity;
import org.cloud.panzer.ui.store.StoreActivity;

import io.github.xudaojie.qrcodelib.CaptureActivity;

public class App extends BaseApplication {

    public static final String TAG = "PANZER_DEBUG";

    private static App mInstance;

    private boolean isNormalMember;

    public static App getInstance() {
        return mInstance;
    }

    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //检测内存泄漏
        initLeakCanary();
        this.isNormalMember = true;
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        mRefWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.mRefWatcher;
    }

    public boolean isNormalMember() {
        return isNormalMember;
    }

    public void setNormalMember(boolean normalMember) {
        isNormalMember = normalMember;
    }

    public void startGoods(Activity activity, String goodsId) {
        Intent intent = new Intent(activity, GoodsActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, goodsId);
        start(activity, intent);
    }

    public void startRegister(Activity activity) {
        Intent intent = new Intent(activity, RegisterActivity.class);
        start(activity, intent);
    }

    public void startFindPass(Activity activity) {
        Intent intent = new Intent(activity, FindPassActivity.class);
        start(activity, intent);
    }

    public void startMain(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        start(activity, intent);
    }

    public void startChatList(Activity activity) {
        Intent intent = new Intent(activity, ChatListActivity.class);
        start(activity, intent);
    }

    public void startCapture(Activity activity) {
        Intent intent = new Intent(activity, CaptureActivity.class);
        startForResult(activity, intent, 1003);
    }

    public void startStore(Activity activity, String storeId) {
        Intent intent = new Intent(activity, StoreActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, storeId);
        start(activity, intent);
    }

    public void startLogin(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        start(activity, intent);
    }

    public void startCheckLogin(Activity activity, Class<?> cls) {
        if (isLogin()) {
            start(activity, cls);
        } else {
            start(activity, LoginActivity.class);
        }
    }
}
