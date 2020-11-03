package org.cloud.core.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.style.FadingCircle;

import org.cloud.core.R;
import org.cloud.core.base.BaseActivity;

public class LoadingViewUtils {
    /**
     * 显示加载圈
     * @param activity
     * @param isCover 是否需要遮罩 防止点击
     */
    public static void showLoading(BaseActivity activity , boolean isCover){
        if (isLoading(activity)){
            return;
        }
        FrameLayout root = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        if (root != null){
            View loadingView = LayoutInflater.from(activity).inflate(R.layout.lyt_loading, null);
            if (isCover) {
                //遮罩层设置点击事件，拦截底层视图的点击事件
                loadingView.findViewById(R.id.cover).setOnClickListener(v -> {
                });
            }
            loadingView.findViewById(R.id.cover).setVisibility(isCover ? View.VISIBLE : View.GONE);
//            ProgressBar progressBar = loadingView.findViewById(R.id.spin_kit);
//            progressBar.setIndeterminateDrawable(new FadingCircle());
            root.removeView(loadingView);
            root.addView(loadingView);
        }
    }

    /**
     * 隐藏加载圈
     * @param activity
     */
    public static void hideLoading(BaseActivity activity){
        FrameLayout root = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        if (root != null){
            View loadingView = root.findViewById(R.id.cover_root);
            if (loadingView != null) {
                root.removeView(loadingView);
            }
        }
    }

    /**
     * 加载圈是否正在显示
     * @param activity
     * @return
     */
    public static boolean isLoading(BaseActivity activity) {
        FrameLayout root = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        if (root != null){
            View loadingView = root.findViewById(R.id.cover_root);
            return loadingView != null && root.indexOfChild(loadingView) != -1;
        }
        return false;
    }
}
