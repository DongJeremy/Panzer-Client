package org.cloud.core.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.cloud.core.R;
import org.cloud.core.utils.LoadingViewUtils;
import org.cloud.core.utils.StatusBarUtils;
import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xuhao
 * @date 2018/6/9 17:12
 * @desc 基类 BaseMvpActivity
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    private Activity activity;

    private Unbinder unBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        if (useEventBus()) {
            EventBus.getDefault().register(this);//注册eventBus
        }
        StatusBarUtils.setStatusBarMode(this, true, R.color.white);
        initPreparedData();
        initView();
        initData();
        initListener();
    }

    /**
     * 是否使用eventBus
     *
     * @return
     */
    protected abstract boolean useEventBus();

    /**
     * 供子类添加功能
     *
     * @return
     */
    protected void initPreparedData() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        if (useEventBus()) {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);//注销eventBus
            }
        }
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, null, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 显示进度框
     */
    protected void showLoadingDialog() {
        LoadingViewUtils.showLoading(this, true);
    }

    /**
     * 隐藏进度框
     */
    protected void hideLoadingDialog() {
        if (LoadingViewUtils.isLoading(this)) {
            LoadingViewUtils.hideLoading(this);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            onReturn();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            onReturn();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 获取布局 Id
     *
     * @return
     */
    protected abstract @LayoutRes int getLayoutId();

    /**
     * 初始化View的代码写在这个方法中
     */
    protected abstract void initView();

    /**
     * 初始化监听器的代码写在这个方法中
     */
    protected abstract void initListener();

    /**
     * 初始数据的代码写在这个方法中，用于从服务器获取数据
     */
    protected abstract void initData();

    public Activity getActivity() {
        return activity;
    }

    public void onReturn() {
        finish();
    }

    public void setToolbar(Toolbar toolbar) {
        toolbar.setTitle("");
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onReturn());
    }

    public void setToolbar(Toolbar toolbar, String title) {
        toolbar.setTitle("");
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        AppCompatTextView viewById = findViewById(R.id.titleTextView);
        viewById.setGravity(Gravity.CENTER);
        viewById.setText(title);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onReturn());
    }
}
