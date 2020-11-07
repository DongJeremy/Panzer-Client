package org.cloud.panzer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.cloud.core.base.BaseApplication;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.GlideImageLoader;
import org.cloud.panzer.bean.GoodsSearchData;
import org.cloud.panzer.ui.common.FindPassActivity;
import org.cloud.panzer.ui.common.LoginActivity;
import org.cloud.panzer.ui.common.RegisterActivity;
import org.cloud.panzer.ui.goods.BuyActivity;
import org.cloud.panzer.ui.goods.GoodsActivity;
import org.cloud.panzer.ui.goods.ListActivity;
import org.cloud.panzer.ui.home.ChatListActivity;
import org.cloud.panzer.ui.main.MainActivity;
import org.cloud.panzer.ui.order.OrderActivity;
import org.cloud.panzer.ui.order.PayActivity;
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
        startForResult(activity, intent, BaseConstant.CODE_ALBUM);
    }

    public void startStore(Activity activity, String storeId) {
        Intent intent = new Intent(activity, StoreActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, storeId);
        start(activity, intent);
    }

    public void startGoodsList(Activity activity, GoodsSearchData goodsSearchData) {
        Intent intent = new Intent(activity, ListActivity.class);
        intent.putExtra(BaseConstant.DATA_BEAN, goodsSearchData);
        start(activity, intent);
    }

    public void startGoodsBuy(Activity activity, String str, String str2) {
        Intent intent = new Intent(activity, BuyActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, str);
        intent.putExtra(BaseConstant.DATA_IFCART, str2);
        startCheckLogin(activity, intent);
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

    public void startCheckLogin(Activity activity, Intent intent) {
        if (isLogin()) {
            start(activity, intent);
        } else {
            start(activity, LoginActivity.class);
        }
    }

    public void startOrder(Activity activity, int position) {
        Intent intent = new Intent(activity, OrderActivity.class);
        intent.putExtra(BaseConstant.DATA_POSITION, position);
        startCheckLogin(activity, intent);
    }

    public void startOrderPay(Activity activity, String paySn) {
        Intent intent = new Intent(activity, PayActivity.class);
        intent.putExtra(BaseConstant.DATA_ID, paySn);
        start(activity, intent);
    }

    public void startImagePicker(Activity activity, int selectLimit, int code, boolean crop) {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        imagePicker.setMultiMode(selectLimit != 1);
        imagePicker.setSelectLimit(selectLimit);
        imagePicker.setShowCamera(true);
        if (crop) {
            imagePicker.setCrop(true);
            imagePicker.setSaveRectangle(true);
            imagePicker.setStyle(CropImageView.Style.RECTANGLE);
            imagePicker.setFocusHeight(800);
            imagePicker.setFocusWidth(800);
            imagePicker.setOutPutX(800);
            imagePicker.setOutPutY(800);
        } else {
            imagePicker.setCrop(false);
        }
        start(activity, new Intent(this, ImageGridActivity.class), code);
    }

    public void finishOk(Activity activity) {
        activity.setResult(-1);
        finish(activity);
    }

    public void finishOk(Activity activity, Intent intent) {
        activity.setResult(-1, intent);
        finish(activity);
    }

}
