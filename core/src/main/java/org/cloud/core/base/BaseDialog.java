package org.cloud.core.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import org.cloud.core.R;
import org.cloud.core.widget.MMLoading;

public class BaseDialog {
    private static volatile BaseDialog instance;
    private Context context;
    private ProgressDialog progressDialog;
    private MMLoading mmLoading;

    public static BaseDialog getInstance() {
        if (instance == null) {
            synchronized (BaseDialog.class) {
                if (instance == null) {
                    instance = new BaseDialog();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        this.context = context;
    }

    public void showLoading(Activity activity) {
        showLoading(activity, "加载中...");
    }

    public void showLoading(Activity activity, String msg) {
        if (mmLoading == null) {
            MMLoading.Builder builder = new MMLoading.Builder(activity)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setCancelOutside(true);
            mmLoading = builder.create();
        } else {
            mmLoading.dismiss();
            MMLoading.Builder builder = new MMLoading.Builder(activity)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setCancelOutside(false);
            mmLoading = builder.create();
        }
        mmLoading.show();
    }

    /**
     * 隐藏加载圈
     */
    public void hideLoading() {
        if (mmLoading != null && mmLoading.isShowing()) {
            mmLoading.dismiss();
            mmLoading = null;
        }
    }

    /**
     * 加载圈是否正在显示
     *
     * @return
     */
    public boolean isLoading() {
        return mmLoading != null && mmLoading.isShowing();
    }

    public void cancel() {
        try {
            if (this.progressDialog != null) {
                this.progressDialog.cancel();
                this.progressDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void progress(Activity activity) {
        try {
            this.progressDialog = new ProgressDialog(activity);
            this.progressDialog.setProgressStyle(0);
            this.progressDialog.setCanceledOnTouchOutside(false);
            this.progressDialog.setCancelable(false);
            this.progressDialog.setMessage(this.context.getString(R.string.handler));
            this.progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void query(Activity activity, String str, String str2, DialogInterface.OnClickListener onClickListener,
                      DialogInterface.OnClickListener onClickListener2) {
        try {
            new AlertDialog.Builder(activity).setTitle(str).setMessage(str2).setCancelable(false)
                    .setPositiveButton(this.context.getString(R.string.confirm), onClickListener)
                    .setNegativeButton(this.context.getString(R.string.cancel), onClickListener2)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queryLoadingFailure(Activity activity, String str, DialogInterface.OnClickListener onClickListener,
                                    DialogInterface.OnClickListener onClickListener2) {
        try {
            new AlertDialog.Builder(activity).setTitle(this.context.getString(R.string.data_loading_failed_do_you_try_again))
                    .setMessage(str).setCancelable(false).setPositiveButton(this.context.getString(R.string.confirm), onClickListener)
                    .setNegativeButton(this.context.getString(R.string.cancel), onClickListener2)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void queryConfirmYourChoice(Activity activity, String str, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        try {
            AlertDialog.Builder dialog = new AlertDialog.Builder(activity).setTitle(this.context.getString(R.string.confirm_your_choice))
                    .setMessage(str).setCancelable(false).setPositiveButton(this.context.getString(R.string.confirm), onClickListener)
                    .setNegativeButton(this.context.getString(R.string.cancel), onClickListener2);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
