package org.cloud.panzer.ui.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.cloud.core.app.AppManager;
import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseCountTime;
import org.cloud.core.base.BaseDialog;
import org.cloud.core.base.BaseFileClient;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.base.BaseToast;
import org.cloud.core.utils.AppUtils;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.Utils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.SplashContract;
import org.cloud.panzer.mvp.presenter.SplashPresenter;
import org.cloud.panzer.ui.main.MainActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SplashActivity extends BaseMvpActivity<SplashPresenter> implements SplashContract.View {

    @BindView(R.id.mainImageView)
    AppCompatImageView mainImageView;

    public ProgressDialog progressDialog;
    public String advertLink;
    public String advertUrl;
    public String apkUrl;
    public String appVersion;
    private boolean isCheck;
    private boolean isPush;
    public boolean isSuccess;

    private String[] permissions;
    private String pushUrl;
    public String updateContent;
    public String versionControl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        App.getInstance().setFullScreen(getActivity());
    }

    @Override
    protected void initView() {
        //StatusBarUtils.setImageNoStatusBar(this, false);
        this.apkUrl = "";
        this.advertUrl = "";
        this.advertLink = "";
        this.appVersion = "";
        this.updateContent = "";
        this.versionControl = "";
        this.isCheck = true;
        this.isSuccess = false;
        this.permissions = new String[]{
                "android.permission.CAMERA",
                "android.permission.CALL_PHONE",
                "android.permission.RECORD_AUDIO",
                "android.permission.READ_PHONE_STATE",
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.WRITE_EXTERNAL_STORAGE"};
        mainImageView.setBackgroundResource(R.mipmap.bg_load);
        App.getInstance().setFullScreen(getActivity());
    }

    @Override
    protected void initListener() {
        this.mainImageView.setOnClickListener(view -> {
            if (this.isSuccess && !TextUtils.isEmpty(this.advertUrl)) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("url", this.advertUrl);
                App.getInstance().start(getActivity(), intent);
                App.getInstance().finish(getActivity());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.isCheck) {
            checkPermissions(this.permissions);
        }
    }

    public void onReturn() {
        App.getInstance().startHome(getActivity());
    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter();
    }

    /**
     * 用户选择允许或拒绝后，会回调onRequestPermissionsResult方法, 该方法类似于onActivityResult
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == -1 || verifyPermissions(grantResults)) {
            this.isCheck = false;
            getConfig();
            return;
        }
        this.isCheck = true;
        App.getInstance().startApplicationSetting(getActivity(), getPackageName());
    }

    @Override
    public void showGetAndroidSuccess(BaseBean baseBean) {
        JsonObject jsonObject = JsonUtils.parseJsonToJsonObject(baseBean.getDatas());
        this.isSuccess = true;
        JsonArray androidConfig = jsonObject.getAsJsonArray("android_config");
        for (int i = 0; i < androidConfig.size(); i++) {
            JsonObject item = androidConfig.get(i).getAsJsonObject();
            String value = item.get("value").getAsString();
            switch (item.get("name").getAsString()) {
                case "android_version_control":
                    this.versionControl = value;
                    break;
                case "android_update_content":
                    this.updateContent = value;
                    break;
                case "android_app_version":
                    this.appVersion = value;
                    break;
                case "android_advert_link":
                    this.advertLink = value;
                    break;
                case "android_advert_url":
                    this.advertUrl = value;
                    break;
                case "android_apk_url":
                    this.apkUrl = value;
                    break;
            }
        }
        if(!this.versionControl.contains(AppUtils.getAppVersionName(Utils.getContext()) + ":1")) {
            BaseDialog.getInstance().queryConfirmYourChoice(getActivity(), "当前版本已弃用，请更新",
                    (dialog, i) -> downloadApk(),
                    (dialog, i) -> App.getInstance().finish(getActivity())
            );
        } else if (!this.appVersion.equals(AppUtils.getAppVersionName(Utils.getContext()))) {
            BaseDialog.getInstance().queryConfirmYourChoice(getActivity(), this.updateContent,
                    (dialogInterface, i) -> this.downloadApk(),
                    (dialogInterface, i) -> this.startMain()
            );
        } else {
            this.startMain();
        }
    }

    @Override
    public void showGetAndroidFail(String msg) {
        this.isSuccess = false;
        BaseDialog.getInstance().queryConfirmYourChoice(getActivity(), msg,
                (dialogInterface, i) -> getConfig(),
                (dialogInterface, i) -> App.getInstance().startHome(getActivity())
        );
    }

    public void getConfig() {
        mPresenter.requestGetAndroid();
    }

    private void checkPermissions(String... strArr) {
        List<String> findPermissions = findPermissions(strArr);
        if (findPermissions.size() > 0) {
            ActivityCompat.requestPermissions(this, findPermissions.toArray(new String[0]), 0);
        } else if (!this.isSuccess) {
            getConfig();
        }
    }

    private boolean verifyPermissions(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    private List<String> findPermissions(String[] strArr) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : strArr) {
            if (ContextCompat.checkSelfPermission(this, str) != 0) {
                arrayList.add(str);
            } else if (ActivityCompat.shouldShowRequestPermissionRationale(this, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private void startMain() {
        new BaseCountTime(BaseConstant.TIME_TICK, BaseConstant.TIME_TICK) {
            @Override
            public void onFinish() {
                super.onFinish();
                App.getInstance().start(getActivity(), MainActivity.class);
                App.getInstance().finish(getActivity());
            }
        }.start();
    }

    @Override
    public void showDownloadSuccess(File file) {
        AppUtils.installApp(getActivity(), file);
        this.progressDialog.dismiss();
        //reStartApp();
    }

//    public void reStartApp(Context context) {
//        Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(intent);
//        AppManager.getInstance().
//        context.getAppManager().finishAllActivity();
//    }

    @Override
    public void showDownloadFail() {
        this.progressDialog.dismiss();
    }

    @Override
    public void showDownloadStart() {
        BaseToast.getInstance().show("准备开始下载...");
        this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        this.progressDialog.setMessage("正在下载中...");
        this.progressDialog.show();
    }

    @Override
    public void showDownloadProgress(int progress, long total) {
        //this.progressDialog.show();
        this.progressDialog.setMax((int) total);
        this.progressDialog.setProgress(progress);
        this.progressDialog.setProgressNumberFormat(" ");
    }

    private void downloadApk() {
        this.progressDialog = new ProgressDialog(getActivity());
        mPresenter.requestDownloadApk(this.apkUrl, BaseFileClient.getInstance().getDownPath(), "panzer.apk");
    }

    /**
     * 重启app
     * @param context
     */
    public static void restartApp(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (null == packageManager) {
            //LogUtils.errorLog("null == packageManager");
            return;
        }
        final Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
        }
    }

    private void handlerPush(Intent intent) {
//        Bundle extras;
//        if (intent != null && (extras = intent.getExtras()) != null) {
//            for (String str : extras.keySet()) {
//                if (str.equals("msg")) {
//                    this.isPush = true;
//                    HashMap<String, String> extrasMap = ((MobPushNotifyMessage) Objects.requireNonNull((MobPushNotifyMessage) extras.get(str))).getExtrasMap();
//                    if (extrasMap != null && extrasMap.containsKey("url")) {
//                        this.pushUrl = extrasMap.get("url");
//                        if (BaseApplication.get().inActivityStack(getActivity(), MainActivity.class)) {
//                            Intent intent = new Intent(getActivity(), MainActivity.class);
//                            intent.putExtra("url", this.pushUrl);
//                            BaseApplication.get().start(getActivity(), intent);
//                            BaseApplication.get().finish(getActivity());
//                        } else {
//                            getConfig();
//                        }
//                    }
//                }
//            }
//        }
    }
}