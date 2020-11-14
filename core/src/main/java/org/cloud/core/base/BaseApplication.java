package org.cloud.core.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.cloud.core.R;
import org.cloud.core.app.AppManager;
import org.cloud.core.utils.ImageUtils;
import org.cloud.core.utils.SPUtils;
import org.cloud.core.utils.StringUtils;
import org.cloud.core.utils.Utils;
import org.cloud.core.utils.cache.CacheManager;
import org.cloud.core.widget.JavascriptInterface;

import java.util.List;

@SuppressLint("StaticFieldLeak")
public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    public IWXAPI iwxapi;
    private boolean isWxPay;
    private boolean isWxPaySuccess;

    public static BaseApplication getInstance() {
        return mInstance;
    }

    //是否允许图片下载
    private boolean isImage;

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public boolean isLogin() {
        return !StringUtils.isEmpty(SPUtils.getInstance().getString(BaseConstant.SHARED_KEY));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        CacheManager.init(this);
        mInstance = this;
        //注册监听每个activity的生命周期,便于堆栈式管理
        registerActivityLifecycleCallbacks(mCallbacks);
        Utils.init(this);
        SPUtils.getInstance().init(BaseConstant.SHARED_NAME);
        ImageUtils.getInstance().init(this, 2);
        BaseToast.getInstance().init(this);
        BaseDialog.getInstance().init(this);
        BaseFileClient.getInstance().init(getPackageName());

        //微信支付
        this.isWxPay = false;
        this.isWxPaySuccess = false;
        iwxapi = WXAPIFactory.createWXAPI(this, null);
        iwxapi.registerApp(BaseConstant.WX_APP_ID);

        isImage = SPUtils.getInstance().getBoolean(BaseConstant.SHARED_SETTING_IMAGE, true);
    }

    public IWXAPI getIwxapi() {
        return iwxapi;
    }

    public void setIwxapi(IWXAPI iwxapi) {
        this.iwxapi = iwxapi;
    }

    public boolean isWxPay() {
        return isWxPay;
    }

    public void setWxPay(boolean wxPay) {
        isWxPay = wxPay;
    }

    public boolean isWxPaySuccess() {
        return this.isWxPaySuccess;
    }

    public void setWxPaySuccess(boolean isWxPaySuccess) {
        this.isWxPaySuccess = isWxPaySuccess;
    }

    private final ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AppManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getInstance().removeActivity(activity);
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    public void setWebView(BaseActivity activity, WebView webView) {
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        // 不显示缩放控件
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.addJavascriptInterface(new JavascriptInterface(this), "imagelistner");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                imgReset(webView);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    public void loadHtml(WebView webView, String str) {
        String replace = str.replace("style=", "other=").replace("src=\"/system", "src=\"https://www.wpccw.com/system");
        webView.loadDataWithBaseURL((String) null, "<html><head><style type='text/css'>*{margin:0,padding:0}body{margin:0;padding:1px;" +
                "line-height:28px;}p,span,section,img,table,embed,input,dl,dd,tr,td,video{width:100%;padding:0;margin:0;color:#333333;" +
                "line-height:28px;}</style></head><body>" + replace + "</body></html>", "text/html", "UTF-8", (String) null);
    }

    private void imgReset(WebView webView) {
        webView.loadUrl("javascript:(function(){var objs = document.getElementsByTagName('img');for(var i=0;i<objs.length;i++){objs[i]" +
                ".onclick=function(){window.imagelistner.openImage(this.src);}}})()");
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(activity.getWindow().peekDecorView().getWindowToken(), 0);
        }
    }

    public void setFullScreen(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void setText2Clipboard(String content) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("", content);
        clipboardManager.setPrimaryClip(clipData);
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.boy, R.color.girl, R.color.primaryAdd);
    }

    public void setTabLayout(TabLayout tabLayout, BaseViewPagerAdapter adapter, ViewPager viewPager) {
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.primary));
        tabLayout.setTabTextColors(ContextCompat.getColor(this, R.color.greyAdd), ContextCompat.getColor(this, R.color.primary));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    public void setRecyclerView(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.requestFocus();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setRecyclerView(Activity activity, RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.requestFocus();
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(null);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
    }

    public GradientDrawable getGradientDrawable(float radius, int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        return gradientDrawable;
    }

    public boolean isLaunchedActivity(@NonNull Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        if (cmpName != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean inActivityStackTop() {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasksInfo = manager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            return getPackageName().equals(tasksInfo.get(0).topActivity.getPackageName());
        }
        return false;
    }

    public boolean inActivityStack(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        ComponentName componentName = intent.resolveActivity(getPackageManager());
        boolean flag = false;
        if (componentName != null) {
            ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = activityManager.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(componentName)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean inActivityStackTop(Class<?> cls) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.equals(cls.getName());
    }

    /**
     * 含有Bundle通过Class跳转界面
     *
     * @param cls    跳转到activity或者service
     * @param bundle 用于传递数据
     */
    public void start(Activity activity, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivity(intent);
    }

    public void start(Activity activity, Class cls, int code) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, code);
    }

    public void start(Activity activity, Intent intent, int code) {
        activity.startActivityForResult(intent, code);
    }

    /**
     * 通过Class跳转界面
     *
     * @param cls 跳转到activity或者service
     */
    public void start(Activity activity, Class<?> cls) {
        start(activity, cls, null);
    }

    public void start(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    public void startForResult(Activity activity, Intent intent, int code) {
        activity.startActivityForResult(intent, code);
    }

    /**
     * 通过Class跳转界面
     *
     * @param cls         跳转到activity或者service
     * @param requestCode 要返回的依据
     */
    public void startForResult(Activity activity, Class<?> cls, int requestCode) {
        startForResult(activity, cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     *
     * @param cls         跳转到activity或者service
     * @param bundle      用于传递数据
     * @param requestCode 要返回的依据
     */
    public void startForResult(Activity activity, Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    public void startApplicationSetting(Activity activity, String str) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", str, (String) null));
        start(activity, intent);
    }

    //Activity销毁
    public void finish(Activity activity) {
        activity.finish();
    }
}
