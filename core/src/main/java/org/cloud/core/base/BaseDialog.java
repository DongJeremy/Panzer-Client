package org.cloud.core.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import org.cloud.core.R;

public class BaseDialog {
    private static volatile BaseDialog instance;
    private Context context;
    private ProgressDialog progressDialog;

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

    public void init(Context context2) {
        this.context = context2;
    }

    public void cancel() {
        try {
            if (this.progressDialog != null) {
                this.progressDialog.cancel();
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
            new AlertDialog.Builder(activity).setTitle(this.context.getString(R.string.confirm_your_choice))
                    .setMessage(str).setCancelable(false).setPositiveButton(this.context.getString(R.string.confirm), onClickListener)
                    .setNegativeButton(this.context.getString(R.string.cancel), onClickListener2)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
