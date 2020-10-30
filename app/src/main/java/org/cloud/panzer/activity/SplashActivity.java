package org.cloud.panzer.activity;

import android.content.Intent;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

import org.cloud.core.base.BaseActivity;
import org.cloud.core.utils.StatusBarUtils;
import org.cloud.panzer.PanzerApplication;
import org.cloud.panzer.R;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.splashConstraintLayout)
    ConstraintLayout mConstraintLayout;
    @BindView(R.id.btn_skip)
    Button mBtn_skip;

    private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    private boolean mIsSkip = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void getIntent(Intent intent) {

    }

    @Override
    protected void initData() {
        PanzerApplication.getInstance().setFullScreen(getActivity());
    }

    @Override
    protected void initView() {
        StatusBarUtils.setStatusBar(this, false, true);
        mConstraintLayout.setBackgroundResource(R.mipmap.bg_load);
    }

    @Override
    protected void initListener() {
        mBtn_skip.setOnClickListener(view -> skip());
        mCompositeDisposable.add(Observable.interval(0, 1, TimeUnit.SECONDS)
                .map(aLong -> 3 - aLong.intValue())
                .take(4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(@NotNull Integer integer) {
                        mBtn_skip.setText(String.format(getResources().getString(R.string.splash_skip_format), integer));
                    }

                    @Override
                    public void onComplete() {
                        skip();
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        skip();
                    }
                }));
    }

    private void skip() {
        if (!mIsSkip) {
            mIsSkip = true;
            finish();
            //start(activity, intent);
            startActivity(new Intent(getActivity(), MainActivity.class));
            overridePendingTransition(R.anim.hold, R.anim.zoom_in_exit);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }
}