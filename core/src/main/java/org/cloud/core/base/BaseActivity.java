package org.cloud.core.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.cloud.core.R;
import org.cloud.core.rx.RxBus;
import org.cloud.core.utils.StatusBarUtils;
import org.cloud.core.widget.MMLoading;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ddw
 * @date 2020/11/6 17:12
 * @desc 基类 BaseMvpActivity
 */
public abstract class BaseActivity extends RxAppCompatActivity {

    private Activity activity;

    private Unbinder unBinder;

    private MMLoading mmLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(getLayoutId());
        unBinder = ButterKnife.bind(this);
        if (useEventBus()) {
            RxBus.getInstance().register(this);
        }
        StatusBarUtils.setStatusBarMode(this, true, R.color.white);
        initPreparedData();
        initView();
        initData();
        initListener();
    }

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
            RxBus.getInstance().unRegister(this);
        }
    }

    public Activity getActivity() {
        return activity;
    }

    /**
     * 显示进度框
     */
    protected void showLoadingDialog() {
        showLoadingDialog("加载中...");
    }

    /**
     * 显示自定义文字的进度框
     * @param msg  进度框显示的文字
     */
    protected void showLoadingDialog(String msg) {
        if (mmLoading == null) {
            MMLoading.Builder builder = new MMLoading.Builder(this)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setCancelOutside(true);
            mmLoading = builder.create();
        }else {
            mmLoading.dismiss();
            MMLoading.Builder builder = new MMLoading.Builder(this)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setCancelOutside(false);
            mmLoading = builder.create();
        }
        mmLoading.show();
    }

    /**
     * 隐藏进度框
     */
    protected void hideLoadingDialog() {
        if (mmLoading != null && mmLoading.isShowing()) {
            mmLoading.dismiss();
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
     * 是否使用eventBus
     */
    protected abstract boolean useEventBus();
    /**
     * 获取布局 Id
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

    public void onReturn() {
        finish();
    }

    /**
     * 配置工具栏
     * @param toolbar 工具栏
     */
    protected void setToolbar(Toolbar toolbar) {
        setToolbar(toolbar, null);
    }

    /**
     * 配置工具栏
     * @param toolbar 工具栏
     * @param title   标题栏文字
     */
    protected void setToolbar(Toolbar toolbar, String title) {
        toolbar.setTitle("");
        toolbar.setContentInsetStartWithNavigation(0);
        toolbar.setNavigationIcon(R.drawable.ic_action_back);
        if (title!=null) {
            AppCompatTextView titleTextView = findViewById(R.id.titleTextView);
            titleTextView.setGravity(Gravity.CENTER);
            titleTextView.setText(title);
        }
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onReturn());
    }

    /**
     * 配置工具栏
     * @param toolbar 工具栏
     * @param title   标题栏文字
     */
    protected void setToolbar(Toolbar toolbar, String title, int colorId) {
        setToolbar(toolbar, title);
        StatusBarUtils.setStatusBarMode(this, true, colorId);
    }

}
