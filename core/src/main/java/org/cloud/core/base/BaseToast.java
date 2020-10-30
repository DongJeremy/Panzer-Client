package org.cloud.core.base;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class BaseToast {
    private static BaseToast instance;

    private Toast mToast;

    private Context mContext;

    protected Handler handler = new Handler(Looper.getMainLooper());

    public static BaseToast getInstance() {
        if (instance == null) {
            synchronized (BaseToast.class) {
                if (instance == null) {
                    instance = new BaseToast();
                }
            }
        }

        return instance;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public void show(int resId) {
        showToast(resId, Toast.LENGTH_SHORT);
    }

    public void show(String paramString) {
        showToast(paramString, Toast.LENGTH_SHORT);
    }

    private void showToast(final String tips, final int duration) {
        if (android.text.TextUtils.isEmpty(tips)) {
            return;
        }
        handler.post(() -> {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, tips, duration);
            } else {
                mToast.setText(tips);
                mToast.setDuration(duration);
            }
            mToast.show();
        });
    }

    private void showToast(final int tips, final int duration) {
        handler.post(() -> {
            if (mToast == null) {
                mToast = Toast.makeText(mContext, tips, duration);
            } else {
                mToast.setText(tips);
                mToast.setDuration(duration);
            }
            mToast.show();
        });
    }

    public void showDataError() {
        show("数据错误");
    }

    public native void showFailure();

    public native void showNetworkTimeout();

    public native void showReturnOneMoreTime();

    public native void showSuccess();
}
