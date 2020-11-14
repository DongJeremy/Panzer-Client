package org.cloud.core.utils.old;

import org.cloud.core.base.BaseActivity;
import org.cloud.core.widget.MMLoading;

public class LoadingViewUtils {

    private static MMLoading mmLoading;

    /**
     * 显示加载圈
     *
     * @param activity
     * @param msg  是否需要遮罩 防止点击
     */

    public static void showLoading(BaseActivity activity, String msg) {
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
    public static void hideLoading() {
        if (mmLoading != null && mmLoading.isShowing()) {
            mmLoading.dismiss();
        }
    }

    /**
     * 加载圈是否正在显示
     *
     * @param activity
     * @return
     */
    public static boolean isLoading(BaseActivity activity) {
        return mmLoading != null && mmLoading.isShowing();
    }
}
