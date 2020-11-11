package org.cloud.panzer.ui.common;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.cloud.core.base.BaseActivity;
import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseConstant;
import org.cloud.core.base.BaseCountTime;
import org.cloud.core.base.BaseDialog;
import org.cloud.core.base.BaseMvpActivity;
import org.cloud.core.utils.JsonUtils;
import org.cloud.core.utils.StatusBarUtils;
import org.cloud.panzer.App;
import org.cloud.panzer.R;
import org.cloud.panzer.mvp.contract.RegisterContract;
import org.cloud.panzer.mvp.contract.SplashContract;
import org.cloud.panzer.mvp.presenter.RegisterPresenter;
import org.cloud.panzer.mvp.presenter.SplashPresenter;
import org.cloud.panzer.ui.main.MainActivity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.dueeeke.videoplayer.player.VideoViewManager.getConfig;

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

    // 订阅的请求进行统一管理
    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

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
                "android.permission.ACCESS_FINE_LOCATION",
                "android.permission.ACCESS_COARSE_LOCATION",
                "android.permission.WRITE_EXTERNAL_STORAGE"};
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == -1 || verifyPermissions(iArr)) {
            this.isCheck = false;
            getConfig();
            return;
        }
        this.isCheck = true;
        //App.getInstance().startApplicationSetting(getActivity(), getPackageName());
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
        if(this.versionControl.contains(App.getInstance().getVersion() + ":1")) {
            BaseDialog.getInstance().queryConfirmYourChoice(getActivity(), "当前版本已弃用，请更新",
                    (DialogInterface.OnClickListener) (dialogInterface, i) -> this.downloadApk(),
                    (DialogInterface.OnClickListener) (dialogInterface, i) -> App.getInstance().finish(getActivity())
            );
        } else if (!this.appVersion.equals(App.getInstance().getVersion())) {
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
        if (findPermissions != null && findPermissions.size() > 0) {
            ActivityCompat.requestPermissions(this, (String[]) findPermissions.toArray(new String[0]), 0);
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

    private void downloadApk() {

    }
}