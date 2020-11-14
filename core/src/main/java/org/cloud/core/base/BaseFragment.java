package org.cloud.core.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxFragment;

import org.cloud.core.rx.RxBus;
import org.jetbrains.annotations.NotNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
* FileName: BaseFragment
* Author: Admin
* Date: 2020/11/14 8:39
* Description: fragment 基类
*/
public abstract class BaseFragment extends RxFragment {

    protected Context mContext;

    private Unbinder unBinder;

    private BaseActivity mActivity;
    /**
     * 缓存Fragment view
     */
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
            unBinder = ButterKnife.bind(this, mRootView);
            if (useEventBus()) {
                RxBus.getInstance().register(this);
            }
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPreparedData();
        initView();
        initListener();
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) this.getActivity();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        if (useEventBus()) {
            RxBus.getInstance().unRegister(this);
        }
    }

    public static <T extends BaseFragment> T newInstance(Class<T> mClass, Bundle args) {
        try {
            T instance = mClass.newInstance();
            instance.setArguments(args);
            return instance;
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前所属activity
     * @return baseActivity
     */
    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    /**
     * 供子类添加功能
     */
    protected void initPreparedData() {}
    /**
     * 返回一个用于显示界面的布局id
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
    /**
     * 是否使用eventbus
     */
    protected abstract boolean useEventBus();
}
