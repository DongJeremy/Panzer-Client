package org.cloud.panzer.ui.common;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.cloud.core.base.BaseActivity;
import org.cloud.core.base.BaseBean;
import org.cloud.core.base.BaseMvpActivity;
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
        //App.getInstance().startHome(getActivity());
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
        App.getInstance().startApplicationSetting(getActivity(), getPackageName());
    }

    @Override
    public void showGetAndroidSuccess(BaseBean baseBean) {
        JSONArray jSONArray = new JSONArray(JsonUtil.getDatasString(baseBean.getDatas(), "android_config"));
        boolean unused = LoadActivity.this.isSuccess = true;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.getString(c.e).equals("android_version_control")) {
                String unused2 = LoadActivity.this.versionControl = jSONObject.getString("value");
            } else if (jSONObject.getString(c.e).equals("android_update_content")) {
                String unused3 = LoadActivity.this.updateContent = jSONObject.getString("value");
            } else if (jSONObject.getString(c.e).equals("android_app_version")) {
                String unused4 = LoadActivity.this.appVersion = jSONObject.getString("value");
            } else if (jSONObject.getString(c.e).equals("android_advert_link")) {
                String unused5 = LoadActivity.this.advertLink = jSONObject.getString("value");
            } else if (jSONObject.getString(c.e).equals("android_advert_url")) {
                String unused6 = LoadActivity.this.advertUrl = jSONObject.getString("value");
            } else if (jSONObject.getString(c.e).equals("android_apk_url")) {
                String unused7 = LoadActivity.this.apkUrl = jSONObject.getString("value");
            }
        }
        String access$100 = LoadActivity.this.versionControl;
        if (!access$100.contains(BaseApplication.get().getVersion() + ":1")) {
            BaseDialog.get().queryConfirmYourChoice(LoadActivity.this.getActivity(), "当前版本已弃用，请更新", new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.downloadApk();
                }
            }, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    BaseApplication.get().finish(LoadActivity.this.getActivity());
                }
            });
        } else if (!LoadActivity.this.appVersion.equals(BaseApplication.get().getVersion())) {
            BaseDialog.get().queryConfirmYourChoice(LoadActivity.this.getActivity(), LoadActivity.this.updateContent, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    LoadActivity.this.downloadApk();
                    //LoadActivity.AnonymousClass1.this.lambda$onSuccess$2$LoadActivity$1(dialogInterface, i);
                }
            }, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    LoadActivity.this.startMain();
                }
            });
        } else {
            LoadActivity.this.startMain();
        }
    }

    @Override
    public void showGetAndroidFail(String msg) {

    }

    public void getConfig() {
        mPresenter.requestGetAndroid();

        IndexModel.get().getAndroid(new BaseHttpListener() {
            public void onSuccess(BaseBean baseBean) {
                try {

                } catch (JSONException e) {
                    BaseDialog.get().queryConfirmYourChoice(LoadActivity.this.getActivity(), e.getMessage(), new DialogInterface.OnClickListener() {
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            LoadActivity.AnonymousClass1.this.lambda$onSuccess$4$LoadActivity$1(dialogInterface, i);
                        }
                    }, new DialogInterface.OnClickListener() {
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            LoadActivity.AnonymousClass1.this.lambda$onSuccess$5$LoadActivity$1(dialogInterface, i);
                        }
                    });
                }
            }

            public /* synthetic */ void lambda$onSuccess$0$LoadActivity$1(DialogInterface dialogInterface, int i) {
                LoadActivity.this.downloadApk();
            }

            public /* synthetic */ void lambda$onSuccess$1$LoadActivity$1(DialogInterface dialogInterface, int i) {
                BaseApplication.get().finish(LoadActivity.this.getActivity());
            }

            public /* synthetic */ void lambda$onSuccess$2$LoadActivity$1(DialogInterface dialogInterface, int i) {
                LoadActivity.this.downloadApk();
            }

            public /* synthetic */ void lambda$onSuccess$3$LoadActivity$1(DialogInterface dialogInterface, int i) {
                LoadActivity.this.startMain();
            }

            public /* synthetic */ void lambda$onSuccess$4$LoadActivity$1(DialogInterface dialogInterface, int i) {
                LoadActivity.this.getConfig();
            }

            public /* synthetic */ void lambda$onSuccess$5$LoadActivity$1(DialogInterface dialogInterface, int i) {
                BaseApplication.get().startHome(LoadActivity.this.getActivity());
            }

            public void onFailure(String str) {
                boolean unused = LoadActivity.this.isSuccess = false;
                BaseDialog.get().queryConfirmYourChoice(LoadActivity.this.getActivity(), str, new DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        LoadActivity.AnonymousClass1.this.lambda$onFailure$6$LoadActivity$1(dialogInterface, i);
                    }
                }, new DialogInterface.OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        LoadActivity.AnonymousClass1.this.lambda$onFailure$7$LoadActivity$1(dialogInterface, i);
                    }
                });
            }

            public /* synthetic */ void lambda$onFailure$6$LoadActivity$1(DialogInterface dialogInterface, int i) {
                LoadActivity.this.getConfig();
            }

            public /* synthetic */ void lambda$onFailure$7$LoadActivity$1(DialogInterface dialogInterface, int i) {
                BaseApplication.get().startHome(LoadActivity.this.getActivity());
            }
        });
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



}