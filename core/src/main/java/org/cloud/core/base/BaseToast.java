package org.cloud.core.base;

import android.content.Context;
import android.widget.Toast;

import org.cloud.core.R;

public class BaseToast {
    private static BaseToast instance;
    private Toast mToast;
    private Context mContext;
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
        if (this.mToast != null) {
            this.mToast.cancel();
        }
        this.mToast = Toast.makeText(this.mContext, resId, Toast.LENGTH_LONG);
        this.mToast.show();
    }

    public void show(String str) {
        if (this.mToast != null) {
            this.mToast.cancel();
        }
        this.mToast = Toast.makeText(this.mContext, str, Toast.LENGTH_LONG);
        this.mToast.show();
    }

    public void showDataError() {
        show(this.mContext.getString(R.string.data_error));
    }

    public void showFailure() {
        show(this.mContext.getString(R.string.failure));
    }

    public void showNetworkTimeout() {
        show(this.mContext.getString(R.string.network_timeout));
    }

    public void showReturnOneMoreTime() {
        show(this.mContext.getString(R.string.return_one_more_time));
    }

    public void showSuccess() {
        show(this.mContext.getString(R.string.success));
    }
}
