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
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.cloud.core.R;
import org.cloud.core.app.AppManager;
import org.cloud.core.utils.LoadingViewUtils;
import org.cloud.core.utils.cache.CacheManager;

import java.util.List;

@SuppressLint("StaticFieldLeak")
public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    //是否允许图片下载
    private boolean isImage;

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(BaseShared.getInstance().getString(BaseConstant.SHARED_KEY));
    }

    private RefWatcher mRefWatcher;

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        CacheManager.init(this);
        //检测内存泄漏
        initLeakCanary();
        //注册监听每个activity的生命周期,便于堆栈式管理
        registerActivityLifecycleCallbacks(mCallbacks);
        BaseShared.getInstance().init(getSharedPreferences(BaseConstant.SHARED_NAME, MODE_PRIVATE));
        BaseToast.getInstance().init(this);
        BaseImageLoader.getInstance().init(this);

        isImage = BaseShared.getInstance().getBoolean(BaseConstant.SHARED_SETTING_IMAGE, true);
        BaseShared.getInstance().putString(BaseConstant.SHARED_KEY, BaseConstant.SHARED_KEY_TEMP);
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        mRefWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.mRefWatcher;
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

    //公共方法
    public int getWidth() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    public int getHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int dipToSp(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dip, getResources().getDisplayMetrics());
    }

    public int dipToPx(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }

    public int getColors(int id) {
        return ContextCompat.getColor(this, id);
    }

    public int getStatusBarHeight() {
        return getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));
    }

    public void setFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.requestFocusFromTouch();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void setWebView(BaseActivity activity, WebView webView) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                LoadingViewUtils.showLoading(activity, true);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                imgReset(webView);
                if (LoadingViewUtils.isLoading(activity)) {
                    LoadingViewUtils.hideLoading(activity);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private void imgReset(WebView webView) {
        webView.loadUrl("javascript:(function(){document.getElementsByTagName('body')[0].style.margin='0';" +
                "var objs = document.getElementsByTagName('img');" +
                "for(var i=0;i<objs.length;i++){var img=objs[i];img.style.maxWidth='100%';img.style.height='auto';}" +
                "})()");
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
        tabLayout.setSelectedTabIndicatorColor(getColors(R.color.primary));
        tabLayout.setTabTextColors(getColors(R.color.greyAdd), getColors(R.color.primary));
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

    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "0.0";
        }
    }

    public Drawable getMipmap(int id) {
        return ContextCompat.getDrawable(this, id);
    }

    public Drawable getMipmap(int id, int color) {
        Drawable mDrawable = ContextCompat.getDrawable(this, id);
        mDrawable = DrawableCompat.wrap(mDrawable);
        DrawableCompat.setTint(mDrawable, ContextCompat.getColor(this, color));
        return mDrawable;
    }

    public Drawable getMipmap(Drawable drawable, int color) {
        Drawable mDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(mDrawable, ContextCompat.getColor(this, color));
        return mDrawable;
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

    public void startTypeValue(Activity activity, String type, String value) {
        Intent intent = null;
    }

    //Activity跳转
    public void startHome(Activity activity) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        start(activity, intent);
    }

    public void startLocation(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        start(activity, intent);
    }

    public void start(Activity activity, Class cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
    }

    public void start(Activity activity, Intent intent) {
        activity.startActivity(intent);
    }

    public void start(Activity activity, Class cls, int code) {
        Intent intent = new Intent(activity, cls);
        activity.startActivityForResult(intent, code);
    }

    public void start(Activity activity, Intent intent, int code) {
        activity.startActivityForResult(intent, code);
    }

    public void startCall(Activity activity, String mobile) {
        try {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobile));
            start(activity, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Activity销毁
    public void finish(Activity activity) {
        activity.finish();
    }

}
